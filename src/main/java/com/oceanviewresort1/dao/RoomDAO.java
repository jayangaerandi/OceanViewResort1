package com.oceanviewresort1.dao;

import com.oceanviewresort1.model.Room;
import java.util.List;

public interface RoomDAO {

    Room getRoomById(int roomId) throws Exception;

    List<Room> getAllRooms() throws Exception;

}