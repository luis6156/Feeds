package com.service;

import org.springframework.stereotype.Service;

@Service
public class EmailNotificationSender implements NotificationSender {
    @Override
    public void send(String message) {
        System.out.println("sending email notification: " + message);
    }
}
