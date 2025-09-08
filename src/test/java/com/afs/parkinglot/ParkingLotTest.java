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
        assertEquals("No available space.", runtimeException.getMessage());
    }

    @Test
    public void should_return_null_when_park_given_a_parking_lot_with_a_null_car() {
        ParkingLot parkingLot = new ParkingLot(10);
        Car car = null;
        assertNull(parkingLot.park(car));
    }

    @Test
    public void should_return_null_when_park_given_a_parking_lot_with_a_parted_car() {
        ParkingLot parkingLot = new ParkingLot(10);
        Car car = new Car("粤W12345");
        Car car2 = new Car("粤W12345");
        parkingLot.park(car);
        assertNull(parkingLot.park(car2));
    }

    @Test
    public void should_return_car_when_park_given_a_parking_lot_and_a_valid_ticket() {
        ParkingLot parkingLot = new ParkingLot(10);
        Car car = new Car("粤W12345");
        Ticket carTicket = parkingLot.park(car);
        assertEquals(car, parkingLot.fetch(carTicket));
    }

    @Test
    public void should_return_Exception_when_park_given_a_parking_lot_and_a_nonValid_ticket() {
        ParkingLot parkingLot = new ParkingLot(10);
        Ticket nonValidTicket = new Ticket();
        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> parkingLot.fetch(nonValidTicket));
        assertEquals("Unrecognized parking ticket.", runtimeException.getMessage());
    }

    @Test
    public void should_return_Exception_when_park_given_a_parking_lot_and_a_null_ticket() {
        ParkingLot parkingLot = new ParkingLot(10);
        Ticket nonValidTicket = null;
        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> parkingLot.fetch(nonValidTicket));
        assertEquals("Unrecognized parking ticket.", runtimeException.getMessage());
    }

    @Test
    public void should_return_Exception_when_park_given_a_parking_lot_and_a_used_ticket() {
        ParkingLot parkingLot = new ParkingLot(10);
        Car car = new Car("粤W12345");
        Ticket carTicket = parkingLot.park(car);
        parkingLot.fetch(carTicket);
        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> parkingLot.fetch(carTicket));
        assertEquals("Unrecognized parking ticket.", runtimeException.getMessage());
    }
}
