package acs.poo.backend.services;

import acs.poo.backend.dtos.ActivityDTO;
import acs.poo.backend.errors.UserNotFoundError;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class NotificationService {
    private final Map<String, Sinks.Many<ActivityDTO>> clientSinks = new ConcurrentHashMap<>();

    public Flux<ActivityDTO> subscribe(String id) {
        return clientSinks
                .computeIfAbsent(id, key -> Sinks.many().multicast().onBackpressureBuffer())
                .asFlux();
    }

    public void sendEventToClient(String id, ActivityDTO activityDTO) throws UserNotFoundError {
        Optional.ofNullable(clientSinks.get(id))
                .orElseThrow(UserNotFoundError::new)
                .tryEmitNext(activityDTO);
    }
}
