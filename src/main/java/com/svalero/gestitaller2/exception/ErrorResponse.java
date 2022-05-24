package com.svalero.gestitaller2.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {

    private String internalError;
    private String message;
}
