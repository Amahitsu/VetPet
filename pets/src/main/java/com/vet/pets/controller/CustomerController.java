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

import com.vet.pets.dto.CustomerCreateDTO;
import com.vet.pets.entities.Customer;
import com.vet.pets.service.CustomerServices;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    @Autowired
    private CustomerServices customerServices;

    @PostMapping
    public ResponseEntity<ApiResponse> createCustomer(@RequestBody CustomerCreateDTO dto) {
        Optional<Customer> customer = customerServices.findCustomerByName(dto.name());
        if (customer.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("Cliente já cadastrado."));
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<Customer>("Created", customerServices.createCustomer(dto)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Customer>>> listCustomer() {
        List<Customer> listCustomer = customerServices.listCustomer();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<List<Customer>>("Ok", listCustomer));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Customer>> findCustomer(@PathVariable Long id) {
        Customer customer = customerServices.findCustomer(id);
        if (customer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<Customer>("Ok", customer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCustomer(@PathVariable Long id) {
        Customer customer = customerServices.findCustomer(id);
        if (customer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        customerServices.deleteCustomer(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Deleted"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateCustomer(@PathVariable Long id, @RequestBody CustomerCreateDTO dto) {
        Customer customer = customerServices.findCustomer(id);
        if (customer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Cliente não encontrado."));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<Customer>("Updated", customerServices.updateCustomerById(id, dto)));
    }

}
