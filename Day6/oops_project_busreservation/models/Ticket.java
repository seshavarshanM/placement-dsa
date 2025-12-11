package com.busreservation.models;

import java.time.LocalDateTime;

public class Ticket {
    private int ticketId;
    private int bookingId;
    private String seatNumber;
    private double fare;
    private LocalDateTime issuedDate;
    private TicketStatus status;

    public enum TicketStatus {
        VALID("Valid"), USED("Used"), CANCELLED("Cancelled");

        private final String value;
        TicketStatus(String value) { this.value = value; }
        public String getValue() { return value; }
    }

    public Ticket(int ticketId, int bookingId, String seatNumber, double fare) {
        this.ticketId = ticketId;
        this.bookingId = bookingId;
        this.seatNumber = seatNumber;
        this.fare = fare;
        this.issuedDate = LocalDateTime.now();
        this.status = TicketStatus.VALID;
    }

    public int getTicketId() { return ticketId; }
    public int getBookingId() { return bookingId; }
    public String getSeatNumber() { return seatNumber; }
    public double getFare() { return fare; }
    public LocalDateTime getIssuedDate() { return issuedDate; }
    public TicketStatus getStatus() { return status; }
    public void setStatus(TicketStatus status) { this.status = status; }

    public void displayTicketInfo() {
        System.out.println("\n  ┌────────────────────────┐");
        System.out.printf("  │ Ticket ID: %-12d│%n", ticketId);
        System.out.printf("  │ Seat: %-17s│%n", seatNumber);
        System.out.printf("  │ Fare: ₹%-15.2f│%n", fare);
        System.out.printf("  │ Status: %-15s│%n", status.getValue());
        System.out.println("  └────────────────────────┘");
    }
}
