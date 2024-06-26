package com.example.szskimbokyun.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Errors {

    @JsonProperty("code")
    private Object code;

    @JsonProperty("message")
    private Object message;

    @JsonProperty("validations")
    private Object validations;
}
