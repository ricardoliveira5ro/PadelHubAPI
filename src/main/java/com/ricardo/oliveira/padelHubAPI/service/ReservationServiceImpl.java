package com.ricardo.oliveira.padelHubAPI.service;

import com.ricardo.oliveira.padelHubAPI.model.Reservation;
import com.ricardo.oliveira.padelHubAPI.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {

    private ReservationRepository ReservationRepository;

    @Autowired
    public ReservationServiceImpl(ReservationRepository userRepository) {
        this.ReservationRepository = userRepository;
    }

    @Override
    public List<Reservation> findAll() {
        return ReservationRepository.findAll();
    }

    @Override
    public Reservation findById(Integer id) {
        Optional<Reservation> result = ReservationRepository.findById(id);

        Reservation reservation;

        if (result.isPresent()) {
            reservation = result.get();
        }
        else {
            throw new RuntimeException("Did not find user id - " + id);
        }

        return reservation;
    }
}
