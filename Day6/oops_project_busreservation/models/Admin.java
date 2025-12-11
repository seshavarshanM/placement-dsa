package com.busreservation.models;

public class Admin extends User {
    private String department;
    private String employeeId;

    public Admin(int userId, String name, String email, String password,
                 String phoneNumber, String address, String department, String employeeId) {
        super(userId, name, email, password, phoneNumber, address);
        this.department = department;
        this.employeeId = employeeId;
    }

    // Getters & Setters

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public String getEmployeeId() { return employeeId; }

    // Implementation of Abstract Methods
    @Override
    public boolean authenticate(String email, String password) {
        return this.getEmail().equals(email) && this.getPassword().equals(password);
    }

    @Override
    public void displayProfile() {
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║        ADMIN PROFILE               ║");
        System.out.println("╠════════════════════════════════════╣");
        System.out.printf("║ ID: %-30d ║%n", this.getUserId());
        System.out.printf("║ Name: %-28s ║%n", this.getName());
        System.out.printf("║ Email: %-27s ║%n", this.getEmail());
        System.out.printf("║ Department: %-21s ║%n", department);
        System.out.printf("║ Employee ID: %-21s ║%n", employeeId);
        System.out.println("╚════════════════════════════════════╝");
    }

    @Override
    public String getUserType() {
        return "ADMIN";
    }
}
