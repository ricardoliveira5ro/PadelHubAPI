package com.ricardo.oliveira.padelHubAPI.controller;

import com.ricardo.oliveira.padelHubAPI.dto.request.ClubRequestDTO;
import com.ricardo.oliveira.padelHubAPI.dto.response.ClubResponseDTO;
import com.ricardo.oliveira.padelHubAPI.model.Club;
import com.ricardo.oliveira.padelHubAPI.model.Role;
import com.ricardo.oliveira.padelHubAPI.model.User;
import com.ricardo.oliveira.padelHubAPI.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clubs")
public class ClubController {

    private final ClubService clubService;

    @Autowired
    public ClubController(ClubService clubService) {
        this.clubService = clubService;
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

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getName().equals("anonymousUser")) {
            throw new RuntimeException("No user authenticated");
        }

        return (User) authentication.getPrincipal();
    }
}
