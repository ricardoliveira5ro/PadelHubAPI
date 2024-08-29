package com.ricardo.oliveira.padelHubAPI.controller;

import com.ricardo.oliveira.padelHubAPI.model.Reservation;
import com.ricardo.oliveira.padelHubAPI.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/")
    public List<Reservation> findAll() {
        return reservationService.findAll();
    }

    @GetMapping("/{reservationId}")
    public Reservation findById(@PathVariable int reservationId) {
        Reservation reservation = reservationService.findById(reservationId);

        if (reservation == null) {
            throw new RuntimeException("Reservation id not found - " + reservationId);
        }

        return reservation;
    }
}
