package com.ricardo.oliveira.padelHubAPI.dto.request;

import jakarta.validation.constraints.Pattern;

public class ReservationStatusRequestDTO {

    @Pattern(regexp = "CONFIRMED|CANCELLED|PENDING|DONE", message = "Invalid role. Allowed values are CONFIRMED, CANCELLED, PENDING or DONE")
    private String status;

    public String getStatus() {
        return status;
    }
}
