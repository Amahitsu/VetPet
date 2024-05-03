package com.vet.pets.service;

import com.vet.pets.controller.ApiResponse;
import com.vet.pets.dto.CreateSpecieDTO;
import com.vet.pets.dto.RaceCreateDTO;
import com.vet.pets.entities.Race;
import com.vet.pets.entities.Species;
import com.vet.pets.repository.RaceRepository;
import com.vet.pets.repository.SpeciesRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RaceServices {
    @Autowired
    private RaceRepository raceRepository;

    @Transactional
    public ApiResponse createRace(RaceCreateDTO dto){
        try{
            raceRepository.save(new Race(null, dto.name(),dto.specie()));
            return new ApiResponse("Created!");
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
