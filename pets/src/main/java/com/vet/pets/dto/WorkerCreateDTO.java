package com.vet.pets.dto;

public record WorkerCreateDTO(String name,String cpf, String phone,String email,
                              String address, String function, int userLevel, boolean active) {
}
