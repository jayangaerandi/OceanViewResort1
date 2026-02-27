package com.oceanviewresort1.model;

public class RoomType {

    private int roomTypeId;
    private String typeName;
    private double pricePerNight;
    private String description;

    public RoomType() {}

    public RoomType(int roomTypeId, String typeName, double pricePerNight, String description) {
        this.roomTypeId = roomTypeId;
        this.typeName = typeName;
        this.pricePerNight = pricePerNight;
        this.description = description;
    }

    public int getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(int roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}