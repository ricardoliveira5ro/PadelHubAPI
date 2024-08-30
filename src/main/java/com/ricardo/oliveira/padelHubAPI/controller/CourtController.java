package com.ricardo.oliveira.padelHubAPI.controller;

import com.ricardo.oliveira.padelHubAPI.dto.CourtDTO;
import com.ricardo.oliveira.padelHubAPI.model.Court;
import com.ricardo.oliveira.padelHubAPI.service.CourtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/courts")
public class CourtController {

    private CourtService courtService;

    @Autowired
    public CourtController(CourtService courtService) {
        this.courtService = courtService;
    }

    @GetMapping("/")
    public ResponseEntity<List<CourtDTO>> findAll() {
        List<Court> courts = courtService.findAll();
        List<CourtDTO> courtDTOS = new ArrayList<>();

        for (Court court : courts) {
            courtDTOS.add(new CourtDTO(court));
        }

        return ResponseEntity.ok(courtDTOS);
    }

    @GetMapping("/{courtId}")
    public ResponseEntity<CourtDTO> findById(@PathVariable int courtId) {
        Court court = courtService.findById(courtId);

        if (court == null) {
            throw new RuntimeException("Court id not found - " + courtId);
        }

        CourtDTO courtDTO = new CourtDTO(court);
        return ResponseEntity.ok(courtDTO);
    }
}
