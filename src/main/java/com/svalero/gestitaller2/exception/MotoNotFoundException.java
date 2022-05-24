package com.svalero.gestitaller2.exception;

public class MotoNotFoundException extends Exception {

    private static final String DEFAULT_ERROR_MESSAGE = "Moto no encontrado";

    public MotoNotFoundException(String message) {
        super(message);
    }

    public MotoNotFoundException() {
        super(DEFAULT_ERROR_MESSAGE);
    }
}