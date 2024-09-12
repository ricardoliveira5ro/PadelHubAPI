package com.ricardo.oliveira.padelHubAPI.service;

import com.ricardo.oliveira.padelHubAPI.dto.request.ClubRequestDTO;
import com.ricardo.oliveira.padelHubAPI.dto.request.LoginRequestDTO;
import com.ricardo.oliveira.padelHubAPI.dto.request.SignupRequestDTO;
import com.ricardo.oliveira.padelHubAPI.exception.InvalidRequestBodyException;
import com.ricardo.oliveira.padelHubAPI.model.Club;
import com.ricardo.oliveira.padelHubAPI.model.Role;
import com.ricardo.oliveira.padelHubAPI.model.User;
import com.ricardo.oliveira.padelHubAPI.repository.ClubRepository;
import com.ricardo.oliveira.padelHubAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ClubRepository clubRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ClubRepository clubRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.clubRepository = clubRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public User signup(SignupRequestDTO signupRequestDTO) {
        validateSignupUserDTO(signupRequestDTO);

        User user = new User(
                signupRequestDTO.getFirstName(),
                signupRequestDTO.getLastName(),
                passwordEncoder.encode(signupRequestDTO.getPassword()),
                signupRequestDTO.getEmail(),
                signupRequestDTO.getContactPhone(),
                Role.valueOf(signupRequestDTO.getRole().toUpperCase())
        );

        if (signupRequestDTO.getClub() != null) {
            Club club = new Club(
                    signupRequestDTO.getClub().getName(),
                    signupRequestDTO.getClub().getDescription(),
                    signupRequestDTO.getClub().getAddress(),
                    signupRequestDTO.getClub().getContactEmail(),
                    signupRequestDTO.getClub().getContactPhone()
            );
            clubRepository.save(club);
            user.setClub(club);

        } else {
            user.setClub(null);
        }


        return userRepository.save(user);
    }

    @Override
    public User authenticate(LoginRequestDTO loginRequestDTO) {
        validateLoginUserDTO(loginRequestDTO);

        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(), loginRequestDTO.getPassword())
        );

        return userRepository.findByContactEmail(loginRequestDTO.getEmail()).orElseThrow();
    }

    private void validateSignupUserDTO(SignupRequestDTO signupRequestDTO) {
        validateField(signupRequestDTO.getFirstName(), "firstName");
        validateField(signupRequestDTO.getLastName(), "lastName");
        validateField(signupRequestDTO.getEmail(), "email");
        validateField(signupRequestDTO.getPassword(), "password");
        validateField(signupRequestDTO.getRole(), "role");

        if (userRepository.findByContactEmail(signupRequestDTO.getEmail()).orElse(null) != null)
            throw new InvalidRequestBodyException("email already exists");

        if (signupRequestDTO.getClub() != null)
            validateClubDTO(signupRequestDTO.getClub());
    }

    private void validateLoginUserDTO(LoginRequestDTO loginRequestDTO) {
        validateField(loginRequestDTO.getEmail(), "email");
        validateField(loginRequestDTO.getPassword(), "password");
    }

    private void validateField(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new InvalidRequestBodyException("'" + fieldName + "' cannot be null or blank");
        }
    }

    private void validateClubDTO(ClubRequestDTO clubRequestDTO) {
        if (clubRepository.findByContactEmail(clubRequestDTO.getContactEmail()).orElse(null) != null)
            throw new InvalidRequestBodyException("club email already exists");

        if (clubRepository.findByContactPhone(clubRequestDTO.getContactPhone()).orElse(null) != null)
            throw new InvalidRequestBodyException("club phone already exists");
    }
}
