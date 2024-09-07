package com.ricardo.oliveira.padelHubAPI.service;

import com.ricardo.oliveira.padelHubAPI.model.Court;
import com.ricardo.oliveira.padelHubAPI.model.Reservation;
import com.ricardo.oliveira.padelHubAPI.model.User;
import com.ricardo.oliveira.padelHubAPI.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationServiceImpl(ReservationRepository userRepository) {
        this.reservationRepository = userRepository;
    }

    @Override
    public List<Reservation> findAllByClub(User clubOwner) {
        List<Reservation> reservations = new ArrayList<>();

        for (Court court : clubOwner.getClub().getCourts()) {
            reservations.addAll(court.getReservations());
        }

        return reservations;
    }

    @Override
    public Reservation findByIdByClub(User clubOwner, Integer reservationId) {

        return clubOwner.getClub().getCourts().stream()
                .flatMap(court -> court.getReservations().stream())
                .filter(reservation -> reservation.getId() == reservationId)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Did not find reservation id - " + reservationId));
    }
}
