package com;

import com.logger.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication // equivalent to @Configuration @EnableAutoConfiguration @ComponentScan
public class Main implements CommandLineRunner {

    private final Logger myLogger;

    public Main(Logger logger) {
        this.myLogger = logger;
    }

    // it looks into application.properties by default
    @Value("${key:default}")
    private String key;

    @Override
    public void run(String... args) {
        log.info("salut");
        log.info("My key is {}", key);

        myLogger.log("TEST LOG");
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }
}
