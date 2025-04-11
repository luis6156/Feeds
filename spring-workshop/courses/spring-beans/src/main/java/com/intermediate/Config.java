package com.intermediate;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

// scan the `intermediate` package for @Component classes
@ComponentScan("com.intermediate")
public class Config {

    // Spring does not know how to instantiate ObjectMapper by default, so we need to help it
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
