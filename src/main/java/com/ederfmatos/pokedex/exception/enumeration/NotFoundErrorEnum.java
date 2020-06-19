package com.ederfmatos.pokedex.exception.enumeration;

import com.ederfmatos.pokedex.exception.NotFoundError;
import com.ederfmatos.pokedex.exception.config.ExceptionCaller;

public enum NotFoundErrorEnum implements ExceptionCaller<NotFoundErrorEnum> {

    POKEMON_NAO_ENCONTRADO("Pokemon não encontrado"),
    ;

    private final String message;

    NotFoundErrorEnum(final String message) {
        this.message = message;
    }

    @Override
    public Throwable getException() {
        return new NotFoundError(message);
    }

}
