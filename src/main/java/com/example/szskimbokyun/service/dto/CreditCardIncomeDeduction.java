package com.example.szskimbokyun.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.Map;

@Data
public class CreditCardIncomeDeduction {

    @JsonProperty("month")
    private ArrayList<Map<String, String>> monthlyDeductions;

    @JsonProperty("year")
    private int year;
}
