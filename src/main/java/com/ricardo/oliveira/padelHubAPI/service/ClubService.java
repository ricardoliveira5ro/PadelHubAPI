package com.ricardo.oliveira.padelHubAPI.service;

import com.ricardo.oliveira.padelHubAPI.dto.request.ClubRequestDTO;
import com.ricardo.oliveira.padelHubAPI.model.Club;
import com.ricardo.oliveira.padelHubAPI.model.User;

public interface ClubService {

    Club findById(Integer id);

    Club save(ClubRequestDTO clubRequestDTO, User clubOwner);
}
