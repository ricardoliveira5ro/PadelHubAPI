package com.ricardo.oliveira.padelHubAPI.dto;

import com.ricardo.oliveira.padelHubAPI.model.Court;
import com.ricardo.oliveira.padelHubAPI.model.Reservation;

import java.util.ArrayList;
import java.util.List;

public class CourtDTO {

    private int id;
    private String name;
    private String surface;
    private String courtEnvironment;
    private int clubId;
    private List<ReservationDTO> reservations = new ArrayList<>();

    public CourtDTO() {}

    public CourtDTO(Court court) {
        this.id = court.getId();
        this.name = court.getName();
        this.surface = court.getSurface();
        this.courtEnvironment = court.getCourtEnvironment();
        this.clubId = court.getClub().getId();
        convertReservationsToDTO(court.getReservations());
    }

    private void convertReservationsToDTO(List<Reservation> tmpReservations) {
        for (Reservation reservation : tmpReservations) {
            this.reservations.add(new ReservationDTO(reservation));
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

    public List<ReservationDTO> getReservations() {
        return reservations;
    }
}
