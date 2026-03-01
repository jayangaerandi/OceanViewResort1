package com.oceanviewresort1.controller;

import com.oceanviewresort1.model.Reservation;
import com.oceanviewresort1.service.ReservationService;
import com.oceanviewresort1.service.impl.ReservationServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/reservations")
public class ReservationListServlet extends HttpServlet {

    private ReservationService reservationService;

    @Override
    public void init() {
        reservationService = new ReservationServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        try {

            List<Reservation> reservations = reservationService.getAllReservations();

            request.setAttribute("reservations", reservations);

            request.getRequestDispatcher("reservations.jsp")
                    .forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}