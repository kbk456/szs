package com.example.szskimbokyun.repository;

import com.example.szskimbokyun.domain.Tax;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaxRepository extends JpaRepository<Tax, Long> {
    @Query("SELECT t FROM Tax t WHERE :value > t.taxBaseMin AND :value <= t.taxBaseMax")
    Optional<Tax> findTaxesInRange(int value);
}
