package com.michael.thirdpartyapi;

public class UserNotFoundException  extends RuntimeException{
    public UserNotFoundException(String message) {
        super(message);
    }
}
