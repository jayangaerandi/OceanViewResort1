package com.oceanviewresort1.service.impl;

import java.util.List;
import java.time.temporal.ChronoUnit;

import com.oceanviewresort1.dao.ReservationDAO;
import com.oceanviewresort1.dao.RoomDAO;
import com.oceanviewresort1.dao.impl.ReservationDAOImpl;
import com.oceanviewresort1.dao.impl.RoomDAOImpl;
import com.oceanviewresort1.model.Reservation;
import com.oceanviewresort1.model.Room;
import com.oceanviewresort1.service.ReservationService;

public class ReservationServiceImpl implements ReservationService {

    private ReservationDAO reservationDAO = new ReservationDAOImpl();
    private RoomDAO roomDAO = new RoomDAOImpl();

    @Override
    public boolean createReservation(Reservation reservation) throws Exception {

        // 1️⃣ Load room from database
        Room room = roomDAO.getRoomById(reservation.getRoomId());

        if (room == null) {
            throw new Exception("Room not found");
        }

        reservation.setRoom(room);

        // 2️⃣ Validate dates
        if (reservation.getCheckOut().isBefore(reservation.getCheckIn())) {
            throw new Exception("Check-out date must be after check-in date");
        }

        // 3️⃣ Check room availability
        boolean available = reservationDAO.isRoomAvailable(
                reservation.getRoomId(),
                reservation.getCheckIn(),
                reservation.getCheckOut()
        );

        if (!available) {
            throw new Exception("Room is already booked for the selected dates");
        }

        // 4️⃣ Calculate number of nights
        long nights = ChronoUnit.DAYS.between(
                reservation.getCheckIn(),
                reservation.getCheckOut()
        );

        // 5️⃣ Calculate price
        double pricePerNight = room.getRoomType().getPricePerNight();
        double totalPrice = nights * pricePerNight;

        reservation.setTotalPrice(totalPrice);

        // 6️⃣ Generate reservation number
        String reservationNumber = "RES" + System.currentTimeMillis();
        reservation.setReservationNumber(reservationNumber);

        // 7️⃣ Set reservation status
        reservation.setStatus("CONFIRMED");

        // 8️⃣ Save reservation
        return reservationDAO.createReservation(reservation);
    }

    @Override
    public Reservation searchReservation(String reservationNumber) throws Exception {

        return reservationDAO.getReservationByNumber(reservationNumber);
    }

    @Override
    public List<Reservation> getAllReservations() throws Exception {

        return reservationDAO.getAllReservations();
    }
}