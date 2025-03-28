package com.example.aeroseat.services;

import com.example.aeroseat.DTOs.SeatDTO;
import com.example.aeroseat.exceptions.SeatUpdateException;
import com.example.aeroseat.model.Seat;
import com.example.aeroseat.repositories.SeatRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeatService {

    private final SeatRepository seatRepository;

    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    /**
     * Parameetriks antud ID põhjal tagastatakse kõik sellel lennul olevad istekohad
     *
     * @param flightId Lend, mille istekohti otsitakse
     * @return List istekohtadest, mis kuuluvad sellele lennule
     */
    public List<Seat> getSeatsByFlightId(long flightId) {
        return seatRepository.findByFlightId(flightId);
    }

    /**
     * Istekohtade broneerimine ID-de põhjal.
     * Kui vähemalt 1 istekoht nende istekohtade seast on juba broneeritud, siis katkestame broneeringu.
     * Kui mõni istekoht ei leidu andmebaasis, siis katkestame broneeringu.
     * See meetod on "synchronized", mis tähendab, et töödeltakse ainult 1 päringut korraga.
     *
     * @param seatIds Kõikide istekohtade ID-d, mida soovitakse broneerida
     * @return Muudetud ridade arv
     */
    public synchronized List<SeatDTO> bookSeats(List<Long> seatIds) {
        System.out.println("Service updating seats " + seatIds);
        List<SeatDTO> updatedSeats;
        if (seatRepository.findAllOccupiedBySeatIds(seatIds) > 0) {
            throw new SeatUpdateException("A seat in the list is already occupied."); // Mõni istekoht on juba broneeritud
        } else if ((updatedSeats = seatRepository.findAllBySeatsId(seatIds)).size() == seatIds.size()) {
            System.out.println("Started updating seats " + updatedSeats);
            seatRepository.bookById(seatIds);
            return updatedSeats;
        } else {
            // Paremeetriks antud ID-de listis oli ID, mida ei ole andmebaasis
            throw new SeatUpdateException("Couldn't find a seat with matching ID.");
        }
    }
}
