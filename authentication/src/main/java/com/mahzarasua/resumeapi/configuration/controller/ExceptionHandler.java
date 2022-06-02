package com.mahzarasua.resumeapi.configuration.controller;

import com.mahzarasua.resumeapi.configuration.exception.CustomNotFoundException;
import com.mahzarasua.resumeapi.configuration.exception.ExceptionBody;
import com.mahzarasua.resumeapi.configuration.exception.MissingRequiredFieldException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {
    protected static final String URI_LABEL = "uri=";

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {CustomNotFoundException.class})
    protected ResponseEntity<Object> handleNotFound(
            CustomNotFoundException exception, WebRequest request) {
        ExceptionBody body = new ExceptionBody();
        body.setTimestamp(LocalDateTime.now());
        body.setStatusCode(HttpStatus.NOT_FOUND.value());
        body.setError(HttpStatus.NOT_FOUND.getReasonPhrase());
        body.setMessage(exception.getMessage());
        body.setPath(request.getDescription(false).replace(URI_LABEL, ""));
        return handleExceptionInternal(
                exception, body, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {MissingRequiredFieldException.class})
    protected ResponseEntity<Object> handleMissingRequiredField(
            MissingRequiredFieldException exception, WebRequest request) {
        ExceptionBody body = new ExceptionBody();
        body.setTimestamp(LocalDateTime.now());
        body.setStatusCode(HttpStatus.BAD_REQUEST.value());
        body.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        body.setMessage(exception.getMessage());
        body.setPath(request.getDescription(false).replace(URI_LABEL, ""));
        body.setDetails(exception.getErrorDetails());
        return handleExceptionInternal(
                exception, body, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
