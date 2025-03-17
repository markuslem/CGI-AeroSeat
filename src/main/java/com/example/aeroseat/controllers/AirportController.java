package com.example.aeroseat.controllers;


import com.example.aeroseat.services.AirportService;
import com.example.aeroseat.services.FlightService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class AirportController {

    private final AirportService airportService;

    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @GetMapping("/api/flight-codes")
    public ResponseEntity<String[]> getAllAirportCodes() {
        String[] airportCodes = airportService.getAllAirportCodes();
        return ResponseEntity.ok(airportCodes);
    }
}
