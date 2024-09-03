package com.dev.exception;

public class InvalidPhoneNumberException extends RuntimeException{
    
    public InvalidPhoneNumberException(String msg){
        super(msg);
    }
}
