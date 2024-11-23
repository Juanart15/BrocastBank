package com.brocast.demo.services.excepciones;

public class CuentaIncorrectaException extends RuntimeException {
    public CuentaIncorrectaException(String message) {
        super(message);
    }
}
