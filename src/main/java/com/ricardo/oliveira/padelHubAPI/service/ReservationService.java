package com.ricardo.oliveira.padelHubAPI.service;

import com.ricardo.oliveira.padelHubAPI.dto.request.ReservationRequestDTO;
import com.ricardo.oliveira.padelHubAPI.dto.request.ReservationStatusRequestDTO;
import com.ricardo.oliveira.padelHubAPI.model.Court;
import com.ricardo.oliveira.padelHubAPI.model.Reservation;
import com.ricardo.oliveira.padelHubAPI.model.User;

import java.util.List;

public interface ReservationService {

    List<Reservation> findAllByClub(User clubOwner);

    Reservation findByIdByClub(User clubOwner, Integer reservationId);

    List<Reservation> findAllGames(User player);

    Reservation save(User player, ReservationRequestDTO reservationRequestDTO);

    Reservation changeStatus(User user, Integer reservationId, ReservationStatusRequestDTO reservationStatusRequestDTO);

    void delete(Integer reservationId);
}
