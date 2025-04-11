package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final EmailNotificationSender emailNotificationSender;

    private final SmsNotificationSender smsNotificationSender;

    @Autowired
    public NotificationService(
            EmailNotificationSender emailNotificationSender,
            SmsNotificationSender smsNotificationSender
    ) {
        this.emailNotificationSender = emailNotificationSender;
        this.smsNotificationSender = smsNotificationSender;
    }

    public void send(String message, String type) {
        switch (type) {
            case "SMS" -> smsNotificationSender.send(message);
            case "EMAIL" -> emailNotificationSender.send(message);
            default -> throw new IllegalStateException("Unexpected value: " + type);
        }
    }

}
