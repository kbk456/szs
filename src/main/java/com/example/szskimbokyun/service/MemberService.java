package com.example.szskimbokyun.service;

import com.example.szskimbokyun.controller.dto.MemberDto;
import com.example.szskimbokyun.controller.dto.SignUpDto;
import com.example.szskimbokyun.domain.Member;
import com.example.szskimbokyun.repository.MemberRepository;
import com.example.szskimbokyun.security.jwt.JwtUtil;
import com.example.szskimbokyun.service.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final MemberMapper memberMapper;

    private final JwtUtil jwtUtil;

    @Transactional
    public MemberDto save(MemberDto memberDto){
        Member member = memberMapper.toEntity(memberDto);
        member = memberRepository.save(member);
        return memberMapper.toDto(member);
    }

    @Transactional(readOnly = true)
    public Optional<Member> getMemberByUserId(String userId){
        return memberRepository.findByUserId(userId);
    }

    @Transactional(readOnly = true)
    public String getAccessToken(Member member){
        return jwtUtil.createAccessToken(member);
    }

}
