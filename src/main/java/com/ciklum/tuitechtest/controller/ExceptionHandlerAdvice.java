package com.ciklum.tuitechtest.controller;

import com.ciklum.tuitechtest.exception.FetchingRepositoryException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
@RequestMapping(produces = "application/vnd.error+json")
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(FetchingRepositoryException.class)
    public ResponseEntity<ErrorResponse> notFoundException(final FetchingRepositoryException e) {
        return error(NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> assertionException(final IllegalArgumentException e) {
        return error(NOT_FOUND, e.getLocalizedMessage());
    }

    @Override
    @Nullable
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return ResponseEntity.status(NOT_ACCEPTABLE).contentType(MediaType.APPLICATION_JSON).body(new ErrorResponse(NOT_ACCEPTABLE.value(), ex.getMessage()));
    }

    private ResponseEntity<ErrorResponse> error(final HttpStatus httpStatus, String message) {
        return ResponseEntity.status(httpStatus.value()).body(new ErrorResponse(httpStatus.value(), message));
    }
}
