package com.brocast.demo.services.exepciones;

public class ClaveIncorrectaException extends RuntimeException {
    public ClaveIncorrectaException(String mensaje) {
        super(mensaje);
    }
}