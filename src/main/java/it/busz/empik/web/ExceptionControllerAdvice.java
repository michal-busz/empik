package it.busz.empik.web;

import io.micrometer.tracing.Tracer;
import it.busz.empik.web.exception.BadRequestException;
import it.busz.empik.web.exception.ResourceNotFoundException;
import it.busz.empik.web.exception.RestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

import static it.busz.empik.web.GlobalErrorMessageCodes.RESOURCE_NOT_FOUND;
import static it.busz.empik.web.GlobalErrorMessageCodes.UNKNOWN_ERROR;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
class ExceptionControllerAdvice {
    private static final String UNKNOWN_ERROR_MESSAGE = "Unknown error";
    private final Tracer tracer;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public @ResponseBody ErrorResponse handleBadRequestException(BadRequestException ex) {
        return buildResponse(ex);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public @ResponseBody ErrorResponse handleNotFoundException() {
        final var exception = new ResourceNotFoundException(RESOURCE_NOT_FOUND);
        return buildResponse(exception);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public @ResponseBody ErrorResponse handleUnknownException(Exception e) {
        final var errorResponseElement = new ErrorResponseElement(UNKNOWN_ERROR_MESSAGE,
                UNKNOWN_ERROR);
        log.error("Unhandled exception occurred: {}", e.getMessage(), e);
        return buildResponseWithError(errorResponseElement);
    }

    private ErrorResponse buildResponse(RestException ex) {
        final var errorElement = buildErrorElement(ex.getMessage(), ex.getMessageCode());
        return buildResponseWithError(errorElement);
    }

    private ErrorResponseElement buildErrorElement(String message, String messageCode) {
        return new ErrorResponseElement(message, messageCode);
    }

    private ErrorResponse buildResponseWithErrors(
            final List<ErrorResponseElement> errors) {
        final var traceContext = TraceUtils.getTraceContext(tracer);
        return new ErrorResponse(
                traceContext.traceId(),
                traceContext.spanId(),
                errors
        );
    }

    private ErrorResponse buildResponseWithError(
            final ErrorResponseElement errorResponseElement) {
        return buildResponseWithErrors(List.of(errorResponseElement));
    }

}