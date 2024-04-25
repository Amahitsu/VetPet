package com.vet.pets.controller;

import com.vet.pets.dto.CustomerCreateDTO;
import com.vet.pets.service.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController{

    @Autowired
    private CustomerServices customerServices;
    @PostMapping
    public ResponseEntity<ApiResponse> createCustomer(@RequestBody CustomerCreateDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(customerServices.createCustomer(dto));
    }

}
