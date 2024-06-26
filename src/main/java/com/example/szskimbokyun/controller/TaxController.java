package com.example.szskimbokyun.controller;

import com.example.szskimbokyun.service.MemberService;
import com.example.szskimbokyun.service.TaxService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/szs")
@RequiredArgsConstructor
@Tag(name = "세금", description = "")
public class TaxController {

    private final MemberService memberService;

    private final TaxService taxService; ;

    @Operation(summary = "스크래핑")
    @PostMapping("/scrap")
    public ResponseEntity scrap(){
        taxService

        return ResponseEntity.ok("");
    }

    @Operation(summary = "결정세액")
    @GetMapping("/refund")
    public ResponseEntity refund(){

        return ResponseEntity.ok("");
    }
}
