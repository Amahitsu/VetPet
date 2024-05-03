package com.vet.pets.repository;

import com.vet.pets.entities.Customer;
import com.vet.pets.entities.Race;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RaceRepository extends JpaRepository<Race, Long> {
}
