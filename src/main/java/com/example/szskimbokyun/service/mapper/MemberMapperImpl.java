package com.example.szskimbokyun.service.mapper;

import com.example.szskimbokyun.controller.dto.MemberDto;
import com.example.szskimbokyun.domain.Member;
import com.example.szskimbokyun.service.EncodingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MemberMapperImpl implements MemberMapper {

    private final EncodingService encodingService;

    @Override
    public Member toEntity(MemberDto dto) {
        Member member = new Member();
        member.setName(dto.getName());
        member.setUserId(dto.getUserId());
        member.setPassword(encodingService.encoding(dto.getPassword()));
        member.setRegNo(encodingService.encoding(dto.getRegNo()));
        return member;
    }

    @Override
    public MemberDto toDto(Member entity) {
        MemberDto signUpDto = new MemberDto();
        signUpDto.setName(entity.getName());
        return signUpDto;
    }

    @Override
    public List<Member> toEntity(List<MemberDto> dtoList) {
        return List.of();
    }

    @Override
    public List<MemberDto> toDto(List<Member> entityList) {
        return List.of();
    }


}
