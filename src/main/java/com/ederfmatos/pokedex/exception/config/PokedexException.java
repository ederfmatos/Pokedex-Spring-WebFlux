package com.ederfmatos.pokedex.exception.config;

import org.springframework.http.HttpStatus;

public abstract class PokedexException extends RuntimeException {

    private final HttpStatus status;

    public PokedexException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

}
