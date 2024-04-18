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
@Table(name = "tb_client")
public class Client{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 45, nullable = false)
    private String name;
    @Column(length = 150, nullable = false)
    private String email;
    @Column(length = 11, nullable = false)
    private Long cpf;
    @Column(length = 14, nullable = false)
    private String phone;
}
