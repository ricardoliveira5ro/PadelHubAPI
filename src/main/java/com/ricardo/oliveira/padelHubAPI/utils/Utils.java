package com.ricardo.oliveira.padelHubAPI.utils;

import com.ricardo.oliveira.padelHubAPI.dto.request.*;
import com.ricardo.oliveira.padelHubAPI.exception.InvalidRequestBodyException;
import com.ricardo.oliveira.padelHubAPI.exception.UnauthenticatedException;
import com.ricardo.oliveira.padelHubAPI.model.User;
import com.ricardo.oliveira.padelHubAPI.repository.ClubRepository;
import com.ricardo.oliveira.padelHubAPI.repository.UserRepository;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.beans.PropertyDescriptor;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

public final class Utils {

    private Utils() {}

    public static User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getName().equals("anonymousUser")) {
            throw new UnauthenticatedException("No user authenticated");
        }

        return (User) authentication.getPrincipal();
    }

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

    public static void validateClubDTO(ClubRepository clubRepository, ClubRequestDTO clubRequestDTO) {
        validateField(clubRequestDTO.getName(), "name");
        validateField(clubRequestDTO.getDescription(), "description");
        validateField(clubRequestDTO.getAddress(), "address");
        validateField(clubRequestDTO.getContactEmail(), "contactEmail");
        validateField(clubRequestDTO.getContactPhone(), "contactPhone");

        if (clubRepository.findByContactEmail(clubRequestDTO.getContactEmail()).orElse(null) != null)
            throw new InvalidRequestBodyException("Club email already exists");

        if (clubRepository.findByContactPhone(clubRequestDTO.getContactPhone()).orElse(null) != null)
            throw new InvalidRequestBodyException("Club phone already exists");
    }

    public static void validateCourtDTO(CourtRequestDTO courtRequestDTO) {
        validateField(courtRequestDTO.getName(), "name");
        validateField(courtRequestDTO.getSurface(), "surface");
        validateField(courtRequestDTO.getCourtEnvironment(), "courtEnvironment");
    }

    public static void validateReservationDTO(ReservationRequestDTO reservationRequestDTO) {
        validateField(reservationRequestDTO.getReservationStartTime(), "reservationStartTime");
        validateField(reservationRequestDTO.getReservationEndTime(), "reservationEndTime");
        validateField(reservationRequestDTO.getCourtId(), "courtId");
    }

    private static void validateField(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new InvalidRequestBodyException("'" + fieldName + "' cannot be null or blank");
        }
    }

    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
