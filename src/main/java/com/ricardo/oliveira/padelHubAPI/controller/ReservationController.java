package com.ricardo.oliveira.padelHubAPI.controller;

import com.ricardo.oliveira.padelHubAPI.dto.request.ReservationRequestDTO;
import com.ricardo.oliveira.padelHubAPI.dto.request.ReservationStatusRequestDTO;
import com.ricardo.oliveira.padelHubAPI.dto.response.ReservationResponseDTO;
import com.ricardo.oliveira.padelHubAPI.model.Reservation;
import com.ricardo.oliveira.padelHubAPI.model.Role;
import com.ricardo.oliveira.padelHubAPI.model.User;
import com.ricardo.oliveira.padelHubAPI.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/club-reservations")
    public ResponseEntity<List<ReservationResponseDTO>> clubReservations() {
        if (getCurrentUser().getRole() != Role.CLUB_OWNER)
            throw new RuntimeException("You are authenticated as a " + getCurrentUser().getRole() + " user. " +
                    "Must be a " + Role.CLUB_OWNER.getValue() + " user to perform this action");

        if (getCurrentUser().getClub() == null)
            throw new RuntimeException("No club available");

        return ResponseEntity.ok(new ArrayList<>(reservationService.findAllByClub(getCurrentUser()).stream().map(ReservationResponseDTO::new).toList()));
    }

    @GetMapping("/club-reservations/{reservation_id}")
    public ResponseEntity<ReservationResponseDTO> clubReservation(@PathVariable int reservation_id) {
        if (getCurrentUser().getRole() != Role.CLUB_OWNER)
            throw new RuntimeException("You are authenticated as a " + getCurrentUser().getRole() + " user. " +
                    "Must be a " + Role.CLUB_OWNER.getValue() + " user to perform this action");

        if (getCurrentUser().getClub() == null)
            throw new RuntimeException("No club available");

        return ResponseEntity.ok(new ReservationResponseDTO(reservationService.findByIdByClub(getCurrentUser(), reservation_id)));
    }

    @GetMapping("/my-games")
    public ResponseEntity<List<ReservationResponseDTO>> myGames() {
        return ResponseEntity.ok(new ArrayList<>(reservationService.findAllGames(getCurrentUser()).stream().map(ReservationResponseDTO::new).toList()));
    }

    @PostMapping("/add-game")
    public ResponseEntity<ReservationResponseDTO> addGame(@RequestBody ReservationRequestDTO reservationRequestDTO) {
        Reservation reservation = reservationService.save(getCurrentUser(), reservationRequestDTO);

        return ResponseEntity.ok(new ReservationResponseDTO(reservation));
    }

    @PostMapping("/{reservation_id}/change-status")
    public ResponseEntity<ReservationResponseDTO> changeStatus(@PathVariable int reservation_id, @RequestBody ReservationStatusRequestDTO reservationStatusRequestDTO) {
        return ResponseEntity.ok(new ReservationResponseDTO(reservationService.changeStatus(getCurrentUser(), reservation_id, reservationStatusRequestDTO)));
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getName().equals("anonymousUser")) {
            throw new RuntimeException("No user authenticated");
        }

        return (User) authentication.getPrincipal();
    }
}
