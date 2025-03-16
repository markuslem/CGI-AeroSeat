package com.example.aeroseat.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class HelloWorldController {
    @GetMapping("/api/message")
    public String getMessage() {
        return "Hello world!";
    }
}
