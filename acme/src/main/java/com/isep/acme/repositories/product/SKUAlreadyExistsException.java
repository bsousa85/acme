package com.isep.acme.repositories.product;

public class SKUAlreadyExistsException extends RuntimeException{
    public SKUAlreadyExistsException(String message) {
        super(message);
    }
}
