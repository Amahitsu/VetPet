package com.vet.pets.dto;

import java.math.BigInteger;

public record CustomerCreateDTO(String name, String cpf, String phone, String email, String address) {
}
