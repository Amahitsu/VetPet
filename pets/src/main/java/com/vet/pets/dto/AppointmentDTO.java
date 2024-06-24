package com.vet.pets.dto;

import java.sql.Date;

public record AppointmentDTO(String start_time, String finish_time, Date date, Long id_customer, Long id_animals, Long id_workers, Long id_services, String observation) {
    
}
