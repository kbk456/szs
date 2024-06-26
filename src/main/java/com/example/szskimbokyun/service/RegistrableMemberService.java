package com.example.szskimbokyun.service;

import com.example.szskimbokyun.domain.RegistrableMember;
import com.example.szskimbokyun.repository.RegistrableMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class RegistrableMemberService {

    private final RegistrableMemberRepository registrableMemberRepository;

    public Optional<RegistrableMember> findByUser(String name){
        return registrableMemberRepository.findByName(name);
    }
}
