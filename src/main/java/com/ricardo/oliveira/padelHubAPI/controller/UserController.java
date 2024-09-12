package com.ricardo.oliveira.padelHubAPI.controller;

import com.ricardo.oliveira.padelHubAPI.dto.request.LoginRequestDTO;
import com.ricardo.oliveira.padelHubAPI.dto.request.SignupRequestDTO;
import com.ricardo.oliveira.padelHubAPI.dto.response.LoginResponseDTO;
import com.ricardo.oliveira.padelHubAPI.dto.response.UserResponseDTO;
import com.ricardo.oliveira.padelHubAPI.exception.RolePrivilegesException;
import com.ricardo.oliveira.padelHubAPI.exception.UnauthenticatedException;
import com.ricardo.oliveira.padelHubAPI.model.Role;
import com.ricardo.oliveira.padelHubAPI.model.User;
import com.ricardo.oliveira.padelHubAPI.service.JwtService;
import com.ricardo.oliveira.padelHubAPI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;

    @Autowired
    public UserController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @GetMapping("/current-user")
    public ResponseEntity<UserResponseDTO> authenticatedUser() {
        return ResponseEntity.ok(new UserResponseDTO(getCurrentUser()));
    }

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDTO> register(@RequestBody SignupRequestDTO signupRequestDTO) {
        if (signupRequestDTO.getClub() != null && !signupRequestDTO.getRole().equalsIgnoreCase(Role.CLUB_OWNER.getValue()))
            throw new RolePrivilegesException("You are authenticated as a " + signupRequestDTO.getRole() + " user. " +
                                        "Must be a " + Role.CLUB_OWNER.getValue() + " user to perform this action");

        User user = userService.signup(signupRequestDTO);

        return ResponseEntity.ok(new UserResponseDTO(user));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        User user = userService.authenticate(loginRequestDTO);

        String token = jwtService.generateToken(user);

        return ResponseEntity.ok(new LoginResponseDTO(user.getUsername(), token, jwtService.getExpirationTime()));
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getName().equals("anonymousUser")) {
            throw new UnauthenticatedException("No user authenticated");
        }

        return (User) authentication.getPrincipal();
    }
}
