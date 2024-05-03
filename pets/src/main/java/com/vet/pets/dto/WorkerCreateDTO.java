package com.vet.pets.dto;

public record WorkerCreateDTO(String name,String cpf, String phone,
                              String address, String function, String username, String password, int userLevel, boolean active) {
}
