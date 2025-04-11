package com.intermediate;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CarDatabase {
    private List<Car> cars;

    public CarDatabase() {
        cars = new ArrayList<>();
        cars.add(new Car("BMW", "5000"));
        cars.add(new Car("Audi", "3000"));
    }

    public Car getCar(int index) {
        return cars.get(index);
    }

    public int getSize() {
        return cars.size();
    }
}
