package com.cbcoders.car_app_v1.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ExceptionsHandling {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new java.util.HashMap<>();
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserAlreadyExistsException.class)
    public Map<String, String> userAlreadyExistsException(UserAlreadyExistsException ex) {
        Map<String, String> errors = new java.util.HashMap<>();
        errors.put("message", ex.getMessage());
        return errors;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public Map<String, String> userNotFoundException(UserNotFoundException ex) {
        Map<String, String> errors = new java.util.HashMap<>();
        errors.put("message", ex.getMessage());
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CarAlreadyExistsException.class)
    public Map<String, String> carAlreadyExistsException(CarAlreadyExistsException ex) {
        Map<String, String> errors = new java.util.HashMap<>();
        errors.put("message", ex.getMessage());
        return errors;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CarNotFoundException.class)
    public Map<String, String> carNotFoundException(CarNotFoundException ex) {
        Map<String, String> errors = new java.util.HashMap<>();
        errors.put("message", ex.getMessage());
        return errors;
    }
}
