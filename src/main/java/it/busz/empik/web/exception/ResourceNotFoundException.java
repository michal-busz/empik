package it.busz.empik.web.exception;

public class ResourceNotFoundException extends RestException {

    private static final String DEFAULT_MESSAGE = "Resource not found.";

    public ResourceNotFoundException(String messageCode) {
        super(DEFAULT_MESSAGE, messageCode);
    }

}