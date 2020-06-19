package com.ederfmatos.pokedex.exception.config;

public interface ExceptionCaller<E extends Enum<E>> {

    Throwable getException();

}
