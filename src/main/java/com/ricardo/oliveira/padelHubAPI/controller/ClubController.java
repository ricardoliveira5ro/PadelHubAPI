package com.ricardo.oliveira.padelHubAPI.controller;

import com.ricardo.oliveira.padelHubAPI.dto.request.ClubRequestDTO;
import com.ricardo.oliveira.padelHubAPI.dto.response.ClubResponseDTO;
import com.ricardo.oliveira.padelHubAPI.dto.response.ClubShortResponseDTO;
import com.ricardo.oliveira.padelHubAPI.dto.response.UserResponseDTO;
import com.ricardo.oliveira.padelHubAPI.exceptions.ErrorResponse;
import com.ricardo.oliveira.padelHubAPI.exceptions.NotFoundException;
import com.ricardo.oliveira.padelHubAPI.model.Club;
import com.ricardo.oliveira.padelHubAPI.model.Role;
import com.ricardo.oliveira.padelHubAPI.model.User;
import com.ricardo.oliveira.padelHubAPI.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/clubs")
public class ClubController {

    private final ClubService clubService;

    @Autowired
    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ClubShortResponseDTO>> clubs() {
        return ResponseEntity.ok(new ArrayList<>(clubService.findAll().stream().map(ClubShortResponseDTO::new).toList()));
    }

    @GetMapping("/{club_id}")
    public ResponseEntity<ClubShortResponseDTO> club(@PathVariable int club_id) {
        return ResponseEntity.ok(new ClubShortResponseDTO(clubService.findById(club_id)));
    }

    @GetMapping("/my-club")
    public ResponseEntity<ClubResponseDTO> myClub() {
        if (getCurrentUser().getRole() != Role.CLUB_OWNER)
            throw new RuntimeException("You are authenticated as a " + getCurrentUser().getRole() + " user. " +
                    "Must be a " + Role.CLUB_OWNER.getValue() + " user to perform this action");

        int clubId = (getCurrentUser().getClub() != null) ? getCurrentUser().getClub().getId() : -1;

        Club club = clubService.findById(clubId);
        ClubResponseDTO ClubResponseDTO = new ClubResponseDTO(club);

        return ResponseEntity.ok(ClubResponseDTO);
    }

    @PostMapping("/add-club")
    public ResponseEntity<ClubResponseDTO> addClub(@RequestBody ClubRequestDTO clubRequestDTO) {
        if (getCurrentUser().getRole() != Role.CLUB_OWNER)
            throw new RuntimeException("You are authenticated as a " + getCurrentUser().getRole() + " user. " +
                    "Must be a " + Role.CLUB_OWNER.getValue() + " user to perform this action");

        Club club = clubService.save(clubRequestDTO, getCurrentUser());

        return ResponseEntity.ok(new ClubResponseDTO(club));
    }

    @PutMapping("/update-club")
    public ResponseEntity<ClubResponseDTO> updateClub(@RequestBody ClubRequestDTO clubRequestDTO) {
        if (getCurrentUser().getRole() != Role.CLUB_OWNER)
            throw new RuntimeException("You are authenticated as a " + getCurrentUser().getRole() + " user. " +
                    "Must be a " + Role.CLUB_OWNER.getValue() + " user to perform this action");

        Club club = clubService.update(clubRequestDTO, getCurrentUser());

        return ResponseEntity.ok(new ClubResponseDTO(club));
    }

    @GetMapping("/my-club/players")
    public ResponseEntity<List<UserResponseDTO>> players() {
        if (getCurrentUser().getRole() != Role.CLUB_OWNER)
            throw new RuntimeException("You are authenticated as a " + getCurrentUser().getRole() + " user. " +
                    "Must be a " + Role.CLUB_OWNER.getValue() + " user to perform this action");

        List<User> players = clubService.findPlayersWithReservationsInClub(getCurrentUser());

        return ResponseEntity.ok(new ArrayList<>(players.stream().map(UserResponseDTO::new).toList()));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        return switch (exception) {
            case NotFoundException e ->
                    new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage(), LocalDateTime.now()), HttpStatus.NOT_FOUND);
            default ->
                    new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), LocalDateTime.now()), HttpStatus.BAD_REQUEST);
        };
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getName().equals("anonymousUser")) {
            throw new RuntimeException("No user authenticated");
        }

        return (User) authentication.getPrincipal();
    }
}
