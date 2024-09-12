package com.ricardo.oliveira.padelHubAPI.exception;

public class InvalidRequestBodyException extends RuntimeException {

    public InvalidRequestBodyException(String message) {
        super(message);
    }
}
