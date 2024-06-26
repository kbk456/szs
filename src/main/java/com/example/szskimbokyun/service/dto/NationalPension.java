package com.example.szskimbokyun.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class NationalPension {

    @JsonProperty("월")
    private String month;

    @JsonProperty("공제액")
    private String deductionAmount;
}
