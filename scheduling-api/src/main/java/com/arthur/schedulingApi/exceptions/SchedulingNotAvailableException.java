package com.arthur.schedulingApi.exceptions;

public class SchedulingNotAvailableException extends RuntimeException {
    public SchedulingNotAvailableException(String message) {
        super(message);
    }
}
