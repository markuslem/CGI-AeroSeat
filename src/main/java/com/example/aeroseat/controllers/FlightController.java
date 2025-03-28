package com.example.aeroseat.controllers;


import com.example.aeroseat.DTOs.FlightWithSeatCountDTO;
import com.example.aeroseat.model.Flight;
import com.example.aeroseat.services.FlightService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/api/flights")
    public ResponseEntity<List<FlightWithSeatCountDTO>> getAllFlights() {
        return ResponseEntity.ok(flightService.findAllFlightsWithSeatCount());
    }
}
