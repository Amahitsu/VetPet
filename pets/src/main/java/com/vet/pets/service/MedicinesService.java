package com.vet.pets.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vet.pets.dto.MedicineDTO;
import com.vet.pets.entities.Medicines;
import com.vet.pets.repository.MedicinesRepository;

import jakarta.transaction.Transactional;

@Service
public class MedicinesService {

    @Autowired
    private MedicinesRepository medicinesRepository;

    public List<Medicines> listMedicines() {
        try {
            List<Medicines> medicine = medicinesRepository.findAll();
            return medicine;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Medicines findMedicine(Long id) {
        try {
            Optional<Medicines> medicine = medicinesRepository.findById(id);
            if (medicine.isEmpty()) {
                return null;
            }

            return medicine.get();

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Optional<Medicines> findMedicineByName(String name) {
        try {
            Optional<Medicines> medicine = medicinesRepository.findByName(name);
            return medicine;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    public Medicines createMedicine(MedicineDTO dto) {
        try {
            Medicines newMedicine = medicinesRepository.save(new Medicines(null, dto.name(), dto.price()));
            return newMedicine;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void deleteMedicine(Long id) {
        try {
            medicinesRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Medicines updateMedicineById(Long id, MedicineDTO dto) {
        try {
            Optional<Medicines> medicine = medicinesRepository.findById(id);
            if (medicine.isEmpty()) {
                return null;
            }
            Medicines newMedicine = medicinesRepository.save(new Medicines(id, dto.name(), dto.price()));
            return newMedicine;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
