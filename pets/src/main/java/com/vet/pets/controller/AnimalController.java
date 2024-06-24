package com.vet.pets.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vet.pets.dto.AnimalDTO;
import com.vet.pets.entities.Animals;
import com.vet.pets.service.AnimalService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/animals")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @PostMapping
    public ResponseEntity<ApiResponse> createAnimal(@RequestBody AnimalDTO dto) {
        Optional<Animals> animal = animalService.findAnimalByName(dto.name());
        if (animal.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("Animal já cadastrado."));
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<Animals>("Created", animalService.createAnimal(dto)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Animals>>> listAnimals(HttpServletRequest request, @RequestParam(value = "customerId", required = false) Long customerId) {
        List<Animals> listAnimal;

        if (customerId != null) {
            listAnimal = animalService.listAnimalByCustomer(customerId);
        } else {
            listAnimal = animalService.listAnimal();
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("Ok", listAnimal));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Animals>> findAnimal(@PathVariable Long id) {
        Animals animal = animalService.findAnimal(id);
        if (animal == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<Animals>("Ok", animal));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteAnimal(@PathVariable Long id) {
        Animals animal = animalService.findAnimal(id);
        if (animal == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        animalService.deleteAnimal(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Deleted"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateAnimal(@PathVariable Long id, @RequestBody AnimalDTO dto) {
        Animals animal = animalService.findAnimal(id);
        if (animal == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Animal não encontrado."));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<Animals>("Updated", animalService.updateAnimalById(id, dto)));
    }

}
