package com.svalero.gestitaller2.exception;

public class MecanicoNotFoundException extends Exception {

    private static final String DEFAULT_ERROR_MESSAGE = "Mecanico no encontrado";

    public MecanicoNotFoundException(String message) {
        super(message);
    }

    public MecanicoNotFoundException() {
        super(DEFAULT_ERROR_MESSAGE);
    }
}