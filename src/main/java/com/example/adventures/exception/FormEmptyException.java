package com.example.adventures.exception;

public class FormEmptyException extends Exception{
    public FormEmptyException(String missingField) {
        super("Please complete the field: \n" + missingField);
    }
}
