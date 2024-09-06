package com.ricardo.oliveira.padelHubAPI.service;

import com.ricardo.oliveira.padelHubAPI.model.Court;
import com.ricardo.oliveira.padelHubAPI.model.User;
import com.ricardo.oliveira.padelHubAPI.repository.CourtRepository;
import com.ricardo.oliveira.padelHubAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourtServiceImpl implements CourtService {

    private final CourtRepository courtRepository;

    @Autowired
    public CourtServiceImpl(CourtRepository courtRepository) {
        this.courtRepository = courtRepository;
    }

    @Override
    public List<Court> findByClubId(User clubOwner) {
        if (clubOwner.getClub() == null) {
            return new ArrayList<>();
        }

        return courtRepository.findByClub_Id(clubOwner.getClub().getId());
    }

    @Override
    public Court findById(Integer id) {
        Optional<Court> result = courtRepository.findById(id);

        Court court;

        if (result.isPresent()) {
            court = result.get();
        }
        else {
            throw new RuntimeException("Did not find court id - " + id);
        }

        return court;
    }

    @Override
    public Court save(Court court) {
        return courtRepository.save(court);
    }
}
