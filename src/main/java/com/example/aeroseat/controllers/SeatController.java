package com.example.aeroseat.controllers;


import com.example.aeroseat.model.Flight;
import com.example.aeroseat.model.Seat;
import com.example.aeroseat.services.FlightService;
import com.example.aeroseat.services.SeatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class SeatController {

    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @GetMapping("/api/seats/{flightId}")
    public ResponseEntity<List<Seat>> getAllSeats(@PathVariable Long flightId) {
        return ResponseEntity.ok(seatService.getSeatsByFlightId(flightId));
    }
}
