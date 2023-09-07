package it.busz.empik.web.exception;

public class BadRequestException extends RestException {
    public BadRequestException(String message, String messageCode) {
        super(message, messageCode);
    }
}