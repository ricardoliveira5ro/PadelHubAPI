package com.ricardo.oliveira.padelHubAPI.controller;

import com.ricardo.oliveira.padelHubAPI.dto.LoginDTO;
import com.ricardo.oliveira.padelHubAPI.dto.LoginResponse;
import com.ricardo.oliveira.padelHubAPI.dto.RegisterDTO;
import com.ricardo.oliveira.padelHubAPI.dto.UserDTO;
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

    private UserService userService;
    private JwtService jwtService;

    @Autowired
    public UserController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @GetMapping("/current-user")
    public ResponseEntity<UserDTO> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        return ResponseEntity.ok(new UserDTO(user));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> findById(@PathVariable int userId) {
        User user = userService.findById(userId);

        if (user == null) {
            throw new RuntimeException("Employee id not found - " + userId);
        }

        UserDTO userDTO = new UserDTO(user);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> register(@RequestBody RegisterDTO registerDTO) {
        User user = userService.signup(registerDTO);

        return ResponseEntity.ok(new UserDTO(user));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginDTO loginDTO) {
        User user = userService.authenticate(loginDTO);

        String token = jwtService.generateToken(user);

        return ResponseEntity.ok(new LoginResponse(user.getUsername(), token, jwtService.getExpirationTime()));
    }
}
