package com.vet.pets.service;

import com.vet.pets.controller.ApiResponse;
import com.vet.pets.dto.CreateSpecieDTO;
import com.vet.pets.entities.Species;
import com.vet.pets.repository.SpeciesRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpeciesServices {

    @Autowired
    private SpeciesRepository speciesRepository;

    @Transactional
    public ApiResponse createSpecie(CreateSpecieDTO dto){
        try{
            speciesRepository.save(new Species(null, dto.name()));
            return new ApiResponse("Created!");
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
