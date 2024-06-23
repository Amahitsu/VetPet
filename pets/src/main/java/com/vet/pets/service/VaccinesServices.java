package com.vet.pets.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vet.pets.dto.VaccineDTO;
import com.vet.pets.entities.Vaccines;
import com.vet.pets.repository.VaccinesRepository;

import jakarta.transaction.Transactional;

@Service
public class VaccinesServices {

    @Autowired
    private VaccinesRepository vaccinesRepository;

    public List<Vaccines> listVaccines() {
        try {
            List<Vaccines> vaccine = vaccinesRepository.findAll();
            return vaccine;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Vaccines findVaccine(Long id) {
        try {
            Optional<Vaccines> vaccine = vaccinesRepository.findById(id);
            if (vaccine.isEmpty()) {
                return null;
            }

            return vaccine.get();

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Optional<Vaccines> findVaccineByName(String name) {
        try {
            Optional<Vaccines> vaccine = vaccinesRepository.findByName(name);
            return vaccine;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    public Vaccines createVaccine(VaccineDTO dto) {
        try {
            Vaccines newVaccine = vaccinesRepository.save(new Vaccines(null, dto.name(), dto.price()));
            return newVaccine;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void deleteVaccine(Long id) {
        try {
            vaccinesRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Vaccines updateVaccineById(Long id, VaccineDTO dto) {
        try {
            Optional<Vaccines> vaccine = vaccinesRepository.findById(id);
            if (vaccine.isEmpty()) {
                return null;
            }
            Vaccines newVaccine = vaccinesRepository.save(new Vaccines(id, dto.name(), dto.price()));
            return newVaccine;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
