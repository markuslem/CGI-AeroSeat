package com.example.aeroseat.services;

import com.example.aeroseat.model.Airport;
import com.example.aeroseat.repositories.AirportRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AirportService {
    private final AirportRepository airportRepository;
    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public String[] getAllAirportCodes() {
        return airportRepository.findAll()
                .stream()
                .map(Airport::getAirportCode)
                .toArray(String[]::new);
    }

}
