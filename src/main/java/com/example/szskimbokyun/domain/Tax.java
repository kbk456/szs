package com.example.szskimbokyun.domain;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "tax")
@Entity
@Data
public class Tax {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int taxBaseMin;

    private int taxBaseMax;

    private int addTax;

    private int basicTaxRate;

}
