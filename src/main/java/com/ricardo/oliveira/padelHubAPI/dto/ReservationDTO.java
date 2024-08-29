package com.ricardo.oliveira.padelHubAPI.dto;

import com.ricardo.oliveira.padelHubAPI.model.Reservation;

import java.time.LocalDateTime;

public class ReservationDTO {

    private int id;
    private LocalDateTime reservationStartTime;
    private LocalDateTime reservationEndTime;
    private String status;
    private int userId;
    private int courtId;

    public ReservationDTO() {}

    public ReservationDTO(Reservation reservation) {
        this.id = reservation.getId();
        this.reservationStartTime = reservation.getReservationStartTime();
        this.reservationEndTime = reservation.getReservationEndTime();
        this.status = reservation.getStatus();
        this.userId = reservation.getUser().getId();
        this.courtId = reservation.getCourt().getId();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getReservationStartTime() {
        return reservationStartTime;
    }

    public void setReservationStartTime(LocalDateTime reservationStartTime) {
        this.reservationStartTime = reservationStartTime;
    }

    public LocalDateTime getReservationEndTime() {
        return reservationEndTime;
    }

    public void setReservationEndTime(LocalDateTime reservationEndTime) {
        this.reservationEndTime = reservationEndTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCourtId() {
        return courtId;
    }

    public void setCourtId(int courtId) {
        this.courtId = courtId;
    }
}
