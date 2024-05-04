package com.vet.pets.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vet.pets.entities.Races;

public interface RaceRepository extends JpaRepository<Races , Long>{
    @Query("SELECT r FROM Races r WHERE r.name = ?1")
    Optional<Races> findByName(String name);
}
