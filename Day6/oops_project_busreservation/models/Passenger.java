package com.busreservation.models;

public class Passenger extends User {
    private int age;
    private String gender;
    private int loyaltyPoints;
    private static final int LOYALTY_MULTIPLIER = 10; // 1 rupee = 10 points

    public Passenger(int userId, String name, String email, String password,
                     String phoneNumber, String address, int age, String gender) {
        super(userId, name, email, password, phoneNumber, address);
        this.age = age;
        this.gender = gender;
        this.loyaltyPoints = 0;
    }

    // Getters & Setters
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public int getLoyaltyPoints() { return loyaltyPoints; }

    // Business Methods
    public void addLoyaltyPoints(double amount) {
        this.loyaltyPoints += (int) (amount / LOYALTY_MULTIPLIER);
    }

    public void redeemLoyaltyPoints(int points) {
        if (points <= loyaltyPoints) {
            this.loyaltyPoints -= points;
        }
    }

    // Implementation of Abstract Methods
    @Override
    public boolean authenticate(String email, String password) {
        return this.getEmail().equals(email) && this.getPassword().equals(password);
    }

    @Override
    public void displayProfile() {
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║      PASSENGER PROFILE             ║");
        System.out.println("╠════════════════════════════════════╣");
        System.out.printf("║ ID: %-30d ║%n", this.getUserId());
        System.out.printf("║ Name: %-28s ║%n", this.getName());
        System.out.printf("║ Email: %-27s ║%n", this.getEmail());
        System.out.printf("║ Age: %-29d ║%n", age);
        System.out.printf("║ Gender: %-26s ║%n", gender);
        System.out.printf("║ Loyalty Points: %-17d ║%n", loyaltyPoints);
        System.out.println("╚════════════════════════════════════╝");
    }

    @Override
    public String getUserType() {
        return "PASSENGER";
    }
}
