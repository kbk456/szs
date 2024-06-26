package com.example.szskimbokyun.service.mapper;

import com.example.szskimbokyun.domain.Income;
import com.example.szskimbokyun.service.dto.IncomeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class IncomeMapperImpl implements IncomeMapper{

    @Override
    public Income toEntity(IncomeDto dto) {
        Income income = new Income();
        income.setName(dto.getName());
        income.setTotalIncome(dto.getTotalIncome());
        income.setTaxDeduction(dto.getTaxDeduction());
        income.setTotalDeduction(dto.getTotalDeduction());
        return income;
    }

    @Override
    public IncomeDto toDto(Income entity) {
        IncomeDto incomeDto = new IncomeDto();
        incomeDto.setName(entity.getName());
        incomeDto.setTotalIncome(entity.getTotalIncome());
        incomeDto.setTotalDeduction(entity.getTotalDeduction());
        incomeDto.setTaxDeduction(entity.getTaxDeduction());
        return incomeDto;
    }

    @Override
    public List<Income> toEntity(List<IncomeDto> dtoList) {
        return null;
    }

    @Override
    public List<IncomeDto> toDto(List<Income> entityList) {
        return null;
    }
}
