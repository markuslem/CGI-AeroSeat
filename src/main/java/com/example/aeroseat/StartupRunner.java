package com.example.aeroseat;

import com.example.aeroseat.services.FlightService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupRunner implements CommandLineRunner {

    private final FlightService flightService;

    public StartupRunner(FlightService flightService) {
        this.flightService = flightService;
    }

    @Override
    public void run(String... args) throws Exception {
        flightService.addSeatsToAllFlights();
    }
}
