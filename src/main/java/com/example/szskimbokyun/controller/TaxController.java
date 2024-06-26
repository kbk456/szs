package com.example.szskimbokyun.controller;

import com.example.szskimbokyun.service.MemberService;
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


    @Operation(summary = "스크래핑")
    @PostMapping("/scrap")
    public ResponseEntity scrap(){


        return ResponseEntity.ok("");
    }

    @Operation(summary = "결정세액")
    @GetMapping("/refund")
    public ResponseEntity refund(){

        return ResponseEntity.ok("");
    }
}
