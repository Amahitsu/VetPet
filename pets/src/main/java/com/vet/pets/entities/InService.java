package com.vet.pets.entities;

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
@Table(name = "tb_inservice")
public class InService{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_services", nullable = false)
    private Services service;
    @ManyToOne
    @JoinColumn(name = "id_appointments", nullable = false)
    private Appointments appointment;
    @ManyToOne
    @JoinColumn(name = "id_medicines", nullable = false)
    private Medicines medicine;
    @ManyToOne
    @JoinColumn(name = "id_vaccines", nullable = false)
    private Vaccines vaccine;
    @Column(length = 300, nullable = true)
    private String observation;
}
