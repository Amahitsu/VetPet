package com.vet.pets.controller;

import com.vet.pets.dto.CreateSpecieDTO;
import com.vet.pets.service.SpeciesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/species")
public class SpeciesController{

    @Autowired
    private SpeciesServices speciesServices;

    @PostMapping
    public ResponseEntity<ApiResponse> createSpecie(@RequestBody CreateSpecieDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(speciesServices.createSpecie(dto));
    }
}
