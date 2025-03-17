package com.example.aeroseat.model;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(nullable = false)
    private String airportCode;

    @NonNull
    @Column(nullable = false)
    private String cityName;

    @NonNull
    @Column(nullable = false)
    private String airportFullName;

    @NonNull
    @Column(nullable = false)
    private String country;

    @OneToMany(mappedBy = "departureAirport")
    private List<Flight> departureTo = new ArrayList<>();

    @OneToMany(mappedBy = "destinationAirport")
    private List<Flight> destinationTo = new ArrayList<>();

    public String getAirportCode() {
        return airportCode;
    }
}
