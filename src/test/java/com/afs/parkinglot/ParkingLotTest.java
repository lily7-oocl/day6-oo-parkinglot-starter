package com.afs.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {
    @Test
    public void should_return_a_ticket_when_park_given_a_parking_lot_with_a_car() {
        ParkingLot parkingLot = new ParkingLot(10);
        Car car = new Car("粤W12345");
        assertNotNull(parkingLot.park(car));
    }

}
