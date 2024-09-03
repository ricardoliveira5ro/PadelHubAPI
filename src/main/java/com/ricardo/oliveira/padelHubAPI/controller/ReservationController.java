package com.ricardo.oliveira.padelHubAPI.controller;

import com.ricardo.oliveira.padelHubAPI.dto.ReservationDTO;
import com.ricardo.oliveira.padelHubAPI.model.Reservation;
import com.ricardo.oliveira.padelHubAPI.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/")
    public ResponseEntity<List<ReservationDTO>> findAll() {
        List<Reservation> reservations = reservationService.findAll();
        List<ReservationDTO> reservationDTOS = new ArrayList<>();

        for (Reservation reservation : reservations) {
            reservationDTOS.add(new ReservationDTO(reservation));
        }

        return ResponseEntity.ok(reservationDTOS);
    }

    @GetMapping("/{reservationId}")
    public ResponseEntity<ReservationDTO> findById(@PathVariable int reservationId) {
        Reservation reservation = reservationService.findById(reservationId);

        if (reservation == null) {
            throw new RuntimeException("Reservation id not found - " + reservationId);
        }

        ReservationDTO reservationDTO = new ReservationDTO(reservation);
        return ResponseEntity.ok(reservationDTO);
    }
}
