package com.ricardo.oliveira.padelHubAPI.dto;

public class LoginResponse {

    private final String username;
    private final String token;
    private final long expiresIn;

    public LoginResponse(String username, String token, long expiresIn) {
        this.username = username;
        this.token = token;
        this.expiresIn = expiresIn;
    }

    public String getUsername() {
        return username;
    }

    public String getToken() {
        return token;
    }

    public long getExpiresIn() {
        return expiresIn;
    }
}
