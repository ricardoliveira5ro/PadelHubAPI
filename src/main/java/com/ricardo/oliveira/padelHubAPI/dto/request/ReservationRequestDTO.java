package com.ricardo.oliveira.padelHubAPI.dto.request;

public class ReservationRequestDTO {

    private String reservationStartTime;
    private String reservationEndTime;
    private String courtId;

    public String getReservationStartTime() {
        return reservationStartTime;
    }

    public String getReservationEndTime() {
        return reservationEndTime;
    }

    public String getCourtId() {
        return courtId;
    }
}
