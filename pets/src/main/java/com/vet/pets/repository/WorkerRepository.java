package com.vet.pets.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vet.pets.entities.Worker;

public interface WorkerRepository extends JpaRepository<Worker,Long> {

    Worker findByUsername(String username);
}
