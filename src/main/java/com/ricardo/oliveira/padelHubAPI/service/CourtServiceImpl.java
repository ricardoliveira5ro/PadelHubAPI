package com.ricardo.oliveira.padelHubAPI.service;

import com.ricardo.oliveira.padelHubAPI.model.Court;
import com.ricardo.oliveira.padelHubAPI.repository.CourtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourtServiceImpl implements CourtService {

    private CourtRepository courtRepository;

    @Autowired
    public CourtServiceImpl(CourtRepository courtRepository) {
        this.courtRepository = courtRepository;
    }

    @Override
    public List<Court> findAll() {
        return courtRepository.findAll();
    }

    @Override
    public Court findById(Integer id) {
        Optional<Court> result = courtRepository.findById(id);

        Court court;

        if (result.isPresent()) {
            court = result.get();
        }
        else {
            throw new RuntimeException("Did not find user id - " + id);
        }

        return court;
    }
}
