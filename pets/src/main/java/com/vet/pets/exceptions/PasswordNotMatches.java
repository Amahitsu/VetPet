package com.vet.pets.exceptions;


public class PasswordNotMatches extends RuntimeException {
    public PasswordNotMatches(String message){
        super(message);
    }
}
