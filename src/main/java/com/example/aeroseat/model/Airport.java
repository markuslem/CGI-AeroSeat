package com.example.aeroseat.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(nullable = false, unique = true)
    private String airportCode;

    @NonNull
    @Column(nullable = false)
    private String cityName;

    @NonNull
    @Column(nullable = false)
    private String airportFullName;

    @NonNull
    @Column(nullable = false)
    private String country;

    @OneToMany(mappedBy = "departureAirport")
    @JsonBackReference
    private List<Flight> departureTo;

    @OneToMany(mappedBy = "destinationAirport")
    @JsonBackReference
    private List<Flight> destinationTo;
}
