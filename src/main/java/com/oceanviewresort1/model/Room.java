package com.oceanviewresort1.model;

public class Room {

    private int roomId;
    private String roomNumber;
    private String status;
    private RoomType roomType;

    public Room() {}

    public Room(int roomId, String roomNumber, String status, RoomType roomType) {
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.status = status;
        this.roomType = roomType;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }
}