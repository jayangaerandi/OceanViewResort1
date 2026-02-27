package com.oceanviewresort1.service.impl;

import com.oceanviewresort1.dao.GuestDAO;
import com.oceanviewresort1.dao.impl.GuestDAOImpl;
import com.oceanviewresort1.model.Guest;
import com.oceanviewresort1.service.GuestService;

import java.util.List;

public class GuestServiceImpl implements GuestService {

    private GuestDAO guestDAO = new GuestDAOImpl();

    @Override
    public boolean registerGuest(Guest guest) throws Exception {

        if (guest.getFullName() == null || guest.getFullName().isEmpty()) {
            throw new Exception("Guest name is required");
        }

        if (guest.getContactNumber() == null || guest.getContactNumber().isEmpty()) {
            throw new Exception("Contact number is required");
        }

        return guestDAO.addGuest(guest);
    }

    @Override
    public List<Guest> getAllGuests() throws Exception {
        return guestDAO.getAllGuests();
    }
}