package com.oceanviewresort1.controller;

import com.oceanviewresort1.dao.GuestDAO;
import com.oceanviewresort1.dao.RoomDAO;
import com.oceanviewresort1.dao.impl.GuestDAOImpl;
import com.oceanviewresort1.dao.impl.RoomDAOImpl;
import com.oceanviewresort1.model.Guest;
import com.oceanviewresort1.model.Room;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/reservationForm")
public class ReservationFormServlet extends HttpServlet {

    private GuestDAO guestDAO = new GuestDAOImpl();
    private RoomDAO roomDAO = new RoomDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        try {

            List<Guest> guests = guestDAO.getAllGuests();
            List<Room> rooms = roomDAO.getAllRooms();

            request.setAttribute("guests", guests);
            request.setAttribute("rooms", rooms);

            request.getRequestDispatcher("addReservation.jsp")
                    .forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}