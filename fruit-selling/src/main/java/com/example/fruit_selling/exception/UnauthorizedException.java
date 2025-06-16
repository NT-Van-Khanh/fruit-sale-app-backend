package com.example.fruit_selling.exception;

public class UnauthorizedException extends Throwable {
    public UnauthorizedException(String message){
        super(message);
    }
}
