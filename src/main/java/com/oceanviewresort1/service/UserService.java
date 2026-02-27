package com.oceanviewresort1.service;

import com.oceanviewresort1.model.User;

public interface UserService {

    User login(String username, String password) throws Exception;
}