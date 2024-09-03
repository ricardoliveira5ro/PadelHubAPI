package com.ricardo.oliveira.padelHubAPI.dto;

import com.ricardo.oliveira.padelHubAPI.model.Reservation;
import com.ricardo.oliveira.padelHubAPI.model.Role;
import com.ricardo.oliveira.padelHubAPI.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserDTO {

    private int id;
    private String username;
    private String contactEmail;
    private String contactPhone;
    private Role role;
    private List<ReservationDTO> reservations = new ArrayList<>();
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public UserDTO() {}

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.contactEmail = user.getContactEmail();
        this.contactPhone = user.getContactPhone();
        this.role = user.getRole();
        convertReservationsToDTO(user.getReservations());
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
    }

    private void convertReservationsToDTO(List<Reservation> tmpReservations) {
        if (tmpReservations != null) {
            for (Reservation reservation : tmpReservations) {
                this.reservations.add(new ReservationDTO(reservation));
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

    public List<ReservationDTO> getReservations() {
        return reservations;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
