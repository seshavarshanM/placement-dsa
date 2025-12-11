package com.busreservation.services;

import com.busreservation.models.Bus;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BusService {
    private List<Bus> buses;
    private static int busCounter = 100;

    public BusService() {
        this.buses = new ArrayList<>();
    }

    public Bus addBus(String busType, String registrationNumber, int totalSeats,
                      String driverId, String conductorId) {
        Bus bus = new Bus(busCounter++, busType, registrationNumber, totalSeats, driverId, conductorId);
        buses.add(bus);
        System.out.println("✓ Bus " + bus.getBusId() + " added successfully!");
        return bus;
    }

    public Bus getBusById(int busId) {
        return buses.stream()
                .filter(b -> b.getBusId() == busId)
                .findFirst()
                .orElse(null);
    }

    public List<Bus> getAllBuses() {
        return new ArrayList<>(buses);
    }

    public List<Bus> getAvailableBuses() {
        return buses.stream()
                .filter(b -> b.getStatus() == Bus.BusStatus.ACTIVE && b.getAvailableSeats() > 0)
                .collect(Collectors.toList());
    }

    public void updateBusStatus(int busId, Bus.BusStatus status) {
        Bus bus = getBusById(busId);
        if (bus != null) {
            bus.setStatus(status);
            System.out.println("✓ Bus " + busId + " status updated to " + status.getValue());
        }
    }

    public int getTotalBuses() {
        return buses.size();
    }

    public int getTotalAvailableSeats() {
        return buses.stream().mapToInt(Bus::getAvailableSeats).sum();
    }
}
