package com.example.aeroseat.DTOs;

import lombok.Getter;

@Getter
public class SeatDTO {
    private final long id;
    private final int seatRow;
    private final char seatColumn;
    private final boolean isOccupied;

    public SeatDTO(long id, int seatRow, char seatColumn, boolean isOccupied) {
        this.id = id;
        this.seatRow = seatRow;
        this.seatColumn = seatColumn;
        this.isOccupied = isOccupied;
    }
}
