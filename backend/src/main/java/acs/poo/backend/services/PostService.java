package acs.poo.backend.services;

import acs.poo.backend.dtos.CommentDTO;
import acs.poo.backend.dtos.PostDTO;
import acs.poo.backend.dtos.PostListResponse;
import acs.poo.backend.dtos.PostResponse;
import acs.poo.backend.entities.Comment;
import acs.poo.backend.entities.Post;
import acs.poo.backend.errors.PostNotFoundError;
import acs.poo.backend.errors.UserNotFoundError;
import acs.poo.backend.repositories.CommentRepository;
import acs.poo.backend.repositories.FriendshipRepository;
import acs.poo.backend.repositories.PostRepository;
import acs.poo.backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final FriendshipRepository friendshipRepository;
    private final UserRepository userRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    public void createPost(PostDTO postDTO, String uid) throws UserNotFoundError {
        var post = modelMapper.map(postDTO, Post.class);
        post.setCreatedAt(Timestamp.from(Instant.now()));
        post.setUser(userRepository.findById(uid).orElseThrow(UserNotFoundError::new));
        postRepository.save(post);
    }

    public PostListResponse listPosts() {
        PostListResponse response = new PostListResponse();

        var posts = new ArrayList<>(StreamSupport.stream(postRepository.findAll().spliterator(), false).toList());
        Collections.shuffle(posts);

        response.setPosts(posts.stream().map((element) -> modelMapper.map(element, PostResponse.class)).toList());
        return response;
    }

    public PostListResponse getFeedPosts(String uid) throws UserNotFoundError {
        PostListResponse response = new PostListResponse();
        var user = userRepository.findById(uid).orElseThrow(UserNotFoundError::new);

        var friendships = friendshipRepository.findBySenderOrReceiverAndIsAccepted(user, user, true);
        var users = friendships.stream()
                .map(friendship -> {
                    if (friendship.getSender().equals(user)) {
                        return friendship.getReceiver();
                    }
                    return friendship.getSender();
                }).toList();

        response.setPosts(postRepository.findAllByUserIn(users).stream()
                .map((element) -> modelMapper.map(element, PostResponse.class)).collect(Collectors.toList()));
        return response;
    }

    public void addCommentToPost(String userId, String postUid, CommentDTO commentDTO) throws PostNotFoundError, UserNotFoundError {
        var user = userRepository.findById(userId).orElseThrow(UserNotFoundError::new);
        var post = postRepository.findById(postUid).orElseThrow(PostNotFoundError::new);
        var comment = commentRepository.save(modelMapper.map(commentDTO, Comment.class));
        comment.setUser(user);
        comment.setCreatedAt(Timestamp.from(Instant.now()));
        post.getComments().add(comment);
        postRepository.save(post);
    }

    public void deletePost(String postUid) throws PostNotFoundError {
        var post = postRepository.findById(postUid).orElseThrow(PostNotFoundError::new);
        postRepository.delete(post);
    }

    public PostResponse getPostById(String postUid) throws PostNotFoundError {
        return modelMapper.map(postRepository.findById(postUid).orElseThrow(PostNotFoundError::new), PostResponse.class);
    }

    public PostListResponse getPostsByUserId(String userId) throws UserNotFoundError {
        PostListResponse response = new PostListResponse();
        var posts = postRepository.findAllByUserIn(List.of(userRepository.findById(userId).orElseThrow(UserNotFoundError::new)));
        response.setPosts(posts.stream().map((element) -> modelMapper.map(element, PostResponse.class)).toList());
        return response;
    }
}
