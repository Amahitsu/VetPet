package com.vet.pets.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vet.pets.dto.CustomerCreateDTO;
import com.vet.pets.entities.Customer;
import com.vet.pets.repository.AnimalRepository;
import com.vet.pets.repository.AppointmentRepository;
import com.vet.pets.repository.CustomerRepository;

import jakarta.transaction.Transactional;

@Service
public class CustomerServices{
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AnimalRepository animalRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Transactional
    public Customer createCustomer(CustomerCreateDTO dto){
        try{
            Customer newCustomer = customerRepository.save(new Customer(null, dto.name(), dto.cpf(), dto.phone(), dto.email(), dto.address(), true));
            return newCustomer;
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public  Optional<Customer> findCustomerByName(String name){
        try{
            Optional<Customer> customer = customerRepository.findByName(name);
            return customer;

        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<Customer> listCustomer(){
        try{
            List<Customer> customer = customerRepository.findAll();
            return customer;
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public Customer findCustomer(Long id){
        try{
            Optional<Customer> customer = customerRepository.findById(id);
            if(customer.isEmpty()) {
                return null;
            }

            return customer.get();

        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    public void deleteCustomer(Long id){
        try{
            appointmentRepository.deleteAppointmentsByAnimalId(id);
            animalRepository.deleteAnimalsByCustomerId(id);
            customerRepository.deleteById(id);
         } catch (Exception e){
             throw new RuntimeException(e.getMessage());
         }
    }

    public Customer updateCustomerById(Long id, CustomerCreateDTO dto){
        try{
            Optional<Customer> customer = customerRepository.findById(id);
            if(customer.isEmpty()) {
                return null;
            }
            Customer newCustomer = customerRepository.save(new Customer(id, dto.name(), dto.cpf(), dto.phone(), dto.email(), dto.address(), true));
            return newCustomer;
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
