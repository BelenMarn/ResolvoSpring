package com.grupob.resolvo.model.exception;

import org.springframework.context.MessageSource;
import org.springframework.http.*;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Locale;

@ControllerAdvice
public class ControllerAdvisory extends ResponseEntityExceptionHandler {

    @ExceptionHandler({NoClientUserFoundException.class, NoTechnicianFoundException.class,
            NoWorkerUserFoundException.class, NoIncidenceFoundException.class, EmptyClientList.class,
            EmptyIncidenceList.class, EmptyWorkerList.class})

    public ResponseEntity<ErrorResponse> handleUserNotFoundExceptions(Exception ex) {

        String errorMessage = ex instanceof NoClientUserFoundException ? "Client user not found" :
                (ex instanceof NoTechnicianFoundException ? "Technician not found" :
                        (ex instanceof NoWorkerUserFoundException ? "Worker user not found" :
                                (ex instanceof NoIncidenceFoundException ? "Incidence not found" :
                                        (ex instanceof EmptyClientList ? "Empty client list" :
                                                (ex instanceof EmptyIncidenceList ? "Empty incidence list" :
                                                        (ex instanceof EmptyWorkerList ? "Empty worker list" : ";"))))));

        ErrorResponse errorResponse = new ErrorResponse() {

            @Override
            public HttpStatus getStatusCode() {
                return HttpStatus.NOT_FOUND;
            }

            @Override
            public HttpHeaders getHeaders() {
                return ErrorResponse.super.getHeaders();
            }

            @Override
            public ProblemDetail getBody() {
                return null;
            }

            @Override
            public String getTypeMessageCode() {
                return ErrorResponse.super.getTypeMessageCode();
            }

            @Override
            public String getTitleMessageCode() {
                return ErrorResponse.super.getTitleMessageCode();
            }

            @Override
            public String getDetailMessageCode() {
                return ErrorResponse.super.getDetailMessageCode();
            }

            @Override
            public Object[] getDetailMessageArguments() {
                return ErrorResponse.super.getDetailMessageArguments();
            }

            @Override
            public Object[] getDetailMessageArguments(MessageSource messageSource, Locale locale) {
                return ErrorResponse.super.getDetailMessageArguments(messageSource, locale);
            }

            @Override
            public ProblemDetail updateAndGetBody(MessageSource messageSource, Locale locale) {
                return ErrorResponse.super.updateAndGetBody(messageSource, locale);
            }
        };

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
