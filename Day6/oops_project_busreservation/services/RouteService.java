package com.busreservation.services;

import com.busreservation.models.Route;
import java.util.ArrayList;
import java.util.List;

public class RouteService {
    private List<Route> routes;
    private static int routeCounter = 1;

    public RouteService() {
        this.routes = new ArrayList<>();
    }

    public Route addRoute(String source, String destination, double distance,
                          String estimatedDuration, double baseFare) {
        Route route = new Route(routeCounter++, source, destination, distance, estimatedDuration, baseFare);
        routes.add(route);
        System.out.println("✓ Route " + route.getRouteId() + " added: " + route);
        return route;
    }

    public Route getRouteById(int routeId) {
        return routes.stream()
                .filter(r -> r.getRouteId() == routeId)
                .findFirst()
                .orElse(null);
    }

    public List<Route> getAllRoutes() {
        return new ArrayList<>(routes);
    }

    public Route getRouteBySourceAndDestination(String source, String destination) {
        return routes.stream()
                .filter(r -> r.getSource().equalsIgnoreCase(source) &&
                        r.getDestination().equalsIgnoreCase(destination))
                .findFirst()
                .orElse(null);
    }

    public int getTotalRoutes() {
        return routes.size();
    }
}
