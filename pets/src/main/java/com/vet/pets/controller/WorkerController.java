package com.vet.pets.controller;

import com.vet.pets.dto.CustomerCreateDTO;
import com.vet.pets.dto.WorkerCreateDTO;
import com.vet.pets.service.SpeciesServices;
import com.vet.pets.service.WorkerServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/worker")
public class WorkerController {

    private WorkerServices workerServices;

    @PostMapping
    public ResponseEntity<ApiResponse> createWorker(@RequestBody WorkerCreateDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(workerServices.createWorker(dto));
    }
}
