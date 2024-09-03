package com.ricardo.oliveira.padelHubAPI.model;

public enum Role {
    ADMIN("ADMIN"),
    CLUB_OWNER("CLUB_OWNER"),
    PLAYER("PLAYER");

    private final String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
