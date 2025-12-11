package com.busreservation.services;

import com.busreservation.models.*;
import com.busreservation.exceptions.PaymentFailedException;
import java.util.ArrayList;
import java.util.List;

public class PaymentService {
    private List<Payment> payments;
    private static int paymentCounter = 8000;

    public PaymentService() {
        this.payments = new ArrayList<>();
    }

    public Payment processPayment(Booking booking, Payment.PaymentMethod paymentMethod)
            throws PaymentFailedException {
        if (booking.getStatus() != Booking.BookingStatus.CONFIRMED) {
            throw new PaymentFailedException("Booking must be confirmed first!");
        }

        Payment payment = new Payment(paymentCounter++, booking.getBookingId(),
                booking.getTotalFare(), paymentMethod);

        if (simulatePaymentGateway()) {
            payment.setStatus(Payment.PaymentStatus.COMPLETED);
            payments.add(payment);
            System.out.println("✓ Payment successful! TXN: " + payment.getTransactionId());
            return payment;
        } else {
            payment.setStatus(Payment.PaymentStatus.FAILED);
            throw new PaymentFailedException("Payment declined by gateway!");
        }
    }

    private boolean simulatePaymentGateway() {
        return Math.random() < 0.95; // 95% success rate
    }

    public boolean refundPayment(int paymentId) throws PaymentFailedException {
        Payment payment = getPaymentById(paymentId);
        if (payment == null || payment.getStatus() != Payment.PaymentStatus.COMPLETED) {
            throw new PaymentFailedException("Cannot refund this payment!");
        }

        payment.setStatus(Payment.PaymentStatus.REFUNDED);
        System.out.println("✓ Refund processed! TXN: " + payment.getTransactionId());
        return true;
    }

    public Payment getPaymentById(int paymentId) {
        return payments.stream()
                .filter(p -> p.getPaymentId() == paymentId)
                .findFirst()
                .orElse(null);
    }

    public List<Payment> getAllPayments() {
        return new ArrayList<>(payments);
    }

    public double getTotalPaymentsReceived() {
        return payments.stream()
                .filter(p -> p.getStatus() == Payment.PaymentStatus.COMPLETED)
                .mapToDouble(Payment::getAmount)
                .sum();
    }
}
