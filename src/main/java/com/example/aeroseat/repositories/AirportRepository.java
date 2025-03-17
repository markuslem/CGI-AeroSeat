package com.example.aeroseat.repositories;

import com.example.aeroseat.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {

    @Query("SELECT a.airportCode FROM Airport a")
    public String[] findAllAirportCodes();
}
