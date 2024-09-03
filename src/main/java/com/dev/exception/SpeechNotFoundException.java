package com.dev.exception;

public class SpeechNotFoundException extends RuntimeException {
    public SpeechNotFoundException(String msg){
        super(msg);
    }
}
