package com.example.aeroseat.repositories;

import com.example.aeroseat.DTOs.AirportCityWithIdDTO;
import com.example.aeroseat.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {


    @Query("SELECT new com.example.aeroseat.DTOs.AirportCityWithIdDTO(a.id, a.cityName) FROM Airport a")
    public List<AirportCityWithIdDTO> airportCityWithIds();
}
