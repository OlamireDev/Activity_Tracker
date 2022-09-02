package com.olamireDev.ActivtyTracker.exceptions;

public class UserNotFoundException extends RuntimeException {

    private final String where;

    public UserNotFoundException(String message, String where) {
        super(message);
        this.where=where;
    }

    public String getWhere(){return this.where;}
}
