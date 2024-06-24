package com.vet.pets.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vet.pets.entities.Appointments;

public interface AppointmentRepository extends JpaRepository<Appointments , Long>{
        @Query("SELECT a FROM Appointments a WHERE a.date = ?1")
    List<Appointments> findByDate(Date date);
    List<Appointments> deleteAppointmentsByAnimalId(Long id_appointments);
}
