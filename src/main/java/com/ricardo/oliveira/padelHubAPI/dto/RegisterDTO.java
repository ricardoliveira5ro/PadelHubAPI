package com.ricardo.oliveira.padelHubAPI.dto;

import jakarta.validation.constraints.Pattern;

public class RegisterDTO {

    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String contactPhone;

    @Pattern(regexp = "ADMIN|CLUB_OWNER|PLAYER", message = "Invalid role. Allowed values are ADMIN, CLUB_OWNER, PLAYER")
    private String role;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setContactEmail(String email) {
        this.email = email;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
