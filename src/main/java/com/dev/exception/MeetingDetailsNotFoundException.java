package com.dev.exception;


public class MeetingDetailsNotFoundException extends RuntimeException {
    public MeetingDetailsNotFoundException(String msg){
        super(msg);
    }
}
