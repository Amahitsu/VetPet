package com.vet.pets.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vet.pets.entities.Vaccines;

public interface VaccinesRepository extends JpaRepository<Vaccines, Long>{
    @Query("SELECT v FROM Vaccines v WHERE v.name = ?1")
    Optional<Vaccines> findByName(String name);
}
