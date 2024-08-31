package com.ricardo.oliveira.padelHubAPI.service;

import com.ricardo.oliveira.padelHubAPI.model.Club;

import java.util.List;

public interface ClubService {

    List<Club> findAll();

    Club findById(Integer id);

    Club save(Club club);
}
