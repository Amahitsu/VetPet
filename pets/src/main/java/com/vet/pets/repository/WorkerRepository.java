package com.vet.pets.repository;

import com.vet.pets.entities.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository extends JpaRepository<Worker,Long> {

    Worker findByUsername(String username);
}
