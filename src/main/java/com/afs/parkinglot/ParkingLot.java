package com.afs.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final int capacity;
    private final Map<Ticket, Car> parkedCars;
    public ParkingLot(int capacity) {
        this.capacity = capacity;
        parkedCars = new HashMap<>();
    }
    public Ticket park(Car car) {
        if (car == null || parkedCars.containsValue(car)) {
            return null;
        }
        if (parkedCars.size() >= capacity) {
            throw new RuntimeException("No available space.");
        }
        Ticket ticket = new Ticket();
        parkedCars.put(ticket, car);
        return ticket;
    }
    public Car fetch(Ticket ticket) {
        if (ticket == null || !parkedCars.containsKey(ticket)) {
            throw new RuntimeException("Unrecognized parking ticket.");
        }
        return parkedCars.remove(ticket);
    }
    public boolean isFull() {
        return parkedCars.size() >= capacity;
    }

    public Map<Ticket, Car> getParkedCars() {
        return parkedCars;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getAvailableSpaces() {
        return capacity - parkedCars.size();
    }
}
