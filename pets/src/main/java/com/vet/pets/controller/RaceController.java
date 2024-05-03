package com.vet.pets.controller;

import com.vet.pets.dto.CreateSpecieDTO;
import com.vet.pets.dto.RaceCreateDTO;
import com.vet.pets.service.RaceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/races")
public class RaceController {
    @Autowired
    private RaceServices raceServices;

    @PostMapping
    public ResponseEntity<ApiResponse> createRace(@RequestBody RaceCreateDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(raceServices.createRace(dto));
    }
}
