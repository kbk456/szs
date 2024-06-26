package com.example.szskimbokyun.controller;

import com.example.szskimbokyun.controller.dto.TaxResponse;
import com.example.szskimbokyun.domain.Income;
import com.example.szskimbokyun.domain.Tax;
import com.example.szskimbokyun.repository.IncomeRepository;
import com.example.szskimbokyun.repository.TaxRepository;
import com.example.szskimbokyun.service.MemberService;
import com.example.szskimbokyun.service.TaxService;
import com.example.szskimbokyun.service.dto.IncomeDeduction;
import com.example.szskimbokyun.service.dto.IncomeDto;
import com.example.szskimbokyun.service.dto.ScrapRequestDTO;
import com.example.szskimbokyun.service.dto.ScrapResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.text.DecimalFormat;
import java.util.Optional;

@RestController
@RequestMapping("/szs")
@RequiredArgsConstructor
@Tag(name = "세금", description = "")
public class TaxController {

    private final TaxService taxService;

    private final IncomeRepository incomeRepository;

    private final TaxRepository taxRepository;

    @Operation(summary = "스크래핑")
    @PostMapping("/scrap")
    public ResponseEntity<String> scrap() {
        ScrapRequestDTO requestDTO = new ScrapRequestDTO();
        requestDTO.setName("동탁");
        requestDTO.setRegNo("921108-1582816");
        ScrapResponseDTO scrap = taxService.scrap(requestDTO);

        BigDecimal totalDeduction = taxService.getTotalDeduction(scrap);
        String name = scrap.getData().getName();
        long totalIncome = scrap.getData().getTotalIncome();
        String taxDeduction = scrap.getData().getIncomeDeduction().getTaxDeduction();

        IncomeDto incomeDto = new IncomeDto();
        incomeDto.setTotalIncome(String.valueOf(totalIncome));
        incomeDto.setTaxDeduction(taxDeduction);
        incomeDto.setTotalDeduction(String.valueOf(totalDeduction));
        incomeDto.setName(name);

        taxService.save(incomeDto);

        return ResponseEntity.ok("저장성공");
    }

    @Operation(summary = "결정세액")
    @GetMapping("/refund")
    public ResponseEntity<TaxResponse> refund(){

        String name = "동탁";

        Optional<Income> income = incomeRepository.findByName(name);

        if (income.isPresent()){
            Income income1 = income.get();
            
            String totalIncome = income1.getTotalIncome();
            
            String taxDeduction = income1.getTaxDeduction();

            String totalDeduction = income1.getTotalDeduction();

            // 종합소득금액
            BigDecimal totalIncomeBig = new BigDecimal(totalIncome);

            // 세액공제
            BigDecimal taxDeductionBig = new BigDecimal(taxDeduction.replaceAll(",",""));

            // 소득공제
            BigDecimal totalDeductionBig = new BigDecimal(totalDeduction.replaceAll(",",""));

            // 종합소득금액 - 소득공제 = 과세표준
            BigDecimal subtract = totalIncomeBig.subtract(totalDeductionBig);

            // 과세표준
            BigDecimal taxBase = subtract.setScale(0, BigDecimal.ROUND_HALF_DOWN);

            // 과세표준 으로 세율 조회
            Optional<Tax> taxInfo = taxRepository.findTaxesInRange(taxBase.intValue());

            if (taxInfo.isPresent()){
                // 추가세
                int addTax = taxInfo.get().getAddTax();

                // 기본세율
                int basicTaxRate = taxInfo.get().getBasicTaxRate();

                // 과세표준 최소값
                int taxBaseMin = taxInfo.get().getTaxBaseMin();

                // 과세 표준 - 과세표준 최소값
                BigDecimal subtract1 = taxBase.subtract(BigDecimal.valueOf(taxBaseMin));
                BigDecimal subtract2 = subtract1.setScale(0, BigDecimal.ROUND_HALF_DOWN);

                // 기본세율 % 로 변환
                double basicTaxRate1 = basicTaxRate / 100.0;

                // (과세 표준 - 과세표준 최소값) * 기본세율
                BigDecimal result = subtract2.multiply(BigDecimal.valueOf(basicTaxRate1));
                BigDecimal result2 = result.setScale(0, BigDecimal.ROUND_HALF_DOWN);

                // result2 + 추가세 = 산출세액
                BigDecimal taxCalc = result2.add(BigDecimal.valueOf(addTax));

                //산출세액 - 세액공제
                BigDecimal 결정세액 = taxCalc.subtract(taxDeductionBig);
                DecimalFormat df = new DecimalFormat("#,##0");
                String formattedTaxAmount = df.format(결정세액);

                TaxResponse taxResponse = new TaxResponse();
                taxResponse.set결정세액(formattedTaxAmount);
                return ResponseEntity.ok(taxResponse);
            }
        }
        return null;
    }
}
