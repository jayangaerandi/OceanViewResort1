package com.oceanviewresort1.controller;

import com.oceanviewresort1.model.User;
import com.oceanviewresort1.service.UserService;
import com.oceanviewresort1.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {

            User user = userService.login(username, password);

            if (user != null) {

                // Create session
                HttpSession session = request.getSession();
                session.setAttribute("loggedUser", user);

                response.sendRedirect("dashboard.jsp");

            } else {
                request.setAttribute("errorMessage", "Invalid username or password");
                request.getRequestDispatcher("login.jsp")
                        .forward(request, response);
            }

        } catch (Exception e) {

            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("login.jsp")
                    .forward(request, response);
        }
    }
}