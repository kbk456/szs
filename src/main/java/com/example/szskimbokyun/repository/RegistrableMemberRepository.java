package com.example.szskimbokyun.repository;

import com.example.szskimbokyun.domain.RegistrableMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegistrableMemberRepository extends JpaRepository<RegistrableMember, Long> {
    Optional<RegistrableMember> findByName(String name);
}
