package com.example.szskimbokyun.controller.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "로그인")
@Getter
@Setter
public class LoginDto {

    @NotNull
    @Schema(description = "사용자 아이디", required = true)
    private String userId;

    @NotNull
    @Schema(description = "사용자 패스워드", required = true)
    private String password;

}
