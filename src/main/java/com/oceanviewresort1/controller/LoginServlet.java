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

    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserServiceImpl();
    }

    // Handle GET request
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("login.jsp")
                .forward(request, response);
    }

    // Handle POST request
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {

            System.out.println("=== LOGIN ATTEMPT ===");
            System.out.println("Username entered: " + username);
            System.out.println("Password entered: " + password);

            User user = userService.login(username, password);

            System.out.println("User found in DB: " + user);

            if (user != null) {

                System.out.println("Login SUCCESS for: " + user.getUsername());

                HttpSession session = request.getSession(true);
                session.setAttribute("loggedUser", user);

                response.sendRedirect(request.getContextPath() + "/dashboard.jsp");

            } else {

                System.out.println("Login FAILED - user is null");

                request.setAttribute("errorMessage",
                        "Invalid username or password");
                request.getRequestDispatcher("login.jsp")
                        .forward(request, response);
            }

        } catch (Exception e) {

            System.out.println("ERROR during login:");
            e.printStackTrace();

            request.setAttribute("errorMessage",
                    "System error occurred. Check console.");
            request.getRequestDispatcher("login.jsp")
                    .forward(request, response);
        }
    }
}