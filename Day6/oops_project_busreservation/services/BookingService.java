package com.busreservation.services;

import com.busreservation.models.*;
import com.busreservation.exceptions.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookingService {
    private List<Booking> bookings;
    private List<Ticket> tickets;
    private static int bookingCounter = 1000;
    private static int ticketCounter = 5000;

    public BookingService() {
        this.bookings = new ArrayList<>();
        this.tickets = new ArrayList<>();
    }

    public Booking createBooking(Passenger passenger, Schedule schedule, Bus bus, int numberOfSeats)
            throws InsufficientSeatsException {
        if (bus.getAvailableSeats() < numberOfSeats) {
            throw new InsufficientSeatsException("Only " + bus.getAvailableSeats() + " seats available!");
        }

        double totalFare = numberOfSeats * 500.0; // Simplified calculation
        Booking booking = new Booking(bookingCounter++, passenger.getUserId(),
                schedule.getScheduleId(), numberOfSeats, totalFare);
        bookings.add(booking);
        System.out.println("✓ Booking " + booking.getBookingId() + " created!");
        return booking;
    }

    public boolean confirmBooking(Booking booking, Bus bus, int[] seatNumbers)
            throws InvalidBookingException {
        if (booking == null) {
            throw new InvalidBookingException("Booking not found!");
        }

        if (seatNumbers.length != booking.getNumberOfSeats()) {
            throw new InvalidBookingException("Seat count mismatch!");
        }

        for (int seatNum : seatNumbers) {
            if (!bus.bookSeat(seatNum)) {
                throw new InvalidBookingException("Seat " + seatNum + " not available!");
            }

            Seat seat = bus.getSeats().stream()
                    .filter(s -> s.getSeatId() == seatNum)
                    .findFirst()
                    .orElse(null);

            if (seat != null) {
                Ticket ticket = new Ticket(ticketCounter++, booking.getBookingId(),
                        seat.getSeatNumber(), seat.getFare());
                tickets.add(ticket);
                booking.addBookedSeat(seatNum);
            }
        }

        booking.setStatus(Booking.BookingStatus.CONFIRMED);
        System.out.println("✓ Booking " + booking.getBookingId() + " confirmed!");
        return true;
    }

    public void cancelBooking(Booking booking, Bus bus) throws InvalidBookingException {
        if (booking == null) {
            throw new InvalidBookingException("Booking not found!");
        }

        for (Integer seatNum : booking.getBookedSeats()) {
            bus.releaseSeat(seatNum);
        }

        for (Ticket ticket : tickets) {
            if (ticket.getBookingId() == booking.getBookingId()) {
                ticket.setStatus(Ticket.TicketStatus.CANCELLED);
            }
        }

        booking.setStatus(Booking.BookingStatus.CANCELLED);
        System.out.println("✓ Booking " + booking.getBookingId() + " cancelled!");
    }

    public Booking getBookingById(int bookingId) {
        return bookings.stream()
                .filter(b -> b.getBookingId() == bookingId)
                .findFirst()
                .orElse(null);
    }

    public List<Booking> getBookingsByPassengerId(int passengerId) {
        return bookings.stream()
                .filter(b -> b.getPassengerId() == passengerId)
                .collect(Collectors.toList());
    }

    public List<Ticket> getTicketsForBooking(int bookingId) {
        return tickets.stream()
                .filter(t -> t.getBookingId() == bookingId)
                .collect(Collectors.toList());
    }

    public List<Booking> getAllBookings() {
        return new ArrayList<>(bookings);
    }

    public int getTotalBookings() {
        return bookings.size();
    }

    public int getTotalRevenue() {
        return (int) bookings.stream()
                .filter(b -> b.getStatus() == Booking.BookingStatus.CONFIRMED)
                .mapToDouble(Booking::getTotalFare)
                .sum();
    }
}
