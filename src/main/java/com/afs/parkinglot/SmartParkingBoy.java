package com.afs.parkinglot;

import java.util.Comparator;
import java.util.List;

public class SmartParkingBoy extends StandardParkingBoy {
    SmartParkingBoy(List<ParkingLot> managingParkingLots) {
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
                .filter(ParkingLot::isNotFull)
                .max(Comparator.comparingInt(ParkingLot::getAvailableSpaces))
                .orElse(null);
        if (bestParkingLot != null) {
            return bestParkingLot.park(car);
        }
        throw new RuntimeException("No available space.");
    }
}
