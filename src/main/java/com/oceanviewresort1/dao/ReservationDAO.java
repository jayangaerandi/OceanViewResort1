package com.oceanviewresort1.dao;

import com.oceanviewresort1.model.Reservation;

import java.util.List;

public interface ReservationDAO {

    boolean addReservation(Reservation reservation) throws Exception;

    Reservation getReservationByNumber(String reservationNumber) throws Exception;

    List<Reservation> getAllReservations() throws Exception;
}