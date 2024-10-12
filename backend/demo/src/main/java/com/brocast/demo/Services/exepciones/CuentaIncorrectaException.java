package com.brocast.demo.Services.exepciones;

public class CuentaIncorrectaException extends RuntimeException {
    public CuentaIncorrectaException(String message) {
        super(message);
    }
}
