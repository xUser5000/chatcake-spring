package com.chatcake.model.request;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public class RegisterRequest {

    @NotEmpty() @Length() private String name;
    @NotEmpty() private String username;
    @NotEmpty() @Length(min = 6) private String password;

    public RegisterRequest() {
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
