package com.vet.pets.dto;

import com.vet.pets.entities.Species;

public record RaceCreateDTO(String name, Species specie) {
}
