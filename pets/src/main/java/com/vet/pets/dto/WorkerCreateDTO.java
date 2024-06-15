package com.vet.pets.dto;

public record WorkerCreateDTO(String name,String cpf, String phone,
    String address, String functionn, String username, String passwordd, int userLevel, String email, boolean active) {
}
