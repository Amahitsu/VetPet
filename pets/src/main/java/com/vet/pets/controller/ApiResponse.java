package com.vet.pets.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

// @AllArgsConstructor
@Getter
public class ApiResponse<T> implements Serializable{
    private static final long serialVersionUID = 1L;
    private String message;
    private T data;

    public ApiResponse(String message) {
        this.message = message;
    }

    public ApiResponse(String message, T data) {
        this.message = message;
        this.data = data;
    }
}
