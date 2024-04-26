package com.vet.pets.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_workers")
public class Worker{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 45, nullable = false)
    private String name;
    @Column(length = 15, nullable = false, unique = true)
    private String cpf;
    @Column(name="phone_number", length = 14, nullable = false, unique = true)
    private String phone;
    @Column(length = 30, nullable = false)
    private String function;
    @Column(length = 2, nullable = false)
    private int userLevel;
    @Column(length = 1, nullable = false)
    private boolean active;
}
