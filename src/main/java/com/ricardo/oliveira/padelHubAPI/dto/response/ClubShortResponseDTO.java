package com.ricardo.oliveira.padelHubAPI.dto.response;

import com.ricardo.oliveira.padelHubAPI.model.Club;

public class ClubShortResponseDTO {

    private int id;
    private String name;
    private String description;
    private String address;
    private String contactEmail;
    private String contactPhone;

    public ClubShortResponseDTO() {}

    public ClubShortResponseDTO(Club club) {
        this.id = club.getId();
        this.name = club.getName();
        this.description = club.getDescription();
        this.address = club.getAddress();
        this.contactEmail = club.getContactEmail();
        this.contactPhone = club.getContactPhone();
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
}
