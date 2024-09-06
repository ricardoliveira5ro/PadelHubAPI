package com.ricardo.oliveira.padelHubAPI.dto.response;

import com.ricardo.oliveira.padelHubAPI.model.Club;
import com.ricardo.oliveira.padelHubAPI.model.Court;

import java.util.ArrayList;
import java.util.List;

public class ClubShortResponseDTO {

    private int id;
    private String name;
    private String description;
    private String address;
    private String contactEmail;
    private String contactPhone;
    private final List<CourtShortResponseDTO> courts = new ArrayList<>();

    public ClubShortResponseDTO() {}

    public ClubShortResponseDTO(Club club) {
        this.id = club.getId();
        this.name = club.getName();
        this.description = club.getDescription();
        this.address = club.getAddress();
        this.contactEmail = club.getContactEmail();
        this.contactPhone = club.getContactPhone();
        convertCourtsToDTO(club.getCourts());
    }

    private void convertCourtsToDTO(List<Court> tmpCourts) {
        if (tmpCourts != null) {
            for (Court court : tmpCourts) {
                this.courts.add(new CourtShortResponseDTO(court));
            }
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public List<CourtShortResponseDTO> getCourts() {
        return courts;
    }
}
