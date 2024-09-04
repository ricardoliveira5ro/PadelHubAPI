package com.ricardo.oliveira.padelHubAPI.service;

import com.ricardo.oliveira.padelHubAPI.dto.request.ClubRequestDTO;
import com.ricardo.oliveira.padelHubAPI.model.Club;
import com.ricardo.oliveira.padelHubAPI.model.User;
import com.ricardo.oliveira.padelHubAPI.repository.ClubRepository;
import com.ricardo.oliveira.padelHubAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClubServiceImpl implements ClubService {

    private final ClubRepository clubRepository;
    private final UserRepository userRepository;

    @Autowired
    public ClubServiceImpl(ClubRepository clubRepository, UserRepository userRepository) {
        this.clubRepository = clubRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Club findById(Integer id) {
        Optional<Club> result = clubRepository.findById(id);

        Club club;

        if (result.isPresent()) {
            club = result.get();
        }
        else {
            throw new RuntimeException("Did not find club id - " + id);
        }

        return club;
    }

    @Override
    public Club save(ClubRequestDTO clubRequestDTO, User clubOwner) {
        Club club = new Club(
            clubRequestDTO.getName(),
            clubRequestDTO.getDescription(),
            clubRequestDTO.getAddress(),
            clubRequestDTO.getContactEmail(),
            clubRequestDTO.getContactPhone()
        );

        clubOwner.setClub(club);
        User user = userRepository.save(clubOwner);

        return findById(user.getClub().getId());
    }
}
