package com.chatcake.exception;

import org.springframework.http.HttpStatus;

public class UnAuthorizedException extends RuntimeException {

    public UnAuthorizedException(String message) {
        super(message);
    }

    HttpStatus getStatus() {
        return HttpStatus.UNAUTHORIZED;
    }
}
