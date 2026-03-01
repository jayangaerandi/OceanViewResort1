package com.oceanviewresort1.controller;

import com.oceanviewresort1.model.Guest;
import com.oceanviewresort1.service.GuestService;
import com.oceanviewresort1.service.impl.GuestServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/addGuest")
public class GuestServlet extends HttpServlet {

    private GuestService guestService = new GuestServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String fullName = request.getParameter("fullName");
        String address = request.getParameter("address");
        String contactNumber = request.getParameter("contactNumber");
        String email = request.getParameter("email");

        try {

            Guest guest = new Guest();
            guest.setFullName(fullName);
            guest.setAddress(address);
            guest.setContactNumber(contactNumber);
            guest.setEmail(email);

            boolean success = guestService.registerGuest(guest);

            if (success) {
                request.setAttribute("successMessage", "Guest registered successfully!");
            } else {
                request.setAttribute("errorMessage", "Failed to register guest.");
            }

            request.getRequestDispatcher("addGuest.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error registering guest.");
            request.getRequestDispatcher("addGuest.jsp").forward(request, response);
        }
    }
}