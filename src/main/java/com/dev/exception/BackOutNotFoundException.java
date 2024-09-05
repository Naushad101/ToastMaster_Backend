package com.dev.exception;

public class BackOutNotFoundException extends RuntimeException  {
    public BackOutNotFoundException(String msg){
        super(msg);
    }
}
