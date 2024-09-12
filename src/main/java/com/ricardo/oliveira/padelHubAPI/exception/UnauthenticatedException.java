package com.ricardo.oliveira.padelHubAPI.exception;

public class UnauthenticatedException extends RuntimeException {

    public UnauthenticatedException(String message) {
        super(message);
    }

    public UnauthenticatedException(String message, Throwable throwable) {
    super(message, throwable);
  }

    public UnauthenticatedException(Throwable throwable) {
    super(throwable);
  }
}
