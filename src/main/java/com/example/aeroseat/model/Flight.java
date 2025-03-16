package com.example.aeroseat.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;

@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "departureAirportId", nullable = false)
    private Airport departureAirport;

    @ManyToOne
    @JoinColumn(name = "destinationAirportId", nullable = false)
    private Airport destinationAirport;

    @NonNull
    @Column(nullable = false)
    private float price;

    @NonNull
    @Column(nullable = false)
    private LocalDateTime departureTime;

    @NonNull
    @Column(nullable = false)
    private LocalDateTime arrivalTime;

    @ManyToOne
    @JoinColumn(name = "plane", nullable = false)
    private Plane plane;

    @NonNull
    @Column(nullable = false)
    private int occupiedSeats;
}
