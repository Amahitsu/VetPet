package com.vet.pets.controller;

import com.vet.pets.dto.AnimalCreateDTO;
import com.vet.pets.dto.RaceCreateDTO;
import com.vet.pets.service.AnimalService;
import com.vet.pets.service.RaceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/animal")
public class AnimalController {
    @Autowired
    private AnimalService animalServices;

    @PostMapping
    public ResponseEntity<ApiResponse> createAnimal(@RequestBody AnimalCreateDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(animalServices.createAnimal(dto));
    }
}
