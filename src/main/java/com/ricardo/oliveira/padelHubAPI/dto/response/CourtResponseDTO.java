package com.ricardo.oliveira.padelHubAPI.dto.response;

import com.ricardo.oliveira.padelHubAPI.model.Court;
import com.ricardo.oliveira.padelHubAPI.model.Reservation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CourtResponseDTO {

    private int id;
    private String name;
    private String surface;
    private String courtEnvironment;
    private int clubId;
    private final List<ReservationResponseDTO> reservations = new ArrayList<>();
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public CourtResponseDTO() {}

    public CourtResponseDTO(Court court) {
        this.id = court.getId();
        this.name = court.getName();
        this.surface = court.getSurface();
        this.courtEnvironment = court.getCourtEnvironment();
        this.clubId = court.getClub().getId();
        convertReservationsToDTO(court.getReservations());
        this.createdAt = court.getCreatedAt();
        this.updatedAt = court.getUpdatedAt();
    }

    private void convertReservationsToDTO(List<Reservation> tmpReservations) {
        if (tmpReservations != null) {
            for (Reservation reservation : tmpReservations) {
                this.reservations.add(new ReservationResponseDTO(reservation));
            }
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurface() {
        return surface;
    }

    public String getCourtEnvironment() {
        return courtEnvironment;
    }

    public int getClubId() {
        return clubId;
    }

    public List<ReservationResponseDTO> getReservations() {
        return reservations;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return "CourtResponseDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surface='" + surface + '\'' +
                ", courtEnvironment='" + courtEnvironment + '\'' +
                ", clubId=" + clubId +
                ", reservations=" + reservations.stream().map(Object::toString).collect(Collectors.joining(", ")) +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
