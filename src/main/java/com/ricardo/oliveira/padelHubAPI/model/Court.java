package com.ricardo.oliveira.padelHubAPI.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "court")
public class Court {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "surface")
    private String surface;

    @Column(name = "court_environment")
    private String courtEnvironment;

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinColumn(name = "club_id")
    private Club club;

    @OneToMany(
        mappedBy = "court",
        fetch = FetchType.LAZY,
        cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH }
    )
    private List<Reservation> reservations;

    // TODO: created_at

    // TODO: updated_at

    public Court() {}

    public Court(String name, String surface, String courtEnvironment) {
        this.name = name;
        this.surface = surface;
        this.courtEnvironment = courtEnvironment;
    }

    public void addReservation(Reservation reservation) {
        if (reservations == null) {
            reservations = new ArrayList<>();
        }
        reservations.add(reservation);

        reservation.setCourt(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurface() {
        return surface;
    }

    public void setSurface(String surface) {
        this.surface = surface;
    }

    public String getCourtEnvironment() {
        return courtEnvironment;
    }

    public void setCourtEnvironment(String courtEnvironment) {
        this.courtEnvironment = courtEnvironment;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    @Override
    public String toString() {
        return "Court{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surface='" + surface + '\'' +
                ", courtEnvironment='" + courtEnvironment + '\'' +
                '}';
    }
}
