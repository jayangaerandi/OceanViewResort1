package com.oceanviewresort1.controller;

import com.oceanviewresort1.model.Reservation;
import com.oceanviewresort1.service.ReservationService;
import com.oceanviewresort1.service.impl.ReservationServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/invoice")
public class InvoiceServlet extends HttpServlet {

    private ReservationService reservationService;

    @Override
    public void init() {
        reservationService = new ReservationServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String reservationNumber = request.getParameter("reservationNumber");

        try {

            Reservation reservation =
                    reservationService.searchReservation(reservationNumber);

            if (reservation == null) {
                request.setAttribute("errorMessage", "Reservation not found");
                request.getRequestDispatcher("searchReservation.jsp")
                        .forward(request, response);
                return;
            }

            request.setAttribute("reservation", reservation);

            request.getRequestDispatcher("invoice.jsp")
                    .forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}