package com.ricardo.oliveira.padelHubAPI.controller;

import com.ricardo.oliveira.padelHubAPI.dto.ClubDTO;
import com.ricardo.oliveira.padelHubAPI.model.Club;
import com.ricardo.oliveira.padelHubAPI.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/clubs")
public class ClubController {

    private ClubService clubService;

    @Autowired
    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ClubDTO>> findAll() {
        List<Club> clubs = clubService.findAll();
        List<ClubDTO> clubDTOS = new ArrayList<>();

        for (Club club : clubs) {
            clubDTOS.add(new ClubDTO(club));
        }

        return ResponseEntity.ok(clubDTOS);
    }

    @GetMapping("/{clubId}")
    public ResponseEntity<ClubDTO> findById(@PathVariable int clubId) {
        Club club = clubService.findById(clubId);

        if (club == null) {
            throw new RuntimeException("Club id not found - " + clubId);
        }

        ClubDTO clubDTO = new ClubDTO(club);
        return ResponseEntity.ok(clubDTO);
    }
}
