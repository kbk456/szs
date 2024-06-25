package com.example.szskimbokyun.service;

import com.example.szskimbokyun.controller.dto.SignUpMemberDto;
import com.example.szskimbokyun.domain.Member;
import com.example.szskimbokyun.repository.MemberRepository;
import com.example.szskimbokyun.service.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    private final MemberMapper memberMapper;

    public SignUpMemberDto save(SignUpMemberDto signUpMemberDto){
        Member member = memberMapper.toEntity(signUpMemberDto);
        member = memberRepository.save(member);
        return memberMapper.toDto(member);
    }
}
