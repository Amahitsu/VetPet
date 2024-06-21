package com.vet.pets.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vet.pets.entities.Medicines;

public interface MedicinesRepository extends JpaRepository<Medicines, Long>{
    @Query("SELECT m FROM Medicines m WHERE m.name = ?1")
    Optional<Medicines> findByName(String name);
}
