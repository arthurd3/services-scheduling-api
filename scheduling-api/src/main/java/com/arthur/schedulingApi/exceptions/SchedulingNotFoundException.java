package com.arthur.schedulingApi.exceptions;

public class SchedulingNotFoundException extends RuntimeException {
    public SchedulingNotFoundException(String message) {
        super(message);
    }
}
