package com.svalero.gestitaller2.exception;

public class OrdenNotFoundException extends Exception {

    private static final String DEFAULT_ERROR_MESSAGE = "Orden no encontrada";

    public OrdenNotFoundException(String message) {
        super(message);
    }

    public OrdenNotFoundException() {
        super(DEFAULT_ERROR_MESSAGE);
    }
}