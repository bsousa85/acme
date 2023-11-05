package com.isep.acme.repositories.user;

public class ExistingNifException extends RuntimeException {
    public ExistingNifException(String message) {
        super(message);
    }
}
