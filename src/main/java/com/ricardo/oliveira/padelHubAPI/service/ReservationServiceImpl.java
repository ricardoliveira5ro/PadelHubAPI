package com.ricardo.oliveira.padelHubAPI.service;

import com.ricardo.oliveira.padelHubAPI.dto.request.ReservationRequestDTO;
import com.ricardo.oliveira.padelHubAPI.dto.request.ReservationStatusRequestDTO;
import com.ricardo.oliveira.padelHubAPI.exception.NotFoundException;
import com.ricardo.oliveira.padelHubAPI.model.*;
import com.ricardo.oliveira.padelHubAPI.repository.CourtRepository;
import com.ricardo.oliveira.padelHubAPI.repository.ReservationRepository;
import com.ricardo.oliveira.padelHubAPI.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final CourtRepository courtRepository;
    private final CourtService courtService;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository, CourtRepository courtRepository, CourtService courtService) {
        this.reservationRepository = reservationRepository;
        this.courtRepository = courtRepository;
        this.courtService = courtService;
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
                .orElseThrow(() -> new NotFoundException("Did not find reservation id - " + reservationId));
    }

    @Override
    public List<Reservation> findAllGames(User player) {
        return reservationRepository.findByUser_Id(player.getId());
    }

    @Override
    public Reservation save(User player, ReservationRequestDTO reservationRequestDTO) {
        Utils.validateReservationDTO(reservationRequestDTO);

        Court court = courtService.findById(Integer.parseInt(reservationRequestDTO.getCourtId()));

        Reservation reservation = new Reservation(
            Utils.formatDateTime(reservationRequestDTO.getReservationStartTime()),
            Utils.formatDateTime(reservationRequestDTO.getReservationEndTime()),
            ReservationStatus.PENDING
        );

        reservation.setCourt(court);
        reservation.setUser(player);

        player.addReservation(reservation);

        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation changeStatus(User user, Integer reservationId, ReservationStatusRequestDTO reservationStatusRequestDTO) {
        Reservation reservation;

        if (user.getRole() == Role.CLUB_OWNER) {
            reservation = findByIdByClub(user, reservationId);

        } else {
            reservation = findById(reservationId);

            if (user.getRole() == Role.PLAYER && reservation.getUser().getId() != user.getId())
                throw new NotFoundException("Did not find reservation id - " + reservationId);
        }

        reservation.setStatus(ReservationStatus.valueOf(reservationStatusRequestDTO.getStatus().toUpperCase()));

        return reservationRepository.save(reservation);
    }

    @Override
    public void delete(User user, Integer reservationId) {
        Reservation reservation;

        if (user.getRole() == Role.CLUB_OWNER) {
            reservation = findByIdByClub(user, reservationId);

        } else {
            reservation = findById(reservationId);

            if (user.getRole() == Role.PLAYER && reservation.getUser().getId() != user.getId())
                throw new NotFoundException("Did not find reservation id - " + reservationId);
        }

        Court court = courtRepository.findById(reservation.getCourt().getId()).orElseThrow();

        user.removeReservation(reservation);
        court.removeReservation(reservation);

        reservationRepository.deleteById(reservationId);
    }

    private Reservation findById(Integer reservationId) {
        Optional<Reservation> result = reservationRepository.findById(reservationId);

        Reservation reservation;

        if (result.isPresent()) {
            reservation = result.get();
        }
        else {
            throw new NotFoundException("Did not find reservation id - " + reservationId);
        }

        return reservation;
    }
}
