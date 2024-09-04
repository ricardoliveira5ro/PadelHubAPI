package com.ricardo.oliveira.padelHubAPI.dto.response;

public class LoginResponseDTO {

    private final String username;
    private final String token;
    private final long expiresIn;

    public LoginResponseDTO(String username, String token, long expiresIn) {
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
