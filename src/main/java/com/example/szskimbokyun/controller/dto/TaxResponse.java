package com.example.szskimbokyun.controller.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "결정세액")
@Getter
@Setter
public class TaxResponse {

    private String 결정세액;

}
