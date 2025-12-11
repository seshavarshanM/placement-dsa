package com.busreservation.services;

import com.busreservation.models.*;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private List<User> users;

    public UserService() {
        this.users = new ArrayList<>();
    }

    public void registerUser(User user) {
        users.add(user);
        System.out.println("✓ User registered: " + user.getName());
    }

    public User authenticateUser(String email, String password) {
        return users.stream()
                .filter(u -> u.authenticate(email, password))
                .findFirst()
                .orElse(null);
    }

    public User getUserById(int userId) {
        return users.stream()
                .filter(u -> u.getUserId() == userId)
                .findFirst()
                .orElse(null);
    }

    public User getUserByEmail(String email) {
        return users.stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    public int getTotalUsers() {
        return users.size();
    }

    public int getTotalPassengers() {
        return (int) users.stream().filter(u -> u instanceof Passenger).count();
    }

    public int getTotalAdmins() {
        return (int) users.stream().filter(u -> u instanceof Admin).count();
    }
}
