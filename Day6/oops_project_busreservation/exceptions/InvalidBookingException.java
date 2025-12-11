package com.busreservation.exceptions;

public class InvalidBookingException extends BusReservationException {
    public InvalidBookingException(String message) {
        super(message);
    }
}
