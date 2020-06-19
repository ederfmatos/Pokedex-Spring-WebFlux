package com.ederfmatos.pokedex.controller;

import com.ederfmatos.pokedex.exception.config.PokedexException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public final class GlobalController {

    @ExceptionHandler(PokedexException.class)
    public final ResponseEntity<CustomExceptionBean> handleException(PokedexException ex) {
        logException(ex);
        CustomExceptionBean errorDetails = new CustomExceptionBean(ex.getMessage(), ex.getStatus());
        return new ResponseEntity<>(errorDetails, ex.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<CustomExceptionBean> handleInternalError(Exception ex) {
        logException(ex);
        CustomExceptionBean errorDetails = new CustomExceptionBean("Erro interno", HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private void logException(Exception exception) {
        log.error("Error", exception);
    }

    public static final class CustomExceptionBean {

        private final LocalDateTime timestamp;
        private final String error;
        private final int status;

        public CustomExceptionBean(String error, HttpStatus status) {
            this.error = error;
            this.status = status.value();
            this.timestamp = LocalDateTime.now();
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public String getError() {
            return error;
        }

        public int getStatus() {
            return status;
        }

    }

}
