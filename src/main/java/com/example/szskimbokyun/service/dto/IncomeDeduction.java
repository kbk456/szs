package com.example.szskimbokyun.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class IncomeDeduction {

    @JsonProperty("국민연금")
    private List<NationalPension> nationalPensions;

    @JsonProperty("신용카드소득공제")
    private CreditCardIncomeDeduction creditCardIncomeDeduction;

    @JsonProperty("세액공제")
    private String taxDeduction;

}
