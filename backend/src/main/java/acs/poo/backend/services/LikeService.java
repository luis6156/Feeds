package acs.poo.backend.services;

import acs.poo.backend.dtos.ActivityDTO;
import acs.poo.backend.entities.Like;
import acs.poo.backend.errors.PostNotFoundError;
import acs.poo.backend.errors.UserNotFoundError;
import acs.poo.backend.repositories.LikeRepository;
import acs.poo.backend.repositories.PostRepository;
import acs.poo.backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final NotificationService notificationService;

    public void addLikeToPost(String postId, String userId) throws UserNotFoundError, PostNotFoundError {
        var user = userRepository.findById(userId).orElseThrow(UserNotFoundError::new);
        var post = postRepository.findById(postId).orElseThrow(PostNotFoundError::new);

        Like like = new Like();
        like.setUser(user);
        like.setPost(post);

        likeRepository.save(like);

        notificationService.sendEventToClient(
                post.getUser().getUid(),
                new ActivityDTO("Like", String.format("%s has liked your post", userId))
        );
    }

    public void removeLikeFromPost(String postId, String userId) throws UserNotFoundError, PostNotFoundError {
        var user = userRepository.findById(userId).orElseThrow(UserNotFoundError::new);
        var post = postRepository.findById(postId).orElseThrow(PostNotFoundError::new);
        var like = likeRepository.findByPostAndUser(post, user).orElseThrow();

        likeRepository.delete(like);
    }
}
