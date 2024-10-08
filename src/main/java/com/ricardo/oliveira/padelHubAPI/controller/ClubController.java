package com.ricardo.oliveira.padelHubAPI.controller;

import com.ricardo.oliveira.padelHubAPI.dto.request.ClubRequestDTO;
import com.ricardo.oliveira.padelHubAPI.dto.response.ClubResponseDTO;
import com.ricardo.oliveira.padelHubAPI.dto.response.ClubShortResponseDTO;
import com.ricardo.oliveira.padelHubAPI.dto.response.UserResponseDTO;
import com.ricardo.oliveira.padelHubAPI.exception.RolePrivilegesException;
import com.ricardo.oliveira.padelHubAPI.model.Club;
import com.ricardo.oliveira.padelHubAPI.model.Role;
import com.ricardo.oliveira.padelHubAPI.model.User;
import com.ricardo.oliveira.padelHubAPI.service.ClubService;
import com.ricardo.oliveira.padelHubAPI.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/clubs")
public class ClubController {

    private final ClubService clubService;

    @Autowired
    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ClubShortResponseDTO>> clubs() {
        return ResponseEntity.ok(new ArrayList<>(clubService.findAll().stream().map(ClubShortResponseDTO::new).toList()));
    }

    @GetMapping("/{club_id}")
    public ResponseEntity<ClubShortResponseDTO> club(@PathVariable int club_id) {
        return ResponseEntity.ok(new ClubShortResponseDTO(clubService.findById(club_id)));
    }

    @GetMapping("/my-club")
    public ResponseEntity<ClubResponseDTO> myClub() {
        if (Utils.getCurrentUser().getRole() != Role.CLUB_OWNER)
            throw new RolePrivilegesException("You are authenticated as a " + Utils.getCurrentUser().getRole() + " user. " +
                    "Must be a " + Role.CLUB_OWNER.getValue() + " user to perform this action");

        int clubId = (Utils.getCurrentUser().getClub() != null) ? Utils.getCurrentUser().getClub().getId() : -1;

        Club club = clubService.findById(clubId);
        ClubResponseDTO ClubResponseDTO = new ClubResponseDTO(club);

        return ResponseEntity.ok(ClubResponseDTO);
    }

    @PostMapping("/add-club")
    public ResponseEntity<ClubResponseDTO> addClub(@RequestBody ClubRequestDTO clubRequestDTO) {
        if (Utils.getCurrentUser().getRole() != Role.CLUB_OWNER)
            throw new RolePrivilegesException("You are authenticated as a " + Utils.getCurrentUser().getRole() + " user. " +
                    "Must be a " + Role.CLUB_OWNER.getValue() + " user to perform this action");

        Club club = clubService.save(clubRequestDTO, Utils.getCurrentUser());

        return ResponseEntity.ok(new ClubResponseDTO(club));
    }

    @PutMapping("/update-club")
    public ResponseEntity<ClubResponseDTO> updateClub(@RequestBody ClubRequestDTO clubRequestDTO) {
        if (Utils.getCurrentUser().getRole() != Role.CLUB_OWNER)
            throw new RolePrivilegesException("You are authenticated as a " + Utils.getCurrentUser().getRole() + " user. " +
                    "Must be a " + Role.CLUB_OWNER.getValue() + " user to perform this action");

        Club club = clubService.update(clubRequestDTO, Utils.getCurrentUser());

        return ResponseEntity.ok(new ClubResponseDTO(club));
    }

    @GetMapping("/my-club/players")
    public ResponseEntity<List<UserResponseDTO>> players() {
        if (Utils.getCurrentUser().getRole() != Role.CLUB_OWNER)
            throw new RolePrivilegesException("You are authenticated as a " + Utils.getCurrentUser().getRole() + " user. " +
                    "Must be a " + Role.CLUB_OWNER.getValue() + " user to perform this action");

        List<User> players = clubService.findPlayersWithReservationsInClub(Utils.getCurrentUser());

        return ResponseEntity.ok(new ArrayList<>(players.stream().map(UserResponseDTO::new).toList()));
    }

    @DeleteMapping("/delete-club")
    public ResponseEntity<String> deleteClub() {
        if (Utils.getCurrentUser().getRole() != Role.CLUB_OWNER)
            throw new RolePrivilegesException("You are authenticated as a " + Utils.getCurrentUser().getRole() + " user. " +
                    "Must be a " + Role.CLUB_OWNER.getValue() + " user to perform this action");

        clubService.delete(Utils.getCurrentUser());

        return ResponseEntity.ok("Club deleted successfully");
    }
}
