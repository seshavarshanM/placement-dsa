package com.busreservation.services;

import com.busreservation.models.Schedule;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ScheduleService {
    private List<Schedule> schedules;
    private static int scheduleCounter = 1;

    public ScheduleService() {
        this.schedules = new ArrayList<>();
    }

    public Schedule addSchedule(int busId, int routeId, LocalDate departureDate,
                                java.time.LocalTime departureTime, java.time.LocalTime arrivalTime) {
        Schedule schedule = new Schedule(scheduleCounter++, busId, routeId, departureDate, departureTime, arrivalTime);
        schedules.add(schedule);
        System.out.println("✓ Schedule " + schedule.getScheduleId() + " added!");
        return schedule;
    }

    public Schedule getScheduleById(int scheduleId) {
        return schedules.stream()
                .filter(s -> s.getScheduleId() == scheduleId)
                .findFirst()
                .orElse(null);
    }

    public List<Schedule> getSchedulesByBusId(int busId) {
        return schedules.stream()
                .filter(s -> s.getBusId() == busId)
                .collect(Collectors.toList());
    }

    public List<Schedule> getSchedulesByRouteId(int routeId) {
        return schedules.stream()
                .filter(s -> s.getRouteId() == routeId)
                .collect(Collectors.toList());
    }

    public List<Schedule> getAllSchedules() {
        return new ArrayList<>(schedules);
    }

    public int getTotalSchedules() {
        return schedules.size();
    }
}
