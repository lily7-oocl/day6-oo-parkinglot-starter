package com.afs.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class StandardParkingBoy {
    List<ParkingLot> managingParkingLots;
    StandardParkingBoy(List<ParkingLot> managingParkingLots) {
        this.managingParkingLots = managingParkingLots;
    }
    public Ticket park(Car car) {
        for (ParkingLot parkingLot : managingParkingLots) {
            if (!parkingLot.isFull()) {
                return parkingLot.park(car);
            }
        }
        throw new RuntimeException("No available space.");
    }
    public Car fetch(Ticket ticket) {
        for (ParkingLot parkingLot : managingParkingLots) {
            try {
                return parkingLot.fetch(ticket);
            } catch (RuntimeException e) {
                if (!e.getMessage().equals("Unrecognized parking ticket.")) {
                    throw e;
                }
            }
        }
        throw new RuntimeException("Unrecognized parking ticket.");
    }
}
