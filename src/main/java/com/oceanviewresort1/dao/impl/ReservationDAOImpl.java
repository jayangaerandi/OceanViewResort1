package com.oceanviewresort1.dao.impl;

import com.oceanviewresort1.config.DBConnection;
import com.oceanviewresort1.dao.ReservationDAO;
import com.oceanviewresort1.model.Reservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAOImpl implements ReservationDAO {

    @Override
    public boolean addReservation(Reservation reservation) throws Exception {

        String sql = "INSERT INTO Reservations (reservation_number, guest_id, room_id, check_in, check_out, total_amount, status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, reservation.getReservationNumber());
            stmt.setInt(2, reservation.getGuest().getGuestId());
            stmt.setInt(3, reservation.getRoom().getRoomId());
            stmt.setDate(4, java.sql.Date.valueOf(reservation.getCheckIn()));
            stmt.setDate(5, java.sql.Date.valueOf(reservation.getCheckOut()));
            stmt.setDouble(6, reservation.getTotalAmount());
            stmt.setString(7, reservation.getStatus());

            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public Reservation getReservationByNumber(String reservationNumber) throws Exception {

        String sql = "SELECT * FROM Reservations WHERE reservation_number=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, reservationNumber);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Reservation reservation = new Reservation();
                reservation.setReservationId(rs.getInt("reservation_id"));
                reservation.setReservationNumber(rs.getString("reservation_number"));
                reservation.setTotalAmount(rs.getDouble("total_amount"));
                reservation.setStatus(rs.getString("status"));
                return reservation;
            }
        }
        return null;
    }

    @Override
    public List<Reservation> getAllReservations() throws Exception {

        List<Reservation> list = new ArrayList<>();
        String sql = "SELECT * FROM Reservations";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Reservation reservation = new Reservation();
                reservation.setReservationId(rs.getInt("reservation_id"));
                reservation.setReservationNumber(rs.getString("reservation_number"));
                reservation.setTotalAmount(rs.getDouble("total_amount"));
                reservation.setStatus(rs.getString("status"));
                list.add(reservation);
            }
        }
        return list;
    }
}