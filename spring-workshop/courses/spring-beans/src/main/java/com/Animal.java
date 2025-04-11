package com;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class Animal {
    private final String name;

    public void logName() {
        log.trace(name);
        log.debug(name);
        log.info(name);
        log.warn(name);
        log.error(name);
    }
}
