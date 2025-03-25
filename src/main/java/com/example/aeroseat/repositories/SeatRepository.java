package com.example.aeroseat.repositories;

import com.example.aeroseat.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    @Query("SELECT s FROM Seat s WHERE s.flight.id = :flightId")
    public List<Seat> findByFlightId(@Param("flightId") long flightId);
}
