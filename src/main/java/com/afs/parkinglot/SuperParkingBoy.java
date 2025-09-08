package com.afs.parkinglot;

import java.util.Comparator;
import java.util.List;

public class SuperParkingBoy extends StandardParkingBoy {
    SuperParkingBoy(List<ParkingLot> managingParkingLots) {
        super(managingParkingLots);
    }

    @Override
    public Ticket park(Car car) {
        if (car == null) {
            return null;
        }
        for (ParkingLot parkingLot : getManagingParkingLots()) {
            if (parkingLot.getParkedCars().containsValue(car)) {
                return null;
            }
        }
        ParkingLot bestParkingLot = getManagingParkingLots().stream()
                .filter(parkingLot -> !parkingLot.isFull())
                .max(Comparator.comparingDouble(p -> (double) p.getAvailableSpaces() / p.getCapacity()))
                .orElse(null);
        if (bestParkingLot != null) {
            return bestParkingLot.park(car);
        }
        throw new RuntimeException("No available space.");
    }
}
