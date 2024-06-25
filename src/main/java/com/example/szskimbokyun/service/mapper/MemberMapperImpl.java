package com.example.szskimbokyun.service.mapper;

import com.example.szskimbokyun.controller.dto.SignUpMemberDto;
import com.example.szskimbokyun.domain.Member;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MemberMapperImpl implements MemberMapper {
    @Override
    public Member toEntity(SignUpMemberDto dto) {
        Member member = new Member();
        member.setName(dto.getName());
        member.setPassword(dto.getPassword());
        member.setUserId(dto.getUserId());
        member.setRegNo(dto.getRegNo());
        return member;
    }

    @Override
    public SignUpMemberDto toDto(Member entity) {
        SignUpMemberDto signUpMemberDto = new SignUpMemberDto();
        signUpMemberDto.setName(entity.getName());
        return signUpMemberDto;
    }

    @Override
    public List<Member> toEntity(List<SignUpMemberDto> dtoList) {
        return null;
    }

    @Override
    public List<SignUpMemberDto> toDto(List<Member> entityList) {
        return null;
    }

}
