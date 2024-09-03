package com.dev.exception;

public class AvailabilityNotFoundException extends RuntimeException {
    public AvailabilityNotFoundException(String msg){
        super(msg);
    }
}
