package com.vet.pets.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vet.pets.entities.Animals;

public interface AnimalRepository extends JpaRepository<Animals , Long>{
    @Query("SELECT a FROM Animals a WHERE a.name = ?1")
    Optional<Animals> findByName(String name);
}
