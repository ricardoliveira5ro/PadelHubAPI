package com.ricardo.oliveira.padelHubAPI.service;

import com.ricardo.oliveira.padelHubAPI.dto.request.ClubRequestDTO;
import com.ricardo.oliveira.padelHubAPI.dto.request.CourtRequestDTO;
import com.ricardo.oliveira.padelHubAPI.exception.NotFoundException;
import com.ricardo.oliveira.padelHubAPI.model.Club;
import com.ricardo.oliveira.padelHubAPI.model.Court;
import com.ricardo.oliveira.padelHubAPI.model.User;
import com.ricardo.oliveira.padelHubAPI.repository.ClubRepository;
import com.ricardo.oliveira.padelHubAPI.repository.UserRepository;
import com.ricardo.oliveira.padelHubAPI.utils.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.PropertyDescriptor;
import java.util.*;

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
            throw new NotFoundException("Did not find club id - " + id);
        }

        return club;
    }

    @Override
    public Club save(ClubRequestDTO clubRequestDTO, User clubOwner) {
        Utils.validateClubDTO(clubRepository, clubRequestDTO);

        Club club = new Club(
            clubRequestDTO.getName(),
            clubRequestDTO.getDescription(),
            clubRequestDTO.getAddress(),
            clubRequestDTO.getContactEmail(),
            clubRequestDTO.getContactPhone()
        );

        for (CourtRequestDTO courtRequestDTO : clubRequestDTO.getCourts()) {
            Court court = new Court(
                courtRequestDTO.getName(),
                courtRequestDTO.getSurface(),
                courtRequestDTO.getCourtEnvironment()
            );

            club.addCourt(court);
        }

        clubOwner.setClub(club);
        User user = userRepository.save(clubOwner);

        return findById(user.getClub().getId());
    }

    @Override
    public Club update(ClubRequestDTO clubRequestDTO, User clubOwner) {
        Club club = findById(clubOwner.getClub().getId());

        BeanUtils.copyProperties(clubRequestDTO, club, Utils.getNullPropertyNames(clubRequestDTO));

        return clubRepository.save(club);
    }

    @Override
    public List<User> findPlayersWithReservationsInClub(User clubOwner) {
        List<User> players = new ArrayList<>();;

        if (clubOwner.getClub() == null)
            return players;

        return clubRepository.findPlayersWithReservationsInClub(clubOwner.getClub().getId());
    }
}
