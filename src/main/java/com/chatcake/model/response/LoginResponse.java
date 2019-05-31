package com.chatcake.model.response;

public class LoginResponse {

    private String token;
    private String id;
    private String name;
    private String username;

    public LoginResponse(String token, String id, String name, String username) {
        this.token = token;
        this.id = id;
        this.name = name;
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
