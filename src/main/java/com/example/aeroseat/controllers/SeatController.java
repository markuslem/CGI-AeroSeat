package com.example.aeroseat.controllers;


import com.example.aeroseat.model.Seat;
import com.example.aeroseat.services.SeatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class SeatController {

    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @GetMapping("/api/seats/{flightId}")
    public ResponseEntity<List<Seat>> getAllSeats(@PathVariable Long flightId) {
        return ResponseEntity.ok(seatService.getSeatsByFlightId(flightId));
    }

    /**
     * Antud ID-dega istekohti soovitakse broneerida
     * @param seatIds list, mis sisaldab broneeritavaid istekohti
     * @return  Muudetud ridade arv andmebaasis
     */
    @PostMapping("/api/seats/booking")
    public ResponseEntity<Integer> bookSeats(@RequestBody List<Long> seatIds) {
        System.out.println(seatIds);
        try {
            int updatedSeats = seatService.bookSeats(seatIds);
            System.out.println("Updated Seats: " + updatedSeats);
            return ResponseEntity.ok(updatedSeats);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

