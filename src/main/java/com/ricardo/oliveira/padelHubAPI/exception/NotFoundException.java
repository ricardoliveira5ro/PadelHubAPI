package com.ricardo.oliveira.padelHubAPI.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public NotFoundException(Throwable throwable) {
        super(throwable);
    }
}
