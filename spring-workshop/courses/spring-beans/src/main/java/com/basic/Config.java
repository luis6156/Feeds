package com.basic;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

public class Config {

    @Bean("first") // give the beans names to help spring differentiate them
//    @Primary // use this to avoid using names every time
    public FirstBean firstBean() {
        return new FirstBean("Hello");
    }

    @Bean("another")
    public FirstBean anotherBean() {
        return new FirstBean("Goodbye");
    }

    @Bean
    // using @Qualifier to tell Spring what bean to inject
    public SecondBean secondBean(@Qualifier("first") FirstBean firstBean) {
        return new SecondBean(firstBean);
    }

}
