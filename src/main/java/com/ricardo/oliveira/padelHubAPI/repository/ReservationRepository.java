package com.ricardo.oliveira.padelHubAPI.repository;

import com.ricardo.oliveira.padelHubAPI.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    List<Reservation> findByCourt_Id(int court_id);
}
