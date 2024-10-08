package com.ricardo.oliveira.padelHubAPI.dto.response;

import com.ricardo.oliveira.padelHubAPI.model.Reservation;
import com.ricardo.oliveira.padelHubAPI.model.Role;
import com.ricardo.oliveira.padelHubAPI.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserResponseDTO {

    private int id;
    private String username;
    private String contactEmail;
    private String contactPhone;
    private Role role;
    private String clubId;
    private final List<ReservationResponseDTO> reservations = new ArrayList<>();
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public UserResponseDTO() {}

    public UserResponseDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.contactEmail = user.getContactEmail();
        this.contactPhone = user.getContactPhone();
        this.role = user.getRole();
        this.clubId = user.getClub() != null ? String.valueOf(user.getClub().getId()): null;
        convertReservationsToDTO(user.getReservations());
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
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

    public String getUsername() {
        return username;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public Role getRole() {
        return role;
    }

    public String getClubId() {
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
        return "UserResponseDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", contactEmail='" + contactEmail + '\'' +
                ", contactPhone='" + contactPhone + '\'' +
                ", role=" + role +
                ", clubId='" + clubId + '\'' +
                ", reservations=" + reservations.stream().map(Object::toString).collect(Collectors.joining(", ")) +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
