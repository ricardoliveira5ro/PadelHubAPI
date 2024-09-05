package com.ricardo.oliveira.padelHubAPI.dto.request;

import java.util.List;

public class ClubRequestDTO {

    private String name;
    private String description;
    private String address;
    private String contactEmail;
    private String contactPhone;
    private List<CourtRequestDTO> courts;

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

    public List<CourtRequestDTO> getCourts() {
        return courts;
    }
}
