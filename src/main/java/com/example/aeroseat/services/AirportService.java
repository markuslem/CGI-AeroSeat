package com.example.aeroseat.services;

import com.example.aeroseat.DTOs.AirportCityWithIdDTO;
import com.example.aeroseat.repositories.AirportRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AirportService {
    private final AirportRepository airportRepository;
    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public String[] getAllAirportCodes() {
        return airportRepository.findAllAirportCodes();
    }

    public List<AirportCityWithIdDTO> getAllCities() {
        return airportRepository.airportCityWithIds();
    }

}
