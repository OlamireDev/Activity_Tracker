package com.olamireDev.ActivtyTracker.exceptions;

public class ActivityNotFoundException extends RuntimeException{

    public ActivityNotFoundException(String message) {
        super(message);
    }
}
