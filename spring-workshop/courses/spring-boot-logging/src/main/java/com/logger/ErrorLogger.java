package com.logger;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile("dev & !test") // will be active if the dev profile is active and the test profile is *not* active
public class ErrorLogger implements Logger {

    @Override
    public void log(String message) {
        log.error(message);
    }
}
