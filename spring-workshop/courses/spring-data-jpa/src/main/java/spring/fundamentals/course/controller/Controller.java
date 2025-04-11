package spring.fundamentals.course.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.fundamentals.course.model.Post;
import spring.fundamentals.course.model.User;
import spring.fundamentals.course.repository.PostRepository;
import spring.fundamentals.course.repository.UserRepository;

import java.util.List;

@RestController
public class Controller {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public Controller(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public class MyPage {
        Object content;
        int totalElements;
        int currentPage;
        int totalPages;

        public Object getContent() {
            return content;
        }

        public int getTotalElements() {
            return totalElements;
        }

        public int getCurrentPage() {
            return currentPage;
        }

        public int getTotalPages() {
            return totalPages;
        }
    }

    @GetMapping("/users")
    public ResponseEntity<MyPage> findAll(@RequestParam("page") int page, @RequestParam("size") int size) {
        PageRequest pageRequest = PageRequest.of(page, size,
                                                 Sort.by(Sort.Direction.ASC, "username"));
        Page<User> pageResponse = userRepository.findAll(pageRequest);
        MyPage myPage = new MyPage();
        myPage.content = pageResponse.getContent();
        myPage.currentPage = pageResponse.getPageable().getPageNumber();
        myPage.totalPages = pageResponse.getTotalPages();
        myPage.totalElements = pageResponse.getNumberOfElements();

        return ResponseEntity.ok(myPage);
    }

    @PostMapping("/users")
    public ResponseEntity<User> insert(@RequestBody User user) {
        User savedUser = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @GetMapping("/posts")
    public ResponseEntity<MyPage> findAllPosts(@RequestParam(value = "page", defaultValue = "0") int page,
                                               @RequestParam(value = "size", defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "createdAt"));
        Page<Post> pageResponse = postRepository.findAll(pageRequest);

        MyPage myPage = new MyPage();
        myPage.content = pageResponse.getContent();
        myPage.currentPage = pageResponse.getPageable().getPageNumber();
        myPage.totalPages = pageResponse.getTotalPages();
        myPage.totalElements = pageResponse.getNumberOfElements();

        return ResponseEntity.ok(myPage);
    }

}
