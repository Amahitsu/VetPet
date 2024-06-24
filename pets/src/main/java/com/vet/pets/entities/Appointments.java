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
@Table(name = "tb_appointments")
public class Appointments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String start_time;
    @Column(nullable = false)
    private String finish_time;
    @Column(nullable = false)
    private Date date;
    @ManyToOne
    @JoinColumn(name = "id_customers", nullable = false)
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "id_animals", nullable = false)
    private Animals animal;
    @ManyToOne
    @JoinColumn(name = "id_workers", nullable = false)
    private Worker worker;
    @ManyToOne
    @JoinColumn(name = "id_services", nullable = false)
    private Services service;
    @Column(nullable = false)
    private String observation;
}