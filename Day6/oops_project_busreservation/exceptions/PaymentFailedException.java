package com.busreservation.exceptions;

public class PaymentFailedException extends BusReservationException {
    public PaymentFailedException(String message) {
        super(message);
    }
}
