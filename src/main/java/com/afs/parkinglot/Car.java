package com.afs.parkinglot;

import java.util.Objects;

public class Car {
    private final String carNumber;
    Car(String carNumber) {
        this.carNumber = carNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(carNumber, car.carNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(carNumber);
    }
}
