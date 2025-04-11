package com.intermediate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);

        CarService carService = applicationContext.getBean(CarService.class);

        carService.logCar();
        carService.logCar();
        carService.logCar();
        carService.logCar();
        carService.logCarDummy();
        carService.logCarDummy();
    }
}
