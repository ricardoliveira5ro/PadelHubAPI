package com.ricardo.oliveira.padelHubAPI.dto.response;

import com.ricardo.oliveira.padelHubAPI.model.Club;
import com.ricardo.oliveira.padelHubAPI.model.Court;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClubResponseDTO {

    private int id;
    private String name;
    private String description;
    private String address;
    private String contactEmail;
    private String contactPhone;
    private final List<CourtResponseDTO> courts = new ArrayList<>();
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ClubResponseDTO() {}

    public ClubResponseDTO(Club club) {
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
        if (tmpCourts != null) {
            for (Court court : tmpCourts) {
                this.courts.add(new CourtResponseDTO(court));
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

    public List<CourtResponseDTO> getCourts() {
        return courts;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return "ClubResponseDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                ", contactEmail='" + contactEmail + '\'' +
                ", contactPhone='" + contactPhone + '\'' +
                ", courts=" + courts.stream().map(Object::toString).collect(Collectors.joining(", ")) +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
