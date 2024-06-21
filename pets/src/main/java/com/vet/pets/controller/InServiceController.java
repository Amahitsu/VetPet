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

import com.vet.pets.dto.InServiceDTO;
import com.vet.pets.entities.InService;
import com.vet.pets.service.InServiceServices;

@RestController
@RequestMapping("/api/v1/inservice")
public class InServiceController {
    
    @Autowired
    private InServiceServices inServiceServices;

    @PostMapping
    public ResponseEntity<ApiResponse> createInService(@RequestBody InServiceDTO dto) {;
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<InService>("Created", inServiceServices.createInService(dto)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<InService>>> listInService(@RequestParam("id_medicines") Optional<Long> id_medicine, @RequestParam("id_vaccines") Optional<Long> id_vaccine ) {
        List<InService> listInService = inServiceServices.listInService(id_medicine, id_vaccine);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<List<InService>>("Ok", listInService));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<InService>> findInService(@PathVariable Long id) {
        InService inService = inServiceServices.findInService(id);
        if (inService == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<InService>("Ok", inService));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteInService(@PathVariable Long id) {
        InService inService = inServiceServices.findInService(id);
        if (inService == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        inServiceServices.deleteInService(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Deleted"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateinService(@PathVariable Long id, @RequestBody InServiceDTO dto) {
        InService inService = inServiceServices.findInService(id);
        if (inService == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Atendimento não encontrado."));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<InService>("Updated", inServiceServices.updateInServiceById(id, dto)));
    }

}
