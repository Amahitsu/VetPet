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

import com.vet.pets.dto.WorkerCreateDTO;
import com.vet.pets.entities.Worker;
import com.vet.pets.service.WorkerServices;
 
@RestController
@RequestMapping("/api/v1/worker")
public class WorkerController {
 
    @Autowired
    private WorkerServices workerServices;
 
    @PostMapping
    public ResponseEntity<ApiResponse> createWorker(@RequestBody WorkerCreateDTO dto) {
        Optional<Worker> worker = workerServices.findWorkerByName(dto.name());
        if (worker.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("Funcionário já cadastrado."));
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<Worker>("Created", workerServices.createWorker(dto)));
    }
 
    @GetMapping
    public ResponseEntity<ApiResponse<List<Worker>>> listWorkers() {
        List<Worker> listWorkers = workerServices.listWorkers();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<List<Worker>>("Ok", listWorkers));
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Worker>> findWorker(@PathVariable Long id) {
        Worker worker = workerServices.findWorker(id);
        if (worker == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<Worker>("Ok", worker));
    }
 
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteWorker(@PathVariable Long id) {
        Worker worker = workerServices.findWorker(id);
        if (worker == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        workerServices.deleteWorker(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Deleted"));
    }
 
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateSpecie(@PathVariable Long id, @RequestBody WorkerCreateDTO dto) {
        Worker worker = workerServices.findWorker(id);
        if (worker == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Espécie não encontrada."));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<Worker>("Updated", workerServices.updateWorkerById(id, dto)));
    }
}