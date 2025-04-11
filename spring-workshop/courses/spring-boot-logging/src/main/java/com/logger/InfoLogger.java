package com.logger;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile("test")
public class InfoLogger implements Logger {
    @Override
    public void log(String message) {
        log.info(message);
    }
}
