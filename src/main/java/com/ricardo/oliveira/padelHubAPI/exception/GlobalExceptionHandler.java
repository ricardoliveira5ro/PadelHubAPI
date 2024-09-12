package com.ricardo.oliveira.padelHubAPI.exception;

import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        if (exception instanceof NotFoundException)
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage(), LocalDateTime.now()), HttpStatus.NOT_FOUND);

        if (exception instanceof JwtException ||
            exception instanceof BadCredentialsException ||
            exception instanceof UnauthenticatedException
        )
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), exception.getMessage(), LocalDateTime.now()), HttpStatus.UNAUTHORIZED);

        if (exception instanceof RolePrivilegesException)
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.FORBIDDEN.value(), exception.getMessage(), LocalDateTime.now()), HttpStatus.FORBIDDEN);

        // Default
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }
}
