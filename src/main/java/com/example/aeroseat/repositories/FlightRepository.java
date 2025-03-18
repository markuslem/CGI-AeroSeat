package com.example.aeroseat.repositories;

import com.example.aeroseat.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {
}
