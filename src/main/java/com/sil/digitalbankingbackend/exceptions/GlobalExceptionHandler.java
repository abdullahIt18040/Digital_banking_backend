package com.sil.digitalbankingbackend.exceptions;

import com.sil.digitalbankingbackend.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Response> handleUserNotFound(UsernameNotFoundException ex) {
        return Response.getResponseData(HttpStatus.BAD_REQUEST, ex.getMessage(), null);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Response> handleBadCredentials(BadCredentialsException ex) {
        return Response.getResponseData(HttpStatus.BAD_REQUEST, "Invalid email or password", null);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleException(Exception ex) {
        return Response.getResponseData(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), null);
    }
}

