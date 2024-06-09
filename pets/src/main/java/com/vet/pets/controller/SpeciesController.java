package com.vet.pets.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vet.pets.dto.SpecieDTO;
import com.vet.pets.entities.Species;
import com.vet.pets.service.SpeciesServices;

@RestController
@RequestMapping("/api/v1/species")
public class SpeciesController {

    @Autowired
    private SpeciesServices speciesServices;

    @PostMapping
    public ResponseEntity<ApiResponse> createSpecie(@RequestBody SpecieDTO dto) {
        Optional<Species> specie = speciesServices.findSpecieByName(dto.name());
        if (specie.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("Espécie já cadastrada."));
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<Species>("Created", speciesServices.createSpecie(dto)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Species>>> listSpecies() {
        List<Species> listSpecies = speciesServices.listSpecies();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<List<Species>>("Ok", listSpecies));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Species>> findSpecie(@PathVariable Long id) {
        Species specie = speciesServices.findSpecie(id);
        if (specie == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<Species>("Ok", specie));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteSpecie(@PathVariable Long id) {
        Species specie = speciesServices.findSpecie(id);
        if (specie == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        speciesServices.deleteSpecie(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Deleted"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateSpecie(@PathVariable Long id, @RequestBody SpecieDTO dto) {
        Species specie = speciesServices.findSpecie(id);
        if (specie == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Espécie não encontrada."));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<Species>("Updated", speciesServices.updateSpecieById(id, dto)));
    }

}
