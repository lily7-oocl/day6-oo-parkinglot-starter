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

    @Test
    public void should_return_Exception_when_park_given_a_full_parking_lot_with_a_car() {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car("粤W12345");
        Car car2 = new Car("粤W23456");
        parkingLot.park(car);
        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> parkingLot.park(car2));
        assertEquals("No available space", runtimeException.getMessage());
    }

    @Test
    public void should_return_null_when_park_given_a_parking_lot_with_a_null_car() {
        ParkingLot parkingLot = new ParkingLot(10);
        Car car = null;
        assertNull(parkingLot.park(car));
    }
}
