package com.svalero.gestitaller2.exception;

public class SpareNotFoundException extends Exception {

    private static final String DEFAULT_ERROR_MESSAGE = "Recambio no encontrado";

    public SpareNotFoundException(String message) {
        super(message);
    }

    public SpareNotFoundException() {
        super(DEFAULT_ERROR_MESSAGE);
    }
}