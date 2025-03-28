package com.example.aeroseat.DTOs;

import com.example.aeroseat.model.Airport;
import com.example.aeroseat.model.Plane;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class FlightWithSeatCountDTO {
    private long id;
    private Airport departureAirport;
    private Airport destinationAirport;
    private float price;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private Plane plane;
    private int occupiedSeats;
    private long totalSeats;

    public FlightWithSeatCountDTO(Long id, Airport departureAirport, Airport destinationAirport,
                                  float price, LocalDateTime departureTime, LocalDateTime arrivalTime,
                                  Plane plane, int occupiedSeats, long totalSeats) {
        this.id = id;
        this.departureAirport = departureAirport;
        this.destinationAirport = destinationAirport;
        this.price = price;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.plane = plane;
        this.occupiedSeats = occupiedSeats;
        this.totalSeats = totalSeats;
    }
}
