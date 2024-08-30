package com.ricardo.oliveira.padelHubAPI.dto;

import com.ricardo.oliveira.padelHubAPI.model.Club;
import com.ricardo.oliveira.padelHubAPI.model.Court;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ClubDTO {

    private int id;
    private String name;
    private String description;
    private String address;
    private String contactEmail;
    private String contactPhone;
    private List<CourtDTO> courts = new ArrayList<>();
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ClubDTO() {}

    public ClubDTO(Club club) {
        this.id = club.getId();
        this.name = club.getName();
        this.description = club.getDescription();
        this.address = club.getAddress();
        this.contactEmail = club.getContactEmail();
        this.contactPhone = club.getContactPhone();
        convertCourtsToDTO(club.getCourts());
        this.createdAt = club.getCreatedAt();
        this.updatedAt = club.getUpdatedAt();
    }

    private void convertCourtsToDTO(List<Court> tmpCourts) {
        for (Court court : tmpCourts) {
            this.courts.add(new CourtDTO(court));
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

    public List<CourtDTO> getCourts() {
        return courts;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
