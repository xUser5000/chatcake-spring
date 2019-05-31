package com.chatcake.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }

    HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
