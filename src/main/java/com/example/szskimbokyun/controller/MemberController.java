package com.example.szskimbokyun.controller;

import com.example.szskimbokyun.controller.dto.LoginDto;
import com.example.szskimbokyun.controller.dto.LoginResponse;
import com.example.szskimbokyun.controller.dto.MemberDto;
import com.example.szskimbokyun.domain.Member;
import com.example.szskimbokyun.domain.RegistrableMember;
import com.example.szskimbokyun.service.EncodingService;
import com.example.szskimbokyun.service.MemberService;
import com.example.szskimbokyun.service.RegistrableMemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import java.util.Optional;

@RestController
@RequestMapping("/szs")
@RequiredArgsConstructor
@Tag(name = "회원", description = "")
public class MemberController {

    private final MemberService memberService;

    private final RegistrableMemberService registrableMemberService;

    private final EncodingService encodingService;

    @Operation(summary = "회원가입")
    @PostMapping("/signup")
    public ResponseEntity signUp(@Valid @RequestBody MemberDto memberDto){
        Optional<RegistrableMember> byUser = registrableMemberService.findByUser(memberDto.getName());
        if(byUser.isPresent()){
            boolean regNoCheckResult = encodingService.matches(memberDto.getRegNo(), byUser.get().getRegNo());
            if(!regNoCheckResult){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity not found");
            }
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity not found");
        }
        memberService.save(memberDto);
        return ResponseEntity.ok("회원가입성공");
    }

    @Operation(summary = "로그인")
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginDto loginDto){
        Optional<Member> memberByUserId = memberService.getMemberByUserId(loginDto.getUserId());
        if(memberByUserId.isPresent()){
            boolean matches = encodingService.matches(loginDto.getPassword(), memberByUserId.get().getPassword());
            if (!matches){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity not found");
            }
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity not found");
        }
        String accessToken = memberService.getAccessToken(memberByUserId.get());
        LoginResponse response = new LoginResponse();
        response.setAccessToken(accessToken);
        return ResponseEntity.ok(response);
    }
}
