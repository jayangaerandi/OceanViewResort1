package com.oceanviewresort1.dao;

import com.oceanviewresort1.model.Guest;

import java.util.List;

public interface GuestDAO {

    boolean addGuest(Guest guest) throws Exception;

    Guest getGuestById(int guestId) throws Exception;

    List<Guest> getAllGuests() throws Exception;
}