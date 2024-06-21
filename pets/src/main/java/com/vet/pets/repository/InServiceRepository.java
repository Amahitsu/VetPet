package com.vet.pets.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vet.pets.entities.InService;

public interface InServiceRepository extends JpaRepository<InService , Long>{
    @Query("SELECT i FROM InService i WHERE i.id = ?1")
    List<InService> findInServiceByVaccineId(Long VaccineId);
    List<InService> findInServiceByMedicineId(Long MedicineId);
}
