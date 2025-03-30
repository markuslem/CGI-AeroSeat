package com.example.aeroseat.controllers;


import com.example.aeroseat.DTOs.AirportCityWithIdDTO;
import com.example.aeroseat.services.AirportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class AirportController {

    private final AirportService airportService;

    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }


    @GetMapping("/api/airport-names")
    public ResponseEntity<List<AirportCityWithIdDTO>> getAirportNames() {
        List<AirportCityWithIdDTO> airports = airportService.getAllCities();
        return ResponseEntity.ok(airports);
    }
}
