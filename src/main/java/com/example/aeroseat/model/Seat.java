package com.example.aeroseat.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

//    @Column(nullable = false)
//    private String seatNumber;

    @Column(nullable = false)
    private int seatRow;

    @Column(nullable = false)
    private char seatColumn;

    @ManyToOne
    @JoinColumn(name = "flight", nullable = false)
    @JsonBackReference
    private Flight flight;

    @Column(nullable = false)
    private boolean isOccupied; // true, kui istekoht on hõivatud
}
