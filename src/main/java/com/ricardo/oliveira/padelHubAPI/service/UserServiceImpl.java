package com.ricardo.oliveira.padelHubAPI.service;

import com.ricardo.oliveira.padelHubAPI.dto.LoginDTO;
import com.ricardo.oliveira.padelHubAPI.dto.RegisterDTO;
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

import java.util.List;
import java.util.Optional;

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
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Integer id) {
        Optional<User> result = userRepository.findById(id);

        User user;

        if (result.isPresent()) {
            user = result.get();
        }
        else {
            throw new RuntimeException("Did not find user id - " + id);
        }

        return user;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User signup(RegisterDTO registerDTO) {
        User user = new User(
                registerDTO.getFirstName(),
                registerDTO.getLastName(),
                passwordEncoder.encode(registerDTO.getPassword()),
                registerDTO.getEmail(),
                registerDTO.getContactPhone(),
                Role.valueOf(registerDTO.getRole().toUpperCase())
        );

        if (registerDTO.getClub() != null) {
            Club club = new Club(
                    registerDTO.getClub().getName(),
                    registerDTO.getClub().getDescription(),
                    registerDTO.getClub().getAddress(),
                    registerDTO.getClub().getContactEmail(),
                    registerDTO.getClub().getContactPhone()
            );
            clubRepository.save(club);
            user.setClub(club);

        } else {
            user.setClub(null);
        }


        return userRepository.save(user);
    }

    @Override
    public User authenticate(LoginDTO loginDTO) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));

        return userRepository.findByContactEmail(loginDTO.getEmail()).orElseThrow();
    }
}
