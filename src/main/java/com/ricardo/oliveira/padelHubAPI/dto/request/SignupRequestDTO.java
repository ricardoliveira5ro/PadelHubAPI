package com.ricardo.oliveira.padelHubAPI.dto.request;

import jakarta.validation.constraints.Pattern;

public class SignupRequestDTO {

    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String contactPhone;
    private ClubRequestDTO club;

    @Pattern(regexp = "ADMIN|CLUB_OWNER|PLAYER", message = "Invalid role. Allowed values are ADMIN, CLUB_OWNER, PLAYER")
    private String role;


    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public String getRole() {
        return role;
    }

    public ClubRequestDTO getClub() {
        return club;
    }
}
