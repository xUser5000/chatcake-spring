package com.chatcake.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }

    HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
