package com.brocast.demo.services.excepciones;

public class ClaveIncorrectaException extends RuntimeException {
    public ClaveIncorrectaException(String mensaje) {
        super(mensaje);
    }
}