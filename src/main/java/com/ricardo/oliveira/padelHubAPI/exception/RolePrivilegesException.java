package com.ricardo.oliveira.padelHubAPI.exception;

public class RolePrivilegesException extends RuntimeException {

    public RolePrivilegesException(String message) {
        super(message);
    }

    public RolePrivilegesException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public RolePrivilegesException(Throwable throwable) {
        super(throwable);
    }
}
