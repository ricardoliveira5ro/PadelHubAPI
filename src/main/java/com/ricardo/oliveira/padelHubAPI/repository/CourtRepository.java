package com.ricardo.oliveira.padelHubAPI.repository;

import com.ricardo.oliveira.padelHubAPI.model.Court;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourtRepository extends JpaRepository<Court, Integer> {

}
