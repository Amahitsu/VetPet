package com.vet.pets.repository;
 
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vet.pets.entities.Worker;
 
public interface WorkerRepository extends JpaRepository<Worker,Long> {
    @Query("SELECT w FROM Worker w WHERE w.name = ?1")
    Optional<Worker> findByName(String name);
    //Worker findByUsername(String username);
}