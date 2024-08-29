package com.ricardo.oliveira.padelHubAPI.dto;

import com.ricardo.oliveira.padelHubAPI.model.Club;
import com.ricardo.oliveira.padelHubAPI.model.Court;

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

    public ClubDTO() {}

    public ClubDTO(Club club) {
        this.id = club.getId();
        this.name = club.getName();
        this.description = club.getDescription();
        this.address = club.getAddress();
        this.contactEmail = club.getContactEmail();
        this.contactPhone = club.getContactPhone();
        convertCourtsToDTO(club.getCourts());
    }

    private void convertCourtsToDTO(List<Court> tmpCourts) {
        for (Court court : tmpCourts) {
            this.courts.add(new CourtDTO(court));
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public List<CourtDTO> getCourts() {
        return courts;
    }

    public void setCourts(List<CourtDTO> courts) {
        this.courts = courts;
    }
}
