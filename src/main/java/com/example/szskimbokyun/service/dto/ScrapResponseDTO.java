package com.example.szskimbokyun.service.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@lombok.Data
public class ScrapResponseDTO {

    @JsonProperty("status")
    private String status;

    @JsonProperty("data")
    private Data data;

    @JsonProperty("errors")
    private Errors errors;

}
