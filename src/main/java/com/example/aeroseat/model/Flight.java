package com.example.aeroseat.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.NonNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "departureAirportId", nullable = false)
    private Airport departureAirport;

    @ManyToOne
    @JoinColumn(name = "destinationAirportId", nullable = false)
    private Airport destinationAirport;

    @NonNull
    @Column(nullable = false)
    private float price;

    @Column(nullable = false)
    private LocalDateTime departureTime;

    @Column(nullable = false)
    private LocalDateTime arrivalTime;

    @ManyToOne
    @JoinColumn(name = "plane", nullable = false)
    @JsonManagedReference
    private Plane plane;

    @Column(nullable = false)
    private int occupiedSeats;

    @OneToMany(mappedBy = "flight")
    private List<Seat> seats = new ArrayList<>();
}
