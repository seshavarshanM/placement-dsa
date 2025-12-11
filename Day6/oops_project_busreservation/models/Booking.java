package com.busreservation.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Booking {
    private int bookingId;
    private int passengerId;
    private int scheduleId;
    private LocalDateTime bookingDate;
    private int numberOfSeats;
    private double totalFare;
    private BookingStatus status;
    private List<Integer> bookedSeats;

    public enum BookingStatus {
        PENDING("Pending"), CONFIRMED("Confirmed"), CANCELLED("Cancelled");

        private final String value;
        BookingStatus(String value) { this.value = value; }
        public String getValue() { return value; }
    }

    public Booking(int bookingId, int passengerId, int scheduleId, int numberOfSeats, double totalFare) {
        this.bookingId = bookingId;
        this.passengerId = passengerId;
        this.scheduleId = scheduleId;
        this.bookingDate = LocalDateTime.now();
        this.numberOfSeats = numberOfSeats;
        this.totalFare = totalFare;
        this.status = BookingStatus.PENDING;
        this.bookedSeats = new ArrayList<>();
    }

    public int getBookingId() { return bookingId; }
    public int getPassengerId() { return passengerId; }
    public int getScheduleId() { return scheduleId; }
    public LocalDateTime getBookingDate() { return bookingDate; }
    public int getNumberOfSeats() { return numberOfSeats; }
    public double getTotalFare() { return totalFare; }
    public BookingStatus getStatus() { return status; }
    public void setStatus(BookingStatus status) { this.status = status; }
    public List<Integer> getBookedSeats() { return bookedSeats; }

    public void addBookedSeat(int seatNumber) {
        bookedSeats.add(seatNumber);
    }

    public void displayBookingInfo() {
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║     BOOKING INFORMATION            ║");
        System.out.println("╠════════════════════════════════════╣");
        System.out.printf("║ Booking ID: %-22d ║%n", bookingId);
        System.out.printf("║ Passenger ID: %-20d ║%n", passengerId);
        System.out.printf("║ Schedule ID: %-21d ║%n", scheduleId);
        System.out.printf("║ Seats: %-29d ║%n", numberOfSeats);
        System.out.printf("║ Total Fare: ₹%-23.2f ║%n", totalFare);
        System.out.printf("║ Status: %-28s ║%n", status.getValue());
        System.out.printf("║ Booked Seats: %-21s ║%n", bookedSeats.toString());
        System.out.println("╚════════════════════════════════════╝");
    }
}
