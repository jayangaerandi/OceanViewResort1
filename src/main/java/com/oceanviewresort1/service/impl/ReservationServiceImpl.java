package com.oceanviewresort1.service.impl;

import com.oceanviewresort1.dao.ReservationDAO;
import com.oceanviewresort1.dao.impl.ReservationDAOImpl;
import com.oceanviewresort1.model.Reservation;
import com.oceanviewresort1.service.ReservationService;

import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class ReservationServiceImpl implements ReservationService {

    private ReservationDAO reservationDAO = new ReservationDAOImpl();

    @Override
    public boolean createReservation(Reservation reservation) throws Exception {

        // Validate dates
        if (reservation.getCheckOut().isBefore(reservation.getCheckIn())) {
            throw new Exception("Check-out date cannot be before check-in date");
        }

        // Generate unique reservation number
        reservation.setReservationNumber(generateReservationNumber());

        // Calculate total amount
        long nights = ChronoUnit.DAYS.between(
                reservation.getCheckIn(),
                reservation.getCheckOut()
        );

        double pricePerNight = reservation.getRoom().getRoomType().getPricePerNight();
        reservation.setTotalAmount(nights * pricePerNight);

        reservation.setStatus("Booked");

        return reservationDAO.addReservation(reservation);
    }

    @Override
    public Reservation searchReservation(String reservationNumber) throws Exception {
        return reservationDAO.getReservationByNumber(reservationNumber);
    }

    @Override
    public java.util.List<Reservation> getAllReservations() throws Exception {
        return reservationDAO.getAllReservations();
    }

    private String generateReservationNumber() {
        return "RES-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}