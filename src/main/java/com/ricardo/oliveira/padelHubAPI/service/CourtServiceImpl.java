package com.ricardo.oliveira.padelHubAPI.service;

import com.ricardo.oliveira.padelHubAPI.dto.request.CourtRequestDTO;
import com.ricardo.oliveira.padelHubAPI.exceptions.NotFoundException;
import com.ricardo.oliveira.padelHubAPI.model.Club;
import com.ricardo.oliveira.padelHubAPI.model.Court;
import com.ricardo.oliveira.padelHubAPI.model.User;
import com.ricardo.oliveira.padelHubAPI.repository.ClubRepository;
import com.ricardo.oliveira.padelHubAPI.repository.CourtRepository;
import com.ricardo.oliveira.padelHubAPI.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.PropertyDescriptor;
import java.util.*;

@Service
public class CourtServiceImpl implements CourtService {

    private final CourtRepository courtRepository;
    private final UserRepository userRepository;
    private final ClubService clubService;

    @Autowired
    public CourtServiceImpl(CourtRepository courtRepository, UserRepository userRepository, ClubService clubService) {
        this.courtRepository = courtRepository;
        this.userRepository = userRepository;
        this.clubService = clubService;
    }

    @Override
    public List<Court> findByClubId(Integer clubId) {
        // Search if clubs exists
        clubService.findById(clubId);

        return courtRepository.findByClub_Id(clubId);
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
            throw new NotFoundException("Did not find court id - " + id);
        }

        return court;
    }

    @Override
    public Court save(CourtRequestDTO courtRequestDTO, User clubOwner) {
        Club club = clubService.findById(clubOwner.getClub().getId());

        Court court = new Court(
            courtRequestDTO.getName(),
            courtRequestDTO.getSurface(),
            courtRequestDTO.getCourtEnvironment()
        );
        court.setClub(club);

        club.addCourt(court);

        clubOwner.setClub(club);

        return userRepository.save(clubOwner).getClub().getCourts().getLast();
    }

    @Override
    public Court update(Integer courtId, CourtRequestDTO courtRequestDTO) {
        Court court = findById(courtId);

        BeanUtils.copyProperties(courtRequestDTO, court, getNullPropertyNames(courtRequestDTO));

        return courtRepository.save(court);
    }

    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
