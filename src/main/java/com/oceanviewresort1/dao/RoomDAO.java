package com.oceanviewresort1.dao;

import com.oceanviewresort1.model.Room;

public interface RoomDAO {

    Room getRoomById(int roomId) throws Exception;

}