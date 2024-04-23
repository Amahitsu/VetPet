package com.vet.pets.service;

import com.vet.pets.controller.ApiResponse;
import com.vet.pets.dto.CustomerCreateDTO;
import com.vet.pets.entities.Customer;
import com.vet.pets.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServices{
    @Autowired
    private CustomerRepository customerRepository;

    public ApiResponse createCustomer(CustomerCreateDTO dto){
        try{
            customerRepository.save(new Customer(null, dto.name(), dto.cpf(), dto.phone(), dto.email()));
            return new ApiResponse("Created!");
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
