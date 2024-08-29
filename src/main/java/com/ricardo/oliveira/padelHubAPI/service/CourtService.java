package com.ricardo.oliveira.padelHubAPI.service;

import com.ricardo.oliveira.padelHubAPI.model.Court;
import java.util.List;

public interface CourtService {

    List<Court> findAll();

    Court findById(Integer id);
}
