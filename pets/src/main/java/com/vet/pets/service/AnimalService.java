package com.vet.pets.service;

import com.vet.pets.controller.ApiResponse;
import com.vet.pets.dto.AnimalCreateDTO;
import com.vet.pets.dto.CustomerCreateDTO;
import com.vet.pets.entities.Animal;
import com.vet.pets.repository.AnimalRepository;
import com.vet.pets.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimalService {
    @Autowired
    private AnimalRepository animalRepository;

    @Transactional
    public ApiResponse createAnimal(AnimalCreateDTO dto){
        try{
            animalRepository.save(new Animal(null, dto.name(),dto.birthDate(),dto.weight(),dto.customer(),dto.race()));
            return new ApiResponse("Created!");
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
