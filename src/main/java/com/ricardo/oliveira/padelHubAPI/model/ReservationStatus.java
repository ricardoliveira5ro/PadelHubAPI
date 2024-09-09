package com.ricardo.oliveira.padelHubAPI.model;

public enum ReservationStatus {
    CONFIRMED("CONFIRMED"),
    CANCELLED("CANCELLED"),
    PENDING("PENDING"),
    DONE("DONE");

    private final String value;

    ReservationStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
