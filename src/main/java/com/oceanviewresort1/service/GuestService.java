package com.oceanviewresort1.service;

import com.oceanviewresort1.model.Guest;

import java.util.List;

public interface GuestService {

    boolean registerGuest(Guest guest) throws Exception;

    List<Guest> getAllGuests() throws Exception;
}