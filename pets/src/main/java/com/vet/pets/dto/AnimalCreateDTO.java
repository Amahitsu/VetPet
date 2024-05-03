package com.vet.pets.dto;

import com.vet.pets.entities.Customer;
import com.vet.pets.entities.Race;

import java.time.LocalDate;

public record AnimalCreateDTO(String name, LocalDate birthDate, float weight, Customer customer, Race race){
}
