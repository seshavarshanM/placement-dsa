package com.busreservation.models;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Bus {
    private int busId;
    private String busType;        // AC, Non-AC, Sleeper
    private String registrationNumber;
    private int totalSeats;
    private String driverId;
    private String conductorId;
    private BusStatus status;
    private List<Seat> seats;

    public enum BusStatus {
        ACTIVE("Active"), INACTIVE("Inactive"), MAINTENANCE("Maintenance");

        private final String value;
        BusStatus(String value) { this.value = value; }
        public String getValue() { return value; }
    }

    public Bus(int busId, String busType, String registrationNumber,
               int totalSeats, String driverId, String conductorId) {
        this.busId = busId;
        this.busType = busType;
        this.registrationNumber = registrationNumber;
        this.totalSeats = totalSeats;
        this.driverId = driverId;
        this.conductorId = conductorId;
        this.status = BusStatus.ACTIVE;
        this.seats = new ArrayList<>();
        initializeSeats();
    }

    // Initialize seats
    private void initializeSeats() {
        for (int i = 1; i <= totalSeats; i++) {
            String seatNumber = "S" + String.format("%02d", i);
            SeatType seatType = (i % 3 == 0) ? SeatType.WINDOW :
                    (i % 3 == 1) ? SeatType.AISLE : SeatType.MIDDLE;
            seats.add(new Seat(i, busId, seatNumber, seatType, 500.0));
        }
    }

    // Getters
    public int getBusId() { return busId; }
    public String getBusType() { return busType; }
    public String getRegistrationNumber() { return registrationNumber; }
    public int getTotalSeats() { return totalSeats; }
    public int getAvailableSeats() {
        return (int) seats.stream().filter(Seat::isAvailable).count();
    }
    public BusStatus getStatus() { return status; }
    public void setStatus(BusStatus status) { this.status = status; }
    public List<Seat> getSeats() { return seats; }

    // Business Methods
    public boolean bookSeat(int seatNumber) {
        Seat seat = findSeatByNumber(seatNumber);
        if (seat != null && seat.isAvailable()) {
            seat.setAvailable(false);
            return true;
        }
        return false;
    }

    public boolean releaseSeat(int seatNumber) {
        Seat seat = findSeatByNumber(seatNumber);
        if (seat != null) {
            seat.setAvailable(true);
            return true;
        }
        return false;
    }

    private Seat findSeatByNumber(int seatNumber) {
        return seats.stream()
                .filter(s -> s.getSeatId() == seatNumber)
                .findFirst()
                .orElse(null);
    }

    public void displayBusInfo() {
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║       BUS INFORMATION              ║");
        System.out.println("╠════════════════════════════════════╣");
        System.out.printf("║ Bus ID: %-27d ║%n", busId);
        System.out.printf("║ Type: %-29s ║%n", busType);
        System.out.printf("║ Registration: %-21s ║%n", registrationNumber);
        System.out.printf("║ Total Seats: %-23d ║%n", totalSeats);
        System.out.printf("║ Available: %-26d ║%n", getAvailableSeats());
        System.out.printf("║ Status: %-28s ║%n", status.getValue());
        System.out.println("╚════════════════════════════════════╝");
    }

    public enum SeatType {
        WINDOW("Window"), AISLE("Aisle"), MIDDLE("Middle");

        private final String value;
        SeatType(String value) { this.value = value; }
        public String getValue() { return value; }
    }
}
