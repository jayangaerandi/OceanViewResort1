package com.oceanviewresort1.dao;

import com.oceanviewresort1.model.User;

public interface UserDAO {

    User login(String username, String password) throws Exception;

    boolean addUser(User user) throws Exception;
}