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

import com.vet.pets.dto.ServiceDTO;
import com.vet.pets.entities.Services;
import com.vet.pets.service.ServiceServices;

@RestController
@RequestMapping("/api/v1/services")
public class ServiceController {

    @Autowired
    private ServiceServices serviceServices;

    @PostMapping
    public ResponseEntity<ApiResponse> createService(@RequestBody ServiceDTO dto) {
        Optional<Services> service = serviceServices.findServiceByName(dto.name());
        if (service.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("Serviço já cadastrado."));
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<Services>("Created", serviceServices.createService(dto)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Services>>> listService() {
        List<Services> listService = serviceServices.listService();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<List<Services>>("Ok", listService));
    }

        @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Services>> findService(@PathVariable Long id) {
        Services service = serviceServices.findService(id);
        if (service == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<Services>("Ok", service));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteService(@PathVariable Long id) {
        Services service = serviceServices.findService(id);
        if (service == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        serviceServices.deleteService(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Deleted"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateService(@PathVariable Long id, @RequestBody ServiceDTO dto) {
        Services service = serviceServices.findService(id);
        if (service == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Serviço não encontrado."));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<Services>("Updated", serviceServices.updateServiceById(id, dto)));
    }
    
}
