package com.busreservation.models;

public class Seat {
    private int seatId;
    private int busId;
    private String seatNumber;
    private Bus.SeatType seatType;
    private double fare;
    private boolean isAvailable;

    public Seat(int seatId, int busId, String seatNumber, Bus.SeatType seatType, double fare) {
        this.seatId = seatId;
        this.busId = busId;
        this.seatNumber = seatNumber;
        this.seatType = seatType;
        this.fare = fare;
        this.isAvailable = true;
    }

    public int getSeatId() { return seatId; }
    public int getBusId() { return busId; }
    public String getSeatNumber() { return seatNumber; }
    public Bus.SeatType getSeatType() { return seatType; }
    public double getFare() { return fare; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }

    @Override
    public String toString() {
        return String.format("Seat %s [%s] ₹%.2f - %s",
                seatNumber, seatType.getValue(), fare,
                isAvailable ? "Available" : "Booked");
    }
}
