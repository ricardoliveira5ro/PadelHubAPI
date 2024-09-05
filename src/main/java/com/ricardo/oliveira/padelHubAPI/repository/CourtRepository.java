package com.ricardo.oliveira.padelHubAPI.repository;

import com.ricardo.oliveira.padelHubAPI.model.Court;
import com.ricardo.oliveira.padelHubAPI.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourtRepository extends JpaRepository<Court, Integer> {

    List<Court> findByClub_Id(int clubId);
}
