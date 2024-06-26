package com.example.szskimbokyun.service;

import com.example.szskimbokyun.domain.Income;
import com.example.szskimbokyun.repository.IncomeRepository;
import com.example.szskimbokyun.service.dto.*;
import com.example.szskimbokyun.service.mapper.IncomeMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
@RequiredArgsConstructor
public class TaxService {

    private final IncomeRepository incomeRepository;

    private final IncomeMapper incomeMapper;

    public ScrapResponseDTO scrap(ScrapRequestDTO requestDTO) {
        String apiUrl = "https://codetest-v4.3o3.co.kr/scrap";
        String AUTH_KEY = "fQL4PtoGRRgj6Q1OZG1WpQ==";

        // HTTP 요청 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(APPLICATION_JSON_VALUE));
        headers.set("X-API-KEY", AUTH_KEY);

        RestClient restClient = RestClient.create();
        String jsonString = restClient.post()
                .uri(apiUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(httpHeaders -> httpHeaders.addAll(headers))
                .body(requestDTO)
                .retrieve().body(String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(jsonString, ScrapResponseDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public BigDecimal getTotalDeduction(ScrapResponseDTO dto){
        BigDecimal totalSum = BigDecimal.ZERO;
        if(dto.getStatus().equals("success")){
            Data data = dto.getData();
            IncomeDeduction incomeDeduction = data.getIncomeDeduction();
            List<NationalPension> nationalPensions = incomeDeduction.getNationalPensions();
            ArrayList<Map<String, String>> creditCardIncomeDeduction = data.getIncomeDeduction().getCreditCardIncomeDeduction().getMonthlyDeductions();

            BigDecimal nationalPensionDeductionSum = BigDecimal.ZERO;
            for (NationalPension nationalPension : nationalPensions) {
                BigDecimal amountBigDecimal = new BigDecimal(nationalPension.getDeductionAmount().replaceAll(",",""));
                nationalPensionDeductionSum = nationalPensionDeductionSum.add(amountBigDecimal);
            }

            BigDecimal creditCardIncomeDeductionSum = BigDecimal.ZERO;

            for (Map<String, String> monthMap : creditCardIncomeDeduction) {
                for (Map.Entry<String, String> entry : monthMap.entrySet()) {
                    String amountStr = entry.getValue();
                    BigDecimal amountBigDecimal = new BigDecimal(amountStr.replace(",", ""));
                    creditCardIncomeDeductionSum = creditCardIncomeDeductionSum.add(amountBigDecimal);
                }
            }
            totalSum = nationalPensionDeductionSum.add(creditCardIncomeDeductionSum);
        }
        return totalSum;
    }

    @Transactional
    public IncomeDto save(IncomeDto incomeDto){
        Income income = incomeMapper.toEntity(incomeDto);
        income = incomeRepository.save(income);
        return incomeMapper.toDto(income);
    }

    public Optional<Income> findByName(String name){
        Optional<Income> income = incomeRepository.findByName(name);
        return income;
    }

}
