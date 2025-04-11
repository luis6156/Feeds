package com.intermediate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class CarDatabaseJson {
    private List<Car> cars;

    private final ObjectMapper objectMapper;

    // read file from src/main/resources
    @Value("classpath:/cars.json")
    private Resource resource;

    public CarDatabaseJson(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    public void initialise() {
        readCars();
    }

    private void readCars() {
        try {
            this.cars = objectMapper.readValue(resource.getFile(), new TypeReference<>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Car getCar(int index) {
        return cars.get(index);
    }

    public int getSize() {
        return cars.size();
    }
}
