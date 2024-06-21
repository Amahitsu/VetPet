package com.vet.pets.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vet.pets.entities.Breeds;

public interface RaceRepository extends JpaRepository<Breeds , Long>{
    @Query("SELECT r FROM Breeds r WHERE r.name = ?1")
    Optional<Breeds> findByName(String name);
    List<Breeds> findRaceBySpecieId(Long specieId);
}
