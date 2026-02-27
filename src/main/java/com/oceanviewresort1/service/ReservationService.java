package com.oceanviewresort1.service;

import com.oceanviewresort1.model.Reservation;

import java.util.List;

public interface ReservationService {

    boolean createReservation(Reservation reservation) throws Exception;

    Reservation searchReservation(String reservationNumber) throws Exception;

    List<Reservation> getAllReservations() throws Exception;
}