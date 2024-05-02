package com.vet.pets.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vet.pets.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("SELECT s FROM Customer c WHERE c.name = ?1")
    Optional<Customer> findByName(String name);
}
