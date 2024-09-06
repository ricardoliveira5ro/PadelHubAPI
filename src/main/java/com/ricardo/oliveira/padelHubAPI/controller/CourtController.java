package com.ricardo.oliveira.padelHubAPI.controller;

import com.ricardo.oliveira.padelHubAPI.dto.response.CourtResponseDTO;
import com.ricardo.oliveira.padelHubAPI.dto.response.CourtShortResponseDTO;
import com.ricardo.oliveira.padelHubAPI.model.Court;
import com.ricardo.oliveira.padelHubAPI.model.Role;
import com.ricardo.oliveira.padelHubAPI.model.User;
import com.ricardo.oliveira.padelHubAPI.service.CourtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/courts")
public class CourtController {

    private final CourtService courtService;

    @Autowired
    public CourtController(CourtService courtService) {
        this.courtService = courtService;
    }

    @GetMapping("/{court_id}")
    public ResponseEntity<CourtShortResponseDTO> court(@PathVariable int court_id) {
        return ResponseEntity.ok(new CourtShortResponseDTO(courtService.findById(court_id)));
    }

    @GetMapping("/my-courts")
    public ResponseEntity<List<CourtResponseDTO>> courts() {
        if (getCurrentUser().getRole() != Role.CLUB_OWNER)
            throw new RuntimeException("You are authenticated as a " + getCurrentUser().getRole() + " user. " +
                    "Must be a " + Role.CLUB_OWNER.getValue() + " user to perform this action");

        List<Court> courts = courtService.findByClubId(getCurrentUser());

        return ResponseEntity.ok(new ArrayList<>(courts.stream().map(CourtResponseDTO::new).toList()));
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getName().equals("anonymousUser")) {
            throw new RuntimeException("No user authenticated");
        }

        return (User) authentication.getPrincipal();
    }
}
