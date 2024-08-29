package com.ricardo.oliveira.padelHubAPI.dto;

import com.ricardo.oliveira.padelHubAPI.model.Reservation;
import com.ricardo.oliveira.padelHubAPI.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {

    private int id;
    private String username;
    private String contactEmail;
    private String contactPhone;
    private String role;
    private List<ReservationDTO> reservations = new ArrayList<>();

    public UserDTO() {}

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.contactEmail = user.getContactPhone();
        this.contactPhone = user.getContactPhone();
        this.role = user.getRole();
        convertReservationsToDTO(user.getReservations());
    }
    private void convertReservationsToDTO(List<Reservation> tmpReservations) {
        for (Reservation reservation : tmpReservations) {
            this.reservations.add(new ReservationDTO(reservation));
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<ReservationDTO> getReservations() {
        return reservations;
    }

    public void setReservations(List<ReservationDTO> reservations) {
        this.reservations = reservations;
    }
}
