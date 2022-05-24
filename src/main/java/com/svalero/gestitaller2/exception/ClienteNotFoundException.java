package com.svalero.gestitaller2.exception;

public class ClienteNotFoundException extends Exception {

    private static final String DEFAULT_ERROR_MESSAGE = "Cliente no encontrado";

    public ClienteNotFoundException(String message) {
        super(message);
    }

    public ClienteNotFoundException() {
        super(DEFAULT_ERROR_MESSAGE);
    }
}