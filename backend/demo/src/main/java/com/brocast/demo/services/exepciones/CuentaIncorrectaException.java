package com.brocast.demo.services.exepciones;

public class CuentaIncorrectaException extends RuntimeException {
    public CuentaIncorrectaException(String message) {
        super(message);
    }
}
