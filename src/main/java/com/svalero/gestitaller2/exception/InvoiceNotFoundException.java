package com.svalero.gestitaller2.exception;

public class InvoiceNotFoundException extends Exception {

    private static final String DEFAULT_ERROR_MESSAGE = "Factura no encontrada";

    public InvoiceNotFoundException(String message) {
        super(message);
    }

    public InvoiceNotFoundException() {
        super(DEFAULT_ERROR_MESSAGE);
    }
}