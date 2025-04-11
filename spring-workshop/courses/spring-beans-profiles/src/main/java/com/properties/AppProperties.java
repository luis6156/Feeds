package com.properties;

import org.springframework.beans.factory.annotation.Value;

public class AppProperties {

    @Value("${user.created.notification.template}")
    private String template;

    public String getTemplate() {
        return template;
    }
}
