package com.ederfmatos.pokedex.exception;

import com.ederfmatos.pokedex.exception.config.PokedexException;
import org.springframework.http.HttpStatus;

public class NotFoundError extends PokedexException {

    public NotFoundError(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }

}
