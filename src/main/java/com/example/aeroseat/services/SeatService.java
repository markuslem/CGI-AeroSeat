package com.example.aeroseat.services;

import com.example.aeroseat.model.Seat;
import com.example.aeroseat.repositories.SeatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {

    private final SeatRepository seatRepository;

    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }
    public List<Seat> getAllSeats() {
        return seatRepository.findAll();
    }

    /**
     * Parameetriks antud ID põhjal tagastatakse kõik sellel lennul olevad istekohad
     * @param flightId Lend, mille istekohti otsitakse
     * @return  List istekohtadest, mis kuuluvad sellele lennule
     */
    public List<Seat> getSeatsByFlightId(long flightId) {
        return seatRepository.findByFlightId(flightId);
    }

    /**
     * Istekohtade broneerimine ID-de põhjal.
     * Kui vähemalt 1 istekoht nende istekohtade seast on juba broneeritud, siis katkestame broneeringu.
     * @param seatIds Kõikide istekohtade ID-d, mida soovitakse broneerida
     * @return Muudetud ridade arv
     */
    public int bookSeats(List<Long> seatIds) {
        System.out.println("Service updating seats " + seatIds);
        if (seatRepository.findAllBySeatIds(seatIds) > 0) {
            System.out.println("A seat is already booked");
            return -1; // Mõni istekoht on juba broneeritud
        } else {
            return seatRepository.bookById(seatIds);
        }
    }
}
