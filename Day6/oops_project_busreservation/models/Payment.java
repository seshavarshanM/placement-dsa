package com.busreservation.models;

import java.time.LocalDateTime;

public class Payment {
    private int paymentId;
    private int bookingId;
    private double amount;
    private LocalDateTime paymentDate;
    private PaymentMethod paymentMethod;
    private String transactionId;
    private PaymentStatus status;

    public enum PaymentMethod {
        CARD("Card"), UPI("UPI"), CASH("Cash"), BANK_TRANSFER("Bank Transfer");

        private final String value;
        PaymentMethod(String value) { this.value = value; }
        public String getValue() { return value; }
    }

    public enum PaymentStatus {
        COMPLETED("Completed"), FAILED("Failed"), PENDING("Pending"), REFUNDED("Refunded");

        private final String value;
        PaymentStatus(String value) { this.value = value; }
        public String getValue() { return value; }
    }

    public Payment(int paymentId, int bookingId, double amount, PaymentMethod paymentMethod) {
        this.paymentId = paymentId;
        this.bookingId = bookingId;
        this.amount = amount;
        this.paymentDate = LocalDateTime.now();
        this.paymentMethod = paymentMethod;
        this.transactionId = generateTransactionId();
        this.status = PaymentStatus.PENDING;
    }

    private String generateTransactionId() {
        return "TXN" + System.currentTimeMillis();
    }

    public int getPaymentId() { return paymentId; }
    public int getBookingId() { return bookingId; }
    public double getAmount() { return amount; }
    public LocalDateTime getPaymentDate() { return paymentDate; }
    public PaymentMethod getPaymentMethod() { return paymentMethod; }
    public String getTransactionId() { return transactionId; }
    public PaymentStatus getStatus() { return status; }
    public void setStatus(PaymentStatus status) { this.status = status; }

    public void displayPaymentInfo() {
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║     PAYMENT INFORMATION            ║");
        System.out.println("╠════════════════════════════════════╣");
        System.out.printf("║ Payment ID: %-22d ║%n", paymentId);
        System.out.printf("║ Amount: ₹%-26.2f ║%n", amount);
        System.out.printf("║ Method: %-28s ║%n", paymentMethod.getValue());
        System.out.printf("║ TXN ID: %-28s ║%n", transactionId);
        System.out.printf("║ Status: %-28s ║%n", status.getValue());
        System.out.println("╚════════════════════════════════════╝");
    }
}
