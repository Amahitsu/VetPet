package com.vet.pets.dto;

import java.sql.Date;

public record AppointmentDTO(String start_time, String finish_time, Boolean medicines, Boolean vaccines, Date date, Long id_animals, Long id_workers, String observation) {
    
}
