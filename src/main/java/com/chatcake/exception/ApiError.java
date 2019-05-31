package com.chatcake.exception;

import java.util.Arrays;
import java.util.List;

public class ApiError {

    private List<String> errors;

    public ApiError () {
    }

    ApiError(List<String> errors) {
        this.errors = errors;
    }

    ApiError (String... errors) {
        this.errors = Arrays.asList(errors);
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
