package com.chatcake.exception;

import org.springframework.http.HttpStatus;

public class ForbiddenException extends RuntimeException {


    public ForbiddenException(String message) {
        super(message);
    }

    HttpStatus getStatus() {
        return HttpStatus.FORBIDDEN;
    }
}
