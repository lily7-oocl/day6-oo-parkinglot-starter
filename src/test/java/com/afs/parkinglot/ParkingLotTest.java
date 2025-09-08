package com.afs.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

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
        parkingLot.park(car);
        parkingLot.park(car);
        assertNull(parkingLot.park(car));
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

    @Test
    public void should_return_Ticket_when_standard_parking_boy_park_given_three_parking_lot_and_a_car() {
        ParkingLot parkingLot = new ParkingLot(0);
        ParkingLot parkingLot2 = new ParkingLot(1);
        ParkingLot parkingLot3 = new ParkingLot(2);
        ArrayList<ParkingLot> parkingLots = new ArrayList<>(Arrays.asList(parkingLot, parkingLot2, parkingLot3));
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLots);
        Car car = new Car("粤W12345");
        assertNotNull(standardParkingBoy.park(car));
    }

    @Test
    public void should_return_Exception_when_standard_parking_boy_park_given_three_parking_lot_and_a_car() {
        ParkingLot parkingLot = new ParkingLot(0);
        ParkingLot parkingLot2 = new ParkingLot(0);
        ParkingLot parkingLot3 = new ParkingLot(1);
        ArrayList<ParkingLot> parkingLots = new ArrayList<>(Arrays.asList(parkingLot, parkingLot2, parkingLot3));
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLots);
        Car car = new Car("粤W1");
        Car car2 = new Car("粤W2");
        assertNotNull(standardParkingBoy.park(car));
        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> parkingLot.park(car2));
        assertEquals("No available space.", runtimeException.getMessage());
    }

    @Test
    public void should_return_null_when_standard_parking_boy_park_given_three_parking_lot_and_a_null_car_or_a_parted_car() {
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);
        ParkingLot parkingLot3 = new ParkingLot(1);
        ArrayList<ParkingLot> parkingLots = new ArrayList<>(Arrays.asList(parkingLot, parkingLot2, parkingLot3));
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLots);
        Car nullCar = null;
        Car partedCar = new Car("粤W1");
        standardParkingBoy.park(partedCar);
        assertNull(standardParkingBoy.park(nullCar));
        assertNull(standardParkingBoy.park(partedCar));
    }

    @Test
    public void should_return_a_car_when_standard_parking_boy_park_given_three_parking_lot_and_a_valid_ticket() {
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);
        ParkingLot parkingLot3 = new ParkingLot(1);
        Car car = new Car("粤W1");
        Ticket ticket = parkingLot2.park(car);
        ArrayList<ParkingLot> parkingLots = new ArrayList<>(Arrays.asList(parkingLot, parkingLot2, parkingLot3));
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLots);
        assertEquals(car, standardParkingBoy.fetch(ticket));
    }

    @Test
    public void should_return_Exception_when_standard_parking_boy_park_given_three_parking_lot_and_a_nonvalid_or_null_or_used_ticket() {
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);
        ParkingLot parkingLot3 = new ParkingLot(1);
        Car car = new Car("粤W1");
        Ticket nonValidTicket = new Ticket();
        Ticket nullTicket = null;
        Ticket usedTicket = parkingLot2.park(car);
        ArrayList<ParkingLot> parkingLots = new ArrayList<>(Arrays.asList(parkingLot, parkingLot2, parkingLot3));
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLots);
        standardParkingBoy.fetch(usedTicket);
        RuntimeException nonValidTicketException = assertThrows(RuntimeException.class, () -> standardParkingBoy.fetch(nonValidTicket));
        RuntimeException nullTicketException = assertThrows(RuntimeException.class, () -> standardParkingBoy.fetch(nullTicket));
        RuntimeException usedTicketException = assertThrows(RuntimeException.class, () -> standardParkingBoy.fetch(usedTicket));
        assertEquals("Unrecognized parking ticket.", nonValidTicketException.getMessage());
        assertEquals("Unrecognized parking ticket.", nullTicketException.getMessage());
        assertEquals("Unrecognized parking ticket.", usedTicketException.getMessage());
    }

    @Test
    public void should_return_Ticket_when_smart_parking_boy_park_given_three_parking_lot_and_a_car() {
        ParkingLot parkingLot = new ParkingLot(0);
        ParkingLot parkingLot2 = new ParkingLot(1);
        ParkingLot parkingLot3 = new ParkingLot(2);
        ArrayList<ParkingLot> parkingLots = new ArrayList<>(Arrays.asList(parkingLot, parkingLot2, parkingLot3));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car car = new Car("粤W12345");
        assertNotNull(smartParkingBoy.park(car));
    }

    @Test
    public void should_return_Exception_when_smart_parking_boy_park_given_three_parking_lot_and_a_car() {
        ParkingLot parkingLot = new ParkingLot(0);
        ParkingLot parkingLot2 = new ParkingLot(0);
        ParkingLot parkingLot3 = new ParkingLot(1);
        ArrayList<ParkingLot> parkingLots = new ArrayList<>(Arrays.asList(parkingLot, parkingLot2, parkingLot3));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car car = new Car("粤W1");
        Car car2 = new Car("粤W2");
        assertNotNull(smartParkingBoy.park(car));
        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> parkingLot.park(car2));
        assertEquals("No available space.", runtimeException.getMessage());
    }

    @Test
    public void should_return_null_when_smart_parking_boy_park_given_three_parking_lot_and_a_null_car_or_a_parted_car() {
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);
        ParkingLot parkingLot3 = new ParkingLot(1);
        ArrayList<ParkingLot> parkingLots = new ArrayList<>(Arrays.asList(parkingLot, parkingLot2, parkingLot3));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car nullCar = null;
        Car partedCar = new Car("粤W1");
        smartParkingBoy.park(partedCar);
        assertNull(smartParkingBoy.park(nullCar));
        assertNull(smartParkingBoy.park(partedCar));
    }

    @Test
    public void should_return_a_car_when_smart_parking_boy_park_given_three_parking_lot_and_a_valid_ticket() {
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);
        ParkingLot parkingLot3 = new ParkingLot(1);
        Car car = new Car("粤W1");
        Ticket ticket = parkingLot2.park(car);
        ArrayList<ParkingLot> parkingLots = new ArrayList<>(Arrays.asList(parkingLot, parkingLot2, parkingLot3));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        assertEquals(car, smartParkingBoy.fetch(ticket));
    }

    @Test
    public void should_return_Exception_when_smart_parking_boy_park_given_three_parking_lot_and_a_nonvalid_or_null_or_used_ticket() {
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);
        ParkingLot parkingLot3 = new ParkingLot(1);
        Car car = new Car("粤W1");
        Ticket nonValidTicket = new Ticket();
        Ticket nullTicket = null;
        Ticket usedTicket = parkingLot2.park(car);
        ArrayList<ParkingLot> parkingLots = new ArrayList<>(Arrays.asList(parkingLot, parkingLot2, parkingLot3));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        smartParkingBoy.fetch(usedTicket);
        RuntimeException nonValidTicketException = assertThrows(RuntimeException.class, () -> smartParkingBoy.fetch(nonValidTicket));
        RuntimeException nullTicketException = assertThrows(RuntimeException.class, () -> smartParkingBoy.fetch(nullTicket));
        RuntimeException usedTicketException = assertThrows(RuntimeException.class, () -> smartParkingBoy.fetch(usedTicket));
        assertEquals("Unrecognized parking ticket.", nonValidTicketException.getMessage());
        assertEquals("Unrecognized parking ticket.", nullTicketException.getMessage());
        assertEquals("Unrecognized parking ticket.", usedTicketException.getMessage());
    }
}
