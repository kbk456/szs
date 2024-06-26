package com.example.szskimbokyun.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
public class Data {

    @JsonProperty("종합소득금액")
    private long totalIncome;

    @JsonProperty("이름")
    private String name;

    @JsonProperty("소득공제")
    private IncomeDeduction incomeDeduction;
}
