package com.vet.pets.repository;

import com.vet.pets.entities.Species;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeciesRepository extends JpaRepository<Species, Long> {
}
