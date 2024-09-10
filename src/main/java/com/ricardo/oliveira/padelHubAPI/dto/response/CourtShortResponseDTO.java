package com.ricardo.oliveira.padelHubAPI.dto.response;

import com.ricardo.oliveira.padelHubAPI.model.Court;

public class CourtShortResponseDTO {

    private int id;
    private String name;
    private String surface;
    private String courtEnvironment;
    private int clubId;

    public CourtShortResponseDTO() {}

    public CourtShortResponseDTO(Court court) {
        this.id = court.getId();
        this.name = court.getName();
        this.surface = court.getSurface();
        this.courtEnvironment = court.getCourtEnvironment();
        this.clubId = court.getClub().getId();
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

    @Override
    public String toString() {
        return "CourtShortResponseDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surface='" + surface + '\'' +
                ", courtEnvironment='" + courtEnvironment + '\'' +
                ", clubId=" + clubId +
                '}';
    }
}
