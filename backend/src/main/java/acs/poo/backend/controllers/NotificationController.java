package acs.poo.backend.controllers;

import acs.poo.backend.dtos.ActivityDTO;
import acs.poo.backend.errors.UserNotFoundError;
import acs.poo.backend.services.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/notification")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ActivityDTO> getNotificationsForClient(@RequestParam String userId) throws UserNotFoundError {
        Flux<ActivityDTO> activityFlux = notificationService.subscribe(userId);
        notificationService.sendEventToClient(
                userId,
                new ActivityDTO("success", "connected to notification service")
        );
        return activityFlux;
    }
}
