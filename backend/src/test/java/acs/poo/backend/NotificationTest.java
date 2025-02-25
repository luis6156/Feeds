package acs.poo.backend;

import acs.poo.backend.entities.User;
import acs.poo.backend.errors.FriendshipAlreadyExistsError;
import acs.poo.backend.errors.UserNotFoundError;
import acs.poo.backend.repositories.FriendshipRepository;
import acs.poo.backend.repositories.UserRepository;
import acs.poo.backend.services.FriendshipService;
import acs.poo.backend.services.NotificationService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

public class NotificationTest {
    FriendshipRepository friendshipRepository = Mockito.mock(FriendshipRepository.class);

    UserRepository userRepository = Mockito.mock(UserRepository.class);

//    @Test
//    void checkNotificationsAreSent() throws UserNotFoundError, FriendshipAlreadyExistsError {
//        NotificationService notificationService = Mockito.mock(NotificationService.class);
//
//        FriendshipService friendshipService = new FriendshipService(
//                friendshipRepository,
//                userRepository,
//                notificationService
//        );
//
//        User testUser1 = new User();
//        testUser1.setUid("test1");
//
//        User testUser2 = new User();
//        testUser2.setUid("test2");
//
//        Mockito.when(userRepository.findById("test1")).thenReturn(Optional.of(testUser1));
//        Mockito.when(userRepository.findById("test2")).thenReturn(Optional.of(testUser2));
//        Mockito.doNothing().when(notificationService).sendEventToClient("test2", Mockito.any());
//
//        friendshipService.createFriendship("test1", "test2");
//
//        Mockito.verify(notificationService).sendEventToClient("test2", Mockito.any());
//    }
}
