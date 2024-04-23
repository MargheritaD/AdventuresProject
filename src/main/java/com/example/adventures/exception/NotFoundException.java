package com.example.adventures.exception;

public class NotFoundException extends Exception{
    public NotFoundException(String message){
        super("Error not found: \n" + message);
    }
}
