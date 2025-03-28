package com.example.aeroseat.repositories;

import com.example.aeroseat.DTOs.SeatDTO;
import com.example.aeroseat.model.Seat;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Transactional
public interface SeatRepository extends JpaRepository<Seat, Long> {
    @Query("SELECT s FROM Seat s WHERE s.flight.id = :flightId ORDER BY s.id")
    public List<Seat> findByFlightId(@Param("flightId") long flightId);

    @Transactional
    @Modifying
    @Query("UPDATE Seat s SET s.isOccupied = true WHERE s.id IN :ids AND s.isOccupied = false")
    public int bookById(@Param("ids") List<Long> ids);

    /* Kui leitakse istekohtade ID-de seast vastav iste, mis on juba broneeritud,
    siis tagastatakse 0-st suurem täisarv.
     */
    @Query("SELECT count(*) FROM Seat s WHERE s.id IN :ids AND s.isOccupied = true")
    public int findAllOccupiedBySeatIds(List<Long> ids);

    @Query("SELECT new com.example.aeroseat.DTOs.SeatDTO(s.id, s.seatRow, s.seatColumn, s.isOccupied) FROM Seat s WHERE s.id IN :ids")
    public List<SeatDTO> findAllBySeatsId(@Param("ids") List<Long> ids);
}
