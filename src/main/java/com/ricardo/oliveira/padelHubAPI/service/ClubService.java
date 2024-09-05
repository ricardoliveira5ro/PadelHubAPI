package com.ricardo.oliveira.padelHubAPI.service;

import com.ricardo.oliveira.padelHubAPI.dto.request.ClubRequestDTO;
import com.ricardo.oliveira.padelHubAPI.model.Club;
import com.ricardo.oliveira.padelHubAPI.model.User;

import java.util.List;

public interface ClubService {

    Club findById(Integer id);

    Club save(ClubRequestDTO clubRequestDTO, User clubOwner);

    Club update(ClubRequestDTO clubRequestDTO, User clubOwner);

    List<User> findPlayersWithReservationsInClub(User clubOwner);
}
