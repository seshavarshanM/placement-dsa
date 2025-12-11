package com.busreservation.exceptions;

public class InsufficientSeatsException extends BusReservationException {
    public InsufficientSeatsException(String message) {
        super(message);
    }
}
