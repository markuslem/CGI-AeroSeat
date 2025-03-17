package com.example.aeroseat.services;

import com.example.aeroseat.repositories.AirportRepository;
import org.springframework.stereotype.Service;


@Service
public class AirportService {
    private final AirportRepository airportRepository;
    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public String[] getAllAirportCodes() {
        return airportRepository.findAllAirportCodes();
    }

}
