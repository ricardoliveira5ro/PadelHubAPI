package com.ricardo.oliveira.padelHubAPI.service;

import com.ricardo.oliveira.padelHubAPI.dto.request.CourtRequestDTO;
import com.ricardo.oliveira.padelHubAPI.exception.NotFoundException;
import com.ricardo.oliveira.padelHubAPI.model.Club;
import com.ricardo.oliveira.padelHubAPI.model.Court;
import com.ricardo.oliveira.padelHubAPI.model.Reservation;
import com.ricardo.oliveira.padelHubAPI.model.User;
import com.ricardo.oliveira.padelHubAPI.repository.CourtRepository;
import com.ricardo.oliveira.padelHubAPI.repository.ReservationRepository;
import com.ricardo.oliveira.padelHubAPI.repository.UserRepository;
import com.ricardo.oliveira.padelHubAPI.utils.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CourtServiceImpl implements CourtService {

    private final CourtRepository courtRepository;
    private final UserRepository userRepository;
    private final ClubService clubService;
    private final ReservationRepository reservationRepository;

    @Autowired
    public CourtServiceImpl(CourtRepository courtRepository, UserRepository userRepository, ClubService clubService, ReservationRepository reservationRepository) {
        this.courtRepository = courtRepository;
        this.userRepository = userRepository;
        this.clubService = clubService;
        this.reservationRepository = reservationRepository;
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
        Utils.validateCourtDTO(courtRequestDTO);

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

        BeanUtils.copyProperties(courtRequestDTO, court, Utils.getNullPropertyNames(courtRequestDTO));

        return courtRepository.save(court);
    }

    @Override
    public void delete(User clubOwner, Integer courtId) {
        if (clubOwner.getClub() == null)
            throw new NotFoundException("No club and courts available");

        Club club = clubOwner.getClub();
        Court court = club.getCourts().stream()
                .filter(c -> c.getId() == courtId)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Did not find court id - " + courtId));

        if (court.getReservations() != null && !court.getReservations().isEmpty()) {
            for (Reservation reservation : court.getReservations()) {
                reservationRepository.deleteById(reservation.getId());
                clubOwner.removeReservation(reservation);
            }
        }

        club.removeCourt(court);

        userRepository.save(clubOwner);

        courtRepository.deleteById(courtId);
    }
}
