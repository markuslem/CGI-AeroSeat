package com.example.aeroseat.DTOs;

import lombok.Getter;

@Getter
public class AirportCityWithIdDTO {
    private long id;
    private String cityName;

    public AirportCityWithIdDTO(long id, String cityName) {
        this.id = id;
        this.cityName = cityName;
    }
}
