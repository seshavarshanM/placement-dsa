package com.busreservation.utils;

import com.busreservation.models.*;
import com.busreservation.services.*;
import com.busreservation.exceptions.*;

public class ReservationSystem {
    private BusService busService;
    private RouteService routeService;
    private ScheduleService scheduleService;
    private BookingService bookingService;
    private PaymentService paymentService;
    private UserService userService;

    public ReservationSystem() {
        this.busService = new BusService();
        this.routeService = new RouteService();
        this.scheduleService = new ScheduleService();
        this.bookingService = new BookingService();
        this.paymentService = new PaymentService();
        this.userService = new UserService();
    }

    // Getters
    public BusService getBusService() { return busService; }
    public RouteService getRouteService() { return routeService; }
    public ScheduleService getScheduleService() { return scheduleService; }
    public BookingService getBookingService() { return bookingService; }
    public PaymentService getPaymentService() { return paymentService; }
    public UserService getUserService() { return userService; }

    // High-level operations
    public void displaySystemStats() {
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║     SYSTEM STATISTICS              ║");
        System.out.println("╠════════════════════════════════════╣");
        System.out.printf("║ Total Buses: %-24d ║%n", busService.getTotalBuses());
        System.out.printf("║ Available Seats: %-19d ║%n", busService.getTotalAvailableSeats());
        System.out.printf("║ Total Routes: %-23d ║%n", routeService.getTotalRoutes());
        System.out.printf("║ Total Schedules: %-20d ║%n", scheduleService.getTotalSchedules());
        System.out.printf("║ Total Bookings: %-21d ║%n", bookingService.getTotalBookings());
        System.out.printf("║ Revenue Generated: ₹%-16d ║%n", bookingService.getTotalRevenue());
        System.out.printf("║ Total Users: %-24d ║%n", userService.getTotalUsers());
        System.out.printf("║ Total Passengers: %-20d ║%n", userService.getTotalPassengers());
        System.out.printf("║ Total Admins: %-23d ║%n", userService.getTotalAdmins());
        System.out.println("╚════════════════════════════════════╝");
    }
}
