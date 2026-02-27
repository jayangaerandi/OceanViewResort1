package com.oceanviewresort1.model;

import java.time.LocalDateTime;

public class Payment {

    private int paymentId;
    private Reservation reservation;
    private LocalDateTime paymentDate;
    private double amount;
    private String paymentMethod;

    public Payment() {}

    public Payment(int paymentId, Reservation reservation,
                   LocalDateTime paymentDate, double amount,
                   String paymentMethod) {
        this.paymentId = paymentId;
        this.reservation = reservation;
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}