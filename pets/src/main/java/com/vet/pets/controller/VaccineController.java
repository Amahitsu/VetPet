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

import com.vet.pets.dto.VaccineDTO;
import com.vet.pets.entities.Vaccines;
import com.vet.pets.service.VaccinesServices;

@RestController
@RequestMapping("/api/v1/vaccines")
public class VaccineController {

    @Autowired
    private VaccinesServices vaccinesServices;

    @PostMapping
    public ResponseEntity<ApiResponse> createVaccine(@RequestBody VaccineDTO dto) {
        Optional<Vaccines> vaccine = vaccinesServices.findVaccineByName(dto.name());
        if (vaccine.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("Vacina já cadastrado."));
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<Vaccines>("Created", vaccinesServices.createVaccine(dto)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Vaccines>>> listVaccines() {
        List<Vaccines> listVaccines = vaccinesServices.listVaccines();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<List<Vaccines>>("Ok", listVaccines));
    }

        @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Vaccines>> findVaccine(@PathVariable Long id) {
        Vaccines vaccine = vaccinesServices.findVaccine(id);
        if (vaccine == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<Vaccines>("Ok", vaccine));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteVaccine(@PathVariable Long id) {
        Vaccines vaccine = vaccinesServices.findVaccine(id);
        if (vaccine == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        vaccinesServices.deleteVaccine(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Deleted"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateVaccine(@PathVariable Long id, @RequestBody VaccineDTO dto) {
        Vaccines vaccine = vaccinesServices.findVaccine(id);
        if (vaccine == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Vacina não encontrado."));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<Vaccines>("Updated", vaccinesServices.updateVaccineById(id, dto)));
    }
    
}
