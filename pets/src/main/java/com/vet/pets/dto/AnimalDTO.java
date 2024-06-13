package com.vet.pets.dto;

import java.sql.Date;

public record AnimalDTO(String name, String gender, Date dateOfBirth, Float weight, Long id_customers, Long id_races) {
    
}
