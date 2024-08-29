package com.ricardo.oliveira.padelHubAPI.service;

import com.ricardo.oliveira.padelHubAPI.model.Reservation;

import java.util.List;

public interface ReservationService {

    List<Reservation> findAll();

    Reservation findById(Integer id);
}
