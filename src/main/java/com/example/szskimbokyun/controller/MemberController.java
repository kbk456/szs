package com.example.szskimbokyun.controller;

import com.example.szskimbokyun.controller.dto.SignUpMemberDto;
import com.example.szskimbokyun.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/szs")
@RequiredArgsConstructor
@Tag(name = "회원", description = "")
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "회원가입")
    @PostMapping("/signup")
    public ResponseEntity signUpMember(@Valid @RequestBody SignUpMemberDto signUpMemberDto){
        memberService.save(signUpMemberDto);
        return ResponseEntity.ok("회원가입성공");
    }
}
