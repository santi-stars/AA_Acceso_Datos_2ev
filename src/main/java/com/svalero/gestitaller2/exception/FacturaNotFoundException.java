package com.svalero.gestitaller2.exception;

public class FacturaNotFoundException extends Exception {

    private static final String DEFAULT_ERROR_MESSAGE = "Factura no encontrada";

    public FacturaNotFoundException(String message) {
        super(message);
    }

    public FacturaNotFoundException() {
        super(DEFAULT_ERROR_MESSAGE);
    }
}