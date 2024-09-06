package com.ricardo.oliveira.padelHubAPI.controller;

import com.ricardo.oliveira.padelHubAPI.dto.request.CourtRequestDTO;
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
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.PanelUI;
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

    @GetMapping("/courtsByClub/{club_id}")
    public ResponseEntity<List<CourtShortResponseDTO>> courtsByClub(@PathVariable int club_id) {
        return ResponseEntity.ok(new ArrayList<>(courtService.findByClubId(club_id).stream().map(CourtShortResponseDTO::new).toList()));
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

    @PostMapping("/add-court")
    public ResponseEntity<CourtResponseDTO> addCourt(@RequestBody CourtRequestDTO courtRequestDTO) {
        if (getCurrentUser().getRole() != Role.CLUB_OWNER)
            throw new RuntimeException("You are authenticated as a " + getCurrentUser().getRole() + " user. " +
                    "Must be a " + Role.CLUB_OWNER.getValue() + " user to perform this action");

        if (getCurrentUser().getClub() == null)
            throw new RuntimeException("No club to assign a new court");

        Court court = courtService.save(courtRequestDTO, getCurrentUser());

        return ResponseEntity.ok(new CourtResponseDTO(court));
    }

    @PutMapping("update-court/{court_id}")
    public ResponseEntity<CourtResponseDTO> updateCourt(@PathVariable int court_id, @RequestBody CourtRequestDTO courtRequestDTO) {
        if (getCurrentUser().getRole() != Role.CLUB_OWNER)
            throw new RuntimeException("You are authenticated as a " + getCurrentUser().getRole() + " user. " +
                    "Must be a " + Role.CLUB_OWNER.getValue() + " user to perform this action");

        return ResponseEntity.ok(new CourtResponseDTO(courtService.update(court_id, courtRequestDTO)));
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getName().equals("anonymousUser")) {
            throw new RuntimeException("No user authenticated");
        }

        return (User) authentication.getPrincipal();
    }
}
