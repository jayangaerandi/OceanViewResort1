package com.oceanviewresort1.dao.impl;

import com.oceanviewresort1.config.DBConnection;
import com.oceanviewresort1.dao.RoomDAO;
import com.oceanviewresort1.model.Room;
import com.oceanviewresort1.model.RoomType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RoomDAOImpl implements RoomDAO {

    @Override
    public Room getRoomById(int roomId) throws Exception {

        String sql = "SELECT r.room_id, r.room_number, rt.room_type_id, rt.type_name, rt.price_per_night " +
                "FROM Rooms r JOIN RoomTypes rt ON r.room_type_id = rt.room_type_id " +
                "WHERE r.room_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, roomId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                Room room = new Room();
                room.setRoomId(rs.getInt("room_id"));
                room.setRoomNumber(rs.getString("room_number"));

                RoomType roomType = new RoomType();
                roomType.setRoomTypeId(rs.getInt("room_type_id"));
                roomType.setTypeName(rs.getString("type_name"));
                roomType.setPricePerNight(rs.getDouble("price_per_night"));

                room.setRoomType(roomType);

                return room;
            }
        }

        return null;
    }
}