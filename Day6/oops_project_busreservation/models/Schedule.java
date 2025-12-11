package com.busreservation.models;

import java.time.LocalDate;
import java.time.LocalTime;

public class Schedule {
    private int scheduleId;
    private int busId;
    private int routeId;
    private LocalDate departureDate;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private ScheduleStatus status;

    public enum ScheduleStatus {
        ON_TIME("On Time"), DELAYED("Delayed"), CANCELLED("Cancelled");

        private final String value;
        ScheduleStatus(String value) { this.value = value; }
        public String getValue() { return value; }
    }

    public Schedule(int scheduleId, int busId, int routeId,
                    LocalDate departureDate, LocalTime departureTime, LocalTime arrivalTime) {
        this.scheduleId = scheduleId;
        this.busId = busId;
        this.routeId = routeId;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.status = ScheduleStatus.ON_TIME;
    }

    public int getScheduleId() { return scheduleId; }
    public int getBusId() { return busId; }
    public int getRouteId() { return routeId; }
    public LocalDate getDepartureDate() { return departureDate; }
    public LocalTime getDepartureTime() { return departureTime; }
    public LocalTime getArrivalTime() { return arrivalTime; }
    public ScheduleStatus getStatus() { return status; }
    public void setStatus(ScheduleStatus status) { this.status = status; }

    public void displayScheduleInfo() {
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║     SCHEDULE INFORMATION           ║");
        System.out.println("╠════════════════════════════════════╣");
        System.out.printf("║ Schedule ID: %-21d ║%n", scheduleId);
        System.out.printf("║ Date: %-29s ║%n", departureDate);
        System.out.printf("║ Departure: %-24s ║%n", departureTime);
        System.out.printf("║ Arrival: %-27s ║%n", arrivalTime);
        System.out.printf("║ Status: %-28s ║%n", status.getValue());
        System.out.println("╚════════════════════════════════════╝");
    }
}
