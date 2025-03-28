package com.example.aeroseat.repositories;

import com.example.aeroseat.DTOs.FlightWithSeatCountDTO;
import com.example.aeroseat.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    @Query("SELECT new com.example.aeroseat.DTOs.FlightWithSeatCountDTO(f.id, f.departureAirport, f.destinationAirport, " +
            "f.price, f.departureTime, f.arrivalTime, " +
            "f.plane, f.occupiedSeats, COUNT(s)) " +
            "FROM Flight f " +
            "JOIN f.seats s " +
            "GROUP BY f.id, f.departureAirport, f.destinationAirport, " +
            "f.price, f.departureTime, f.arrivalTime, " +
            "f.plane, f.occupiedSeats")
    public List<FlightWithSeatCountDTO> findAllWithSeatCount();
}
