package com.example.szskimbokyun.controller.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "로그인 응답")
@Getter
@Setter
public class LoginResponse {

    @NotNull
    @Schema(description = "토큰", required = true)
    private String accessToken;

}
