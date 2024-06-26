package com.example.szskimbokyun.controller.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "회원가입")
@Getter
@Setter
public class SignUpDto {

    @NotNull
    @Schema(description = "사용자 아이디", required = true)
    private String userId;

    @NotNull
    @Schema(description = "사용자 비밀번호", required = true)
    private String password;

    @NotNull
    @Schema(description = "사용자 이름", required = true)
    private String name;

    @NotNull
    @Pattern(regexp = "^(\\d{6})-(\\d{7})$", message = "주민등록번호 형식이 올바르지 않습니다. (예: YYMMDD-NNNNNNN)")
    @Schema(description = "사용자 주민등록번호", required = true)
    private String regNo;

}
