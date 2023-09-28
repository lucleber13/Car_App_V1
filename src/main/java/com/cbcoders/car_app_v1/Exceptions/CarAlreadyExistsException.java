package com.cbcoders.car_app_v1.Exceptions;

public class CarAlreadyExistsException extends RuntimeException {
    public CarAlreadyExistsException(String message) {
        super(message);
    }
}
