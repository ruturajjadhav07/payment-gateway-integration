package com.example.securepay.exception;

public class DuplicateResourceFound extends RuntimeException {

    // Handle Duplicate Resource
    public DuplicateResourceFound(String message) {
        super(message);
    }
}
