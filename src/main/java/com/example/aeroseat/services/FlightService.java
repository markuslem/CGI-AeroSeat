package com.example.aeroseat.services;

import com.example.aeroseat.DTOs.FlightWithSeatCountDTO;
import com.example.aeroseat.model.Flight;
import com.example.aeroseat.model.Seat;
import com.example.aeroseat.repositories.FlightRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {
    private final FlightRepository flightRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    /**
     * Tagastab kogu info lennu kohta välja arvatud istekohad.
     * Täpsustatakse istekohtade arv.
     */
    public List<FlightWithSeatCountDTO> findAllFlightsWithSeatCount() {
        return flightRepository.findAllWithSeatCount();
    }

    /**
     * Lisab kõikidele andmebaasis olevate lendude istekohad vastavalt sellele, kui palju on
     * lennukis ruumi.
     */
    @Transactional
    public void addSeatsToAllFlights() {
        List<Flight> flights = entityManager.createQuery("SELECT f FROM Flight f", Flight.class).getResultList();

        for (Flight flight : flights) {
            int totalSeats = flight.getPlane().getNumberOfSeats();
            for (int i = 0; i <= totalSeats; i++) {
                Seat seat = new Seat();
                char column = switch (i % 4 + 1) {
                    case 1 -> 'A';
                    case 2 -> 'B';
                    case 3 -> 'C';
                    default -> 'D';
                };
                seat.setSeatRow(Math.floorDiv(i, 4) + 1);
                seat.setSeatColumn(column);
                seat.setFlight(flight);
                seat.setOccupied(Math.random() > 0.5);
                entityManager.persist(seat);
            }
        }
    }
}
