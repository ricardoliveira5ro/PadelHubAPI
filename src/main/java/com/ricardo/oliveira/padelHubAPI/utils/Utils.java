package com.ricardo.oliveira.padelHubAPI.utils;

import com.ricardo.oliveira.padelHubAPI.dto.request.ClubRequestDTO;
import com.ricardo.oliveira.padelHubAPI.dto.request.LoginRequestDTO;
import com.ricardo.oliveira.padelHubAPI.dto.request.SignupRequestDTO;
import com.ricardo.oliveira.padelHubAPI.exception.InvalidRequestBodyException;
import com.ricardo.oliveira.padelHubAPI.repository.ClubRepository;
import com.ricardo.oliveira.padelHubAPI.repository.UserRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class Utils {

    private Utils() {}

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    public static LocalDateTime formatDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime, formatter);
    }

    public static void validateSignupUserDTO(UserRepository userRepository, ClubRepository clubRepository, SignupRequestDTO signupRequestDTO) {
        validateField(signupRequestDTO.getFirstName(), "firstName");
        validateField(signupRequestDTO.getLastName(), "lastName");
        validateField(signupRequestDTO.getEmail(), "email");
        validateField(signupRequestDTO.getPassword(), "password");
        validateField(signupRequestDTO.getRole(), "role");

        if (userRepository.findByContactEmail(signupRequestDTO.getEmail()).orElse(null) != null)
            throw new InvalidRequestBodyException("Email already exists");

        if (signupRequestDTO.getClub() != null)
            validateClubDTO(clubRepository, signupRequestDTO.getClub());
    }

    public static void validateLoginUserDTO(LoginRequestDTO loginRequestDTO) {
        validateField(loginRequestDTO.getEmail(), "email");
        validateField(loginRequestDTO.getPassword(), "password");
    }

    public static void validateField(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new InvalidRequestBodyException("'" + fieldName + "' cannot be null or blank");
        }
    }

    public static void validateClubDTO(ClubRepository clubRepository, ClubRequestDTO clubRequestDTO) {
        if (clubRepository.findByContactEmail(clubRequestDTO.getContactEmail()).orElse(null) != null)
            throw new InvalidRequestBodyException("Club email already exists");

        if (clubRepository.findByContactPhone(clubRequestDTO.getContactPhone()).orElse(null) != null)
            throw new InvalidRequestBodyException("Club phone already exists");
    }
}
