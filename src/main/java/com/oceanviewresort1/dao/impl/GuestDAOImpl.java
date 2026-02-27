package com.oceanviewresort1.dao.impl;

import com.oceanviewresort1.config.DBConnection;
import com.oceanviewresort1.dao.GuestDAO;
import com.oceanviewresort1.model.Guest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GuestDAOImpl implements GuestDAO {

    @Override
    public boolean addGuest(Guest guest) throws Exception {

        String sql = "INSERT INTO Guests (full_name, address, contact_number, email) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, guest.getFullName());
            stmt.setString(2, guest.getAddress());
            stmt.setString(3, guest.getContactNumber());
            stmt.setString(4, guest.getEmail());

            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public Guest getGuestById(int guestId) throws Exception {

        String sql = "SELECT * FROM Guests WHERE guest_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, guestId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Guest guest = new Guest();
                guest.setGuestId(rs.getInt("guest_id"));
                guest.setFullName(rs.getString("full_name"));
                guest.setAddress(rs.getString("address"));
                guest.setContactNumber(rs.getString("contact_number"));
                guest.setEmail(rs.getString("email"));
                return guest;
            }
        }
        return null;
    }

    @Override
    public List<Guest> getAllGuests() throws Exception {

        List<Guest> guestList = new ArrayList<>();
        String sql = "SELECT * FROM Guests";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Guest guest = new Guest();
                guest.setGuestId(rs.getInt("guest_id"));
                guest.setFullName(rs.getString("full_name"));
                guest.setAddress(rs.getString("address"));
                guest.setContactNumber(rs.getString("contact_number"));
                guest.setEmail(rs.getString("email"));
                guestList.add(guest);
            }
        }
        return guestList;
    }
}