package com.intermediate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@Slf4j
public class CarService {
    private CarDatabaseJson carDatabase;
    private CarDatabase carDatabaseDummy;

    // dependency injection via constructor
    public CarService(CarDatabaseJson carDatabase, CarDatabase carDatabaseDummy) {
        this.carDatabase = carDatabase;
        this.carDatabaseDummy = carDatabaseDummy;
    }

    public void logCar() {
        int index = new Random().nextInt(carDatabase.getSize());

        log.info("Logged car is {}", carDatabase.getCar(index));
    }

    public void logCarDummy() {
        int index = new Random().nextInt(carDatabaseDummy.getSize());

        log.info("Logged car is {}", carDatabaseDummy.getCar(index));
    }
}
