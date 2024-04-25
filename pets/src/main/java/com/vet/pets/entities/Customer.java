package com.vet.pets.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_customers")
public class Customer{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 45, nullable = false)
    private String name;
    @Column(length = 15, nullable = false, unique = true)
    private String cpf;
    @Column(name="phone_number", length = 14, nullable = false, unique = true)
    private String phone;
    @Column(length = 150, nullable = false, unique = true)
    private String email;
}
