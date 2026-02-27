package com.oceanviewresort1.service.impl;

import com.oceanviewresort1.dao.UserDAO;
import com.oceanviewresort1.dao.impl.UserDAOImpl;
import com.oceanviewresort1.model.User;
import com.oceanviewresort1.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO = new UserDAOImpl();

    @Override
    public User login(String username, String password) throws Exception {

        // Basic validation
        if (username == null || username.isEmpty()) {
            throw new Exception("Username cannot be empty");
        }

        if (password == null || password.isEmpty()) {
            throw new Exception("Password cannot be empty");
        }

        return userDAO.login(username, password);
    }
}