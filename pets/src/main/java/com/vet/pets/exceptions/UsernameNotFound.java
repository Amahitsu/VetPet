package com.vet.pets.exceptions;



public class UsernameNotFound extends RuntimeException{
    public UsernameNotFound(String message){
        super(message);
    }
}
