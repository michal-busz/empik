package it.busz.empik.web.exception;

import lombok.Getter;

public abstract class RestException extends RuntimeException {
    @Getter
    private final String messageCode;

    RestException(String message, String messageCode) {
        super(message);
        this.messageCode = messageCode;
    }
}
