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
        if (parkedCars.size() >= capacity) {
            throw new RuntimeException("No available space");
        }
        Ticket ticket = new Ticket();
        parkedCars.put(ticket, car);
        return ticket;
    }
}
