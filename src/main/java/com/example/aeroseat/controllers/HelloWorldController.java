package com.example.aeroseat.controllers;

import com.example.aeroseat.model.Airport;
import com.example.aeroseat.services.AirportService;
import com.example.aeroseat.services.FlightService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class HelloWorldController {

    @GetMapping("/api/message")
    public String getMessage() {
        return "Hello world!";
    }
}
