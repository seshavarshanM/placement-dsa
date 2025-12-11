package com.busreservation.models;

public class Route {
    private int routeId;
    private String source;
    private String destination;
    private double distance;
    private String estimatedDuration;
    private double baseFare;

    public Route(int routeId, String source, String destination,
                 double distance, String estimatedDuration, double baseFare) {
        this.routeId = routeId;
        this.source = source;
        this.destination = destination;
        this.distance = distance;
        this.estimatedDuration = estimatedDuration;
        this.baseFare = baseFare;
    }

    public int getRouteId() { return routeId; }
    public String getSource() { return source; }
    public String getDestination() { return destination; }
    public double getDistance() { return distance; }
    public String getEstimatedDuration() { return estimatedDuration; }
    public double getBaseFare() { return baseFare; }

    @Override
    public String toString() {
        return String.format("%s → %s (%.2f km, %s, ₹%.2f)",
                source, destination, distance, estimatedDuration, baseFare);
    }

    public void displayRouteInfo() {
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║      ROUTE INFORMATION             ║");
        System.out.println("╠════════════════════════════════════╣");
        System.out.printf("║ Route ID: %-25d ║%n", routeId);
        System.out.printf("║ From: %-29s ║%n", source);
        System.out.printf("║ To: %-31s ║%n", destination);
        System.out.printf("║ Distance: %-25.2f ║%n", distance);
        System.out.printf("║ Duration: %-25s ║%n", estimatedDuration);
        System.out.printf("║ Base Fare: %-24.2f ║%n", baseFare);
        System.out.println("╚════════════════════════════════════╝");
    }
}
