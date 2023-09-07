package it.busz.empik.web;

import java.util.List;

record ErrorResponse(String traceId,
                     String spanId,
                     List<ErrorResponseElement> errors) {

}
