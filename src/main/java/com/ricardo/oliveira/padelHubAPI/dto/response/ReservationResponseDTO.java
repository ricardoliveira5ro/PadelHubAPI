package com.ricardo.oliveira.padelHubAPI.dto.response;

import com.ricardo.oliveira.padelHubAPI.model.Reservation;
import com.ricardo.oliveira.padelHubAPI.model.ReservationStatus;

import java.time.LocalDateTime;

public class ReservationResponseDTO {

    private int id;
    private LocalDateTime reservationStartTime;
    private LocalDateTime reservationEndTime;
    private ReservationStatus status;
    private int userId;
    private int courtId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ReservationResponseDTO() {}

    public ReservationResponseDTO(Reservation reservation) {
        this.id = reservation.getId();
        this.reservationStartTime = reservation.getReservationStartTime();
        this.reservationEndTime = reservation.getReservationEndTime();
        this.status = reservation.getStatus();
        this.userId = reservation.getUser().getId();
        this.courtId = reservation.getCourt().getId();
        this.createdAt = reservation.getCreatedAt();
        this.updatedAt = reservation.getUpdatedAt();
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getReservationStartTime() {
        return reservationStartTime;
    }

    public LocalDateTime getReservationEndTime() {
        return reservationEndTime;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public int getUserId() {
        return userId;
    }

    public int getCourtId() {
        return courtId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return "ReservationResponseDTO{" +
                "id=" + id +
                ", reservationStartTime=" + reservationStartTime +
                ", reservationEndTime=" + reservationEndTime +
                ", status=" + status +
                ", userId=" + userId +
                ", courtId=" + courtId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
