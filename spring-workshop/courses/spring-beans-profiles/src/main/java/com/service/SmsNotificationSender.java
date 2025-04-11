package com.service;

import org.springframework.stereotype.Service;

@Service
public class SmsNotificationSender implements NotificationSender {
    @Override
    public void send(String message) {
        System.out.println("sending sms notification: " + message);
    }
}
