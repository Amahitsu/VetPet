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

import com.vet.pets.dto.MedicineDTO;
import com.vet.pets.entities.Medicines;
import com.vet.pets.service.MedicinesService;

@RestController
@RequestMapping("/api/v1/medicines")
public class MedicinesController {

    @Autowired
    private MedicinesService medicinesServices;

    @PostMapping
    public ResponseEntity<ApiResponse> createMedicine(@RequestBody MedicineDTO dto) {
        Optional<Medicines> medicine = medicinesServices.findMedicineByName(dto.name());
        if (medicine.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("Medicamento já cadastrado."));
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<Medicines>("Created", medicinesServices.createMedicine(dto)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Medicines>>> listMedicine() {
        List<Medicines> listMedicine = medicinesServices.listMedicines();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<List<Medicines>>("Ok", listMedicine));
    }

        @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Medicines>> findMedicine(@PathVariable Long id) {
        Medicines medicine = medicinesServices.findMedicine(id);
        if (medicine == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<Medicines>("Ok", medicine));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteMedicine(@PathVariable Long id) {
        Medicines medicine = medicinesServices.findMedicine(id);
        if (medicine == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        medicinesServices.deleteMedicine(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Deleted"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateMedicine(@PathVariable Long id, @RequestBody MedicineDTO dto) {
        Medicines medicine = medicinesServices.findMedicine(id);
        if (medicine == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Medicamento não encontrado."));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<Medicines>("Updated", medicinesServices.updateMedicineById(id, dto)));
    }
    
}
