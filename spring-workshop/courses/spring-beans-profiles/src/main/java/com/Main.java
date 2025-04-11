package com;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.config.AppConfig;
import com.service.UserService;

/*
    Exercise:
    Define interface:
     public interface NotificationSender{
        void send(String message);
     }
    Define EmailNotificationSender and SmsNotificationSender both of them are implementing NotificationSender interface

    NotificationService should use beans of defined classes.
    The signature of send method from NotificationService is changed like:
    public void send(String message, String type){...}

    where type can be one of the following values: SMS, EMAIL
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        userService.createUser("my_username");
    }
}