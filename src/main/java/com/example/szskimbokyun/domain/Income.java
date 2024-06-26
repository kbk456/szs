package com.example.szskimbokyun.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Table(name = "income")
@Entity
@Data
public class Income {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String totalIncome;

    private String taxDeduction;

    private String totalDeduction;

}
