package com.example.aeroseat.model;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Plane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    @Column(nullable = false)
    private String name;

    @NonNull
    @Column(nullable = false)
    private int numberOfSeats;

    @OneToMany(mappedBy = "plane")
    private List<Flight> flights = new ArrayList<>();
}
