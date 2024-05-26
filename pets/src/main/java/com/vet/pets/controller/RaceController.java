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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vet.pets.dto.RaceDTO;
import com.vet.pets.entities.Breeds;
import com.vet.pets.service.RaceServices;

@RestController
@RequestMapping("/api/v1/breeds")
public class RaceController {
    
    @Autowired
    private RaceServices raceService;

    @PostMapping
    public ResponseEntity<ApiResponse> createBreed(@RequestBody RaceDTO dto) {
        Optional<Breeds> breed = raceService.findBreedByName(dto.name());
        if (breed.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("Raça já cadastrada."));
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<Breeds>("Created", raceService.createBreed(dto)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Breeds>>> listBreed(@RequestParam("id_specie") Optional<Long> id_specie) {
        List<Breeds> listBreed = raceService.listBreed(id_specie);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<List<Breeds>>("Ok", listBreed));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Breeds>> findBreed(@PathVariable Long id) {
        Breeds breed = raceService.findBreed(id);
        if (breed == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<Breeds>("Ok", breed));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteBreed(@PathVariable Long id) {
        Breeds breed = raceService.findBreed(id);
        if (breed == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        raceService.deleteBreed(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Deleted"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateBreed(@PathVariable Long id, @RequestBody RaceDTO dto) {
        Breeds breed = raceService.findBreed(id);
        if (breed == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Raça não encontrada."));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<Breeds>("Updated", raceService.updateBreedById(id, dto)));
    }

}
