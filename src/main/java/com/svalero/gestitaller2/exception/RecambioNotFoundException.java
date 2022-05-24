package com.svalero.gestitaller2.exception;

public class RecambioNotFoundException extends Exception {

    private static final String DEFAULT_ERROR_MESSAGE = "Recambio no encontrado";

    public RecambioNotFoundException(String message) {
        super(message);
    }

    public RecambioNotFoundException() {
        super(DEFAULT_ERROR_MESSAGE);
    }
}