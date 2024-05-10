package com.vet.pets.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vet.pets.entities.Services;

public interface ServiceRepository extends JpaRepository<Services, Long>{
    @Query("SELECT ss FROM Services ss WHERE ss.name = ?1")
    Optional<Services> findByName(String name);
}
