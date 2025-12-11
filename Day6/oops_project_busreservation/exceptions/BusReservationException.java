package com.busreservation.exceptions;

public class BusReservationException extends Exception {
    public BusReservationException(String message) {
        super(message);
    }

    public BusReservationException(String message, Throwable cause) {
        super(message, cause);
    }
}
