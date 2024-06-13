package com.vet.pets.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vet.pets.dto.AnimalDTO;
import com.vet.pets.entities.Animals;
import com.vet.pets.entities.Breeds;
import com.vet.pets.entities.Customer;
import com.vet.pets.repository.AnimalRepository;
import com.vet.pets.repository.CustomerRepository;
import com.vet.pets.repository.RaceRepository;

import jakarta.transaction.Transactional;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;
    @Autowired
    private RaceRepository raceRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    public Animals createAnimal(AnimalDTO dto) {
        try {
            Breeds breeds = raceRepository.findById(dto.id_breeds())
                    .orElseThrow(() -> new RuntimeException("Raça não encontrada com o ID: " + dto.id_breeds()));
            Customer customer = customerRepository.findById(dto.id_customers())
                    .orElseThrow(() -> new RuntimeException("Cliente não encontrado com o ID: " + dto.id_customers()));

            Animals newAnimal = new Animals();
            newAnimal.setName(dto.name());
            newAnimal.setGender(dto.gender());
            newAnimal.setDateOfBirth(dto.dateOfBirth());
            newAnimal.setWeight(dto.weight());
            newAnimal.setRace(breeds);
            newAnimal.setCustomer(customer);

            Animals savedAnimal = animalRepository.save(newAnimal);

            return savedAnimal;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Optional<Animals> findAnimalByName(String name) {
        try {
            Optional<Animals> animal = animalRepository.findByName(name);
            return animal;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<Animals> listAnimal() {
        try {
            List<Animals> animal = animalRepository.findAll();
            return animal;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Animals findAnimal(Long id) {
        try {
            Optional<Animals> animal = animalRepository.findById(id);
            if (animal.isEmpty()) {
                return null;
            }

            return animal.get();

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void deleteAnimal(Long id) {
        try {
            animalRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    public Animals updateAnimalById(Long id, AnimalDTO dto) {
        try {
            Optional<Animals> animal = animalRepository.findById(id);
            if (animal.isEmpty()) {
                return null;
            }

            Animals existingAnimal = animal.get();

            if (dto.name() != null) {
                existingAnimal.setName(dto.name());
            }
            if (dto.gender() != null) {
                existingAnimal.setGender(dto.gender());
            }
            if (dto.dateOfBirth() != null) {
                existingAnimal.setDateOfBirth(dto.dateOfBirth());
            }
            if (dto.weight() != null) {
                existingAnimal.setWeight(dto.weight());
            }
            if (dto.id_breeds() != null) {
                Breeds races = raceRepository.findById(dto.id_breeds())
                        .orElseThrow(() -> new RuntimeException("Raça não encontrada com o ID: " + dto.id_breeds()));
                existingAnimal.setRace(races);
            }
            if (dto.id_customers() != null) {
                Customer customer = customerRepository.findById(dto.id_customers())
                        .orElseThrow(() -> new RuntimeException("Cliente não encontrado com o ID: " + dto.id_customers()));
                existingAnimal.setCustomer(customer);
            }

            Animals updatedAnimal = animalRepository.save(existingAnimal);

            return updatedAnimal;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
