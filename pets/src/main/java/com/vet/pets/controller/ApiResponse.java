package com.vet.pets.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public class ApiResponse implements Serializable{
    private static final long serialVersionUID = 1L;
    private String message;
}
