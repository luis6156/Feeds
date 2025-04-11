package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.properties.AppProperties;

@Service
public class UserService {
    private final NotificationService notificationService;

    private final AppProperties appProperties;

    @Value("${message.type}")
    private String messageType;

    @Autowired
    public UserService(NotificationService notificationService, AppProperties appProperties) {
        this.notificationService = notificationService;
        this.appProperties = appProperties;
    }

    public void createUser(String username) {
        System.out.println("creating user: " + username);
        notificationService.send(String.format(appProperties.getTemplate(), username), messageType);
    }
}
