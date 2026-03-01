package com.oceanviewresort1.service.impl;
import java.util.List;
import com.oceanviewresort1.dao.ReservationDAO;
import com.oceanviewresort1.dao.RoomDAO;
import com.oceanviewresort1.dao.impl.ReservationDAOImpl;
import com.oceanviewresort1.dao.impl.RoomDAOImpl;
import com.oceanviewresort1.model.Reservation;
import com.oceanviewresort1.model.Room;
import com.oceanviewresort1.service.ReservationService;

import java.time.temporal.ChronoUnit;

public class ReservationServiceImpl implements ReservationService {

    private ReservationDAO reservationDAO = new ReservationDAOImpl();
    private RoomDAO roomDAO = new RoomDAOImpl();

    @Override
    public boolean createReservation(Reservation reservation) throws Exception {

        Room room = roomDAO.getRoomById(reservation.getRoomId());

        if (room == null) {
            throw new Exception("Room not found");
        }

        reservation.setRoom(room);

        if (reservation.getCheckOut().isBefore(reservation.getCheckIn())) {
            throw new Exception("Check-out date must be after check-in date");
        }

        long nights = ChronoUnit.DAYS.between(reservation.getCheckIn(), reservation.getCheckOut());

        double pricePerNight = room.getRoomType().getPricePerNight();
        double totalPrice = nights * pricePerNight;

        reservation.setTotalPrice(totalPrice);

        // ⭐ Generate reservation number
        String reservationNumber = "RES" + System.currentTimeMillis();
        reservation.setReservationNumber(reservationNumber);

        reservation.setStatus("CONFIRMED");

        return reservationDAO.createReservation(reservation);
    }

    @Override
    public Reservation searchReservation(String reservationNumber) throws Exception {
        return null;
    }

    @Override
    public List<Reservation> getAllReservations() throws Exception {
        return reservationDAO.getAllReservations();
    }
}