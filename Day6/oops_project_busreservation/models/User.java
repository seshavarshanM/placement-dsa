package com.busreservation.models;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class User {
    private int userId;
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String address;
    private LocalDateTime registrationDate;

    public User(int userId, String name, String email, String password,
                String phoneNumber, String address) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.registrationDate = LocalDateTime.now();
    }

    // Getters & Setters
    public int getUserId() { return userId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public LocalDateTime getRegistrationDate() { return registrationDate; }

    // Abstract Methods (Polymorphism)
    public abstract boolean authenticate(String email, String password);
    public abstract void displayProfile();
    public abstract String getUserType();
}
