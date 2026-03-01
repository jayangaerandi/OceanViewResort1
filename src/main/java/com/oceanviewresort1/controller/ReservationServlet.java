package com.oceanviewresort1.controller;

import com.oceanviewresort1.model.Reservation;
import com.oceanviewresort1.service.ReservationService;
import com.oceanviewresort1.service.impl.ReservationServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/addReservation")
public class ReservationServlet extends HttpServlet {

    private ReservationService reservationService;

    @Override
    public void init() {
        reservationService = new ReservationServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        try {

            int guestId = Integer.parseInt(request.getParameter("guestId"));
            int roomId = Integer.parseInt(request.getParameter("roomId"));

            LocalDate checkIn = LocalDate.parse(request.getParameter("checkInDate"));
            LocalDate checkOut = LocalDate.parse(request.getParameter("checkOutDate"));

            Reservation reservation = new Reservation();

            reservation.setGuestId(guestId);
            reservation.setRoomId(roomId);   // only pass roomId

            reservation.setCheckIn(checkIn);
            reservation.setCheckOut(checkOut);

            boolean success = reservationService.createReservation(reservation);

            if (success) {
                request.setAttribute("successMessage", "Reservation created successfully!");
            } else {
                request.setAttribute("errorMessage", "Failed to create reservation.");
            }

            request.getRequestDispatcher("addReservation.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("addReservation.jsp").forward(request, response);
        }
    }
}