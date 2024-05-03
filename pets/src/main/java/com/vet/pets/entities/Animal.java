package com.vet.pets.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_animal")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 45, nullable = false)
    private String name;
    @Column(length = 10,nullable=false)
    private LocalDate birthDate;
    @Column(length = 6,nullable=false)
    private float weight;
    @ManyToOne
    @JoinColumn(name = "id_customer")
    private Customer customer_id;
    @ManyToOne
    @JoinColumn(name = "id_race")
    private Race race;

}
