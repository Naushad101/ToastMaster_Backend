package com.dev.exception;

public class MembershipDetailsNotFoundException extends RuntimeException {

    public MembershipDetailsNotFoundException(String msg){
        super(msg);
    }
}
