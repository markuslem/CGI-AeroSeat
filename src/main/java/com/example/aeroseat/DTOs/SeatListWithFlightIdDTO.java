package com.example.aeroseat.DTOs;

import lombok.Getter;

import java.util.List;

@Getter
public class SeatListWithFlightIdDTO {
    private final List<SeatDTO> seats;

    public SeatListWithFlightIdDTO(List<SeatDTO> seats, long id) {
        this.seats = seats;
    }
}
