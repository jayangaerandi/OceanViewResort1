package com.oceanviewresort1.dao.impl;

import com.oceanviewresort1.config.DBConnection;
import com.oceanviewresort1.dao.ReservationDAO;
import com.oceanviewresort1.model.Guest;
import com.oceanviewresort1.model.Reservation;
import com.oceanviewresort1.model.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAOImpl implements ReservationDAO {

    @Override
    public boolean createReservation(Reservation reservation) {

        String sql = "INSERT INTO Reservations (reservation_number, guest_id, room_id, check_in, check_out, total_amount, status) VALUES (?, ?, ?, ?, ?, ?, ?)";

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

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean addReservation(Reservation reservation) throws Exception {
        return false;
    }

    @Override
    public Reservation getReservationByNumber(String reservationNumber) throws Exception {

        String sql = "SELECT r.*, g.full_name, rm.room_number " +
                "FROM Reservations r " +
                "JOIN Guests g ON r.guest_id = g.guest_id " +
                "JOIN Rooms rm ON r.room_id = rm.room_id " +
                "WHERE r.reservation_number = ?";

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

                // Guest
                Guest guest = new Guest();
                guest.setFullName(rs.getString("full_name"));
                reservation.setGuest(guest);

                // Room
                Room room = new Room();
                room.setRoomNumber(rs.getString("room_number"));
                reservation.setRoom(room);

                reservation.setCheckIn(rs.getDate("check_in").toLocalDate());
                reservation.setCheckOut(rs.getDate("check_out").toLocalDate());

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
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

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

    @Override
    public boolean isRoomAvailable(int roomId, LocalDate checkIn, LocalDate checkOut) {

        String sql = "SELECT COUNT(*) FROM Reservations " +
                "WHERE room_id = ? " +
                "AND status = 'CONFIRMED' " +
                "AND (check_in < ? AND check_out > ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, roomId);
            stmt.setDate(2, java.sql.Date.valueOf(checkOut));
            stmt.setDate(3, java.sql.Date.valueOf(checkIn));

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) == 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}