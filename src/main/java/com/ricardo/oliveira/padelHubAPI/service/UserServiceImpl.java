package com.ricardo.oliveira.padelHubAPI.service;

import com.ricardo.oliveira.padelHubAPI.dto.request.LoginRequestDTO;
import com.ricardo.oliveira.padelHubAPI.dto.request.SignupRequestDTO;
import com.ricardo.oliveira.padelHubAPI.model.Club;
import com.ricardo.oliveira.padelHubAPI.model.Role;
import com.ricardo.oliveira.padelHubAPI.model.User;
import com.ricardo.oliveira.padelHubAPI.repository.ClubRepository;
import com.ricardo.oliveira.padelHubAPI.repository.UserRepository;
import com.ricardo.oliveira.padelHubAPI.utils.Utils;
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
        Utils.validateSignupUserDTO(userRepository, clubRepository, signupRequestDTO);

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
        Utils.validateLoginUserDTO(loginRequestDTO);

        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(), loginRequestDTO.getPassword())
        );

        return userRepository.findByContactEmail(loginRequestDTO.getEmail()).orElseThrow();
    }
}
