package com.jjbeto.microservice.iplocator.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<String> constrainViolationHandle(ConstraintViolationException exception) {
        logger.error(format("Bad Request: %s", exception.getMessage()), exception);
        return ResponseEntity.status(BAD_REQUEST).body(exception.getMessage());
    }

}
