package com.vet.pets.entities;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_animals")
public class Animals {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 45, nullable = false)
    private String name;
    @Column (length = 255, nullable = false)
    private String gender;
    @Column(nullable = false)
    private Date dateOfBirth;
    @Column(nullable = false)
    private Float weight;
    @ManyToOne
    @JoinColumn(name = "id_customers", nullable = false)
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "id_breeds", nullable = false)
    private Breeds race;
}