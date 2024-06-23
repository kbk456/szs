package com.example.szskimbokyun.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "registrable_member",
        uniqueConstraints={@UniqueConstraint(name="uniqueNameAndRegNo",
        columnNames={"name", "reg_no"})})
@Entity
@Getter
@NoArgsConstructor
public class RegistrableMember {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "reg_no", nullable = false)
    private String regNo;

}
