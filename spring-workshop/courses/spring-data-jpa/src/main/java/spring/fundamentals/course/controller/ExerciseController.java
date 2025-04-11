package spring.fundamentals.course.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.fundamentals.course.model.Post;
import spring.fundamentals.course.model.Tag;
import spring.fundamentals.course.repository.PostRepository;
import spring.fundamentals.course.repository.ProfileRepository;
import spring.fundamentals.course.repository.TagRepository;
import spring.fundamentals.course.repository.UserRepository;

@RestController
@RequestMapping("/exercises")
public class ExerciseController {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final TagRepository tagRepository;

    public ExerciseController(PostRepository postRepository, UserRepository userRepository, ProfileRepository profileRepository, TagRepository tagRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.tagRepository = tagRepository;
    }

    @PostMapping("/profiles/{id}/bio")
    public ResponseEntity<String> updateProfileBio(
            @PathVariable int id,
            @RequestParam String newBio) {
        // Find user by ID and update the associated profile's bio
        return profileRepository.findByUserId(id).map(profile -> {
            profile.setBio(newBio);
            profileRepository.save(profile); // Save the updated profile
            return ResponseEntity.ok("Profile bio updated successfully!");
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/posts/{id}/tags")
    public ResponseEntity<String> addTagToPost(
            @PathVariable int id,
            @RequestBody String newTag) {
        return postRepository.findById(id).map(post -> {
            // Check if tag exists or create a new one
            Tag tag = tagRepository.findByName(newTag)
                                   .orElseGet(() -> tagRepository.save(new Tag(newTag)));
            post.getTags().add(tag); // Add the tag to the post
            postRepository.save(post); // Save the post with the new tag
            return ResponseEntity.ok("Tag added to post successfully!");
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUserAndCascade(
            @PathVariable int id) {
        userRepository.deleteById(id); // Cascade delete should be set up in entities
        return ResponseEntity.ok("User deleted successfully!");
    }

    @GetMapping("/posts/title-contains")
    public Page<Post> searchPosts(@RequestParam String tagName,
                                  @RequestParam int page,
                                  @RequestParam int size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        // Custom query logic to find posts that have a tag with 'tagName'
        return postRepository.findByTagsName(tagName, pageRequest);
    }
}
