package com.ricardo.oliveira.padelHubAPI.service;

import com.ricardo.oliveira.padelHubAPI.model.Reservation;
import com.ricardo.oliveira.padelHubAPI.model.User;

import java.util.List;

public interface ReservationService {

    List<Reservation> findAllByClub(User clubOwner);

    Reservation findByIdByClub(User clubOwner, Integer reservationId);
}
