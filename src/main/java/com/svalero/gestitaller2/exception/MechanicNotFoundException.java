package com.svalero.gestitaller2.exception;

public class MechanicNotFoundException extends Exception {

    private static final String DEFAULT_ERROR_MESSAGE = "Mecanico no encontrado";

    public MechanicNotFoundException(String message) {
        super(message);
    }

    public MechanicNotFoundException() {
        super(DEFAULT_ERROR_MESSAGE);
    }
}