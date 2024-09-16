package com.ricardo.oliveira.padelHubAPI.service;

import com.ricardo.oliveira.padelHubAPI.dto.request.CourtRequestDTO;
import com.ricardo.oliveira.padelHubAPI.model.Court;
import com.ricardo.oliveira.padelHubAPI.model.User;

import java.util.List;

public interface CourtService {
    List<Court> findByClubId(Integer clubId);

    List<Court> findByClubId(User clubOwner);

    Court findById(Integer id);

    Court save(CourtRequestDTO courtRequestDTO, User clubOwner);

    Court update(Integer courtId, CourtRequestDTO courtRequestDTO);

    void delete(User clubOwner, Integer courtId);
}
