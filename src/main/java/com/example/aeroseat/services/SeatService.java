package com.example.aeroseat.services;

import com.example.aeroseat.model.Seat;
import com.example.aeroseat.repositories.SeatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {

    private final SeatRepository seatRepository;

    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }
    public List<Seat> getAllSeats() {
        return seatRepository.findAll();
    }
    public List<Seat> getSeatsByFlightId(long flightId) {
        return seatRepository.findByFlightId(flightId);
    }
    public int bookSeats(List<Long> seatIds) {
        System.out.println("Service updating seats " + seatIds);
        return seatRepository.bookById(seatIds);
    }
}
