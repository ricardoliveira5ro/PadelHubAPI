package com.ricardo.oliveira.padelHubAPI.service;

import com.ricardo.oliveira.padelHubAPI.model.Club;
import com.ricardo.oliveira.padelHubAPI.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClubServiceImpl implements ClubService {

    private final ClubRepository clubRepository;

    @Autowired
    public ClubServiceImpl(ClubRepository userRepository) {
        this.clubRepository = userRepository;
    }

    @Override
    public List<Club> findAll() {
        return clubRepository.findAll();
    }

    @Override
    public Club findById(Integer id) {
        Optional<Club> result = clubRepository.findById(id);

        Club club;

        if (result.isPresent()) {
            club = result.get();
        }
        else {
            throw new RuntimeException("Did not find user id - " + id);
        }

        return club;
    }

    @Override
    public Club save(Club club) {
        return clubRepository.save(club);
    }
}
