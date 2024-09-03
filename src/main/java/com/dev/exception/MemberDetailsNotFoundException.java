package com.dev.exception;

public class MemberDetailsNotFoundException extends RuntimeException{
    public MemberDetailsNotFoundException(String msg){
        super(msg);
    }
}
