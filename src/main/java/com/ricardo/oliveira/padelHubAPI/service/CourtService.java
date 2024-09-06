package com.ricardo.oliveira.padelHubAPI.service;

import com.ricardo.oliveira.padelHubAPI.model.Court;
import com.ricardo.oliveira.padelHubAPI.model.User;

import java.util.List;

public interface CourtService {

    List<Court> findByClubId(User clubOwner);

    Court findById(Integer id);

    Court save(Court court);
}
