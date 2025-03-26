package com.example.aeroseat.repositories;

import com.example.aeroseat.model.Seat;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Transactional
public interface SeatRepository extends JpaRepository<Seat, Long> {
    @Query("SELECT s FROM Seat s WHERE s.flight.id = :flightId")
    public List<Seat> findByFlightId(@Param("flightId") long flightId);

    @Modifying
    @Query("UPDATE Seat s SET s.isOccupied = true WHERE s.id IN :ids AND s.isOccupied = false")
    public int bookById(@Param("ids") List<Long> ids);
}
