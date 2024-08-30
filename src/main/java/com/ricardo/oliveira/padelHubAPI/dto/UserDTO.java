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

    public String getUsername() {
        return username;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public String getRole() {
        return role;
    }

    public List<ReservationDTO> getReservations() {
        return reservations;
    }
}
