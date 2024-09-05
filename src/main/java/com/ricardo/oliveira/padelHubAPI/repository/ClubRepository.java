package com.ricardo.oliveira.padelHubAPI.repository;

import com.ricardo.oliveira.padelHubAPI.model.Club;
import com.ricardo.oliveira.padelHubAPI.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClubRepository extends JpaRepository<Club, Integer> {

    @Query("SELECT DISTINCT r.user FROM Reservation r WHERE r.court.club.id = :clubId")
    List<User> findPlayersWithReservationsInClub(@Param("clubId") int clubId);
}
