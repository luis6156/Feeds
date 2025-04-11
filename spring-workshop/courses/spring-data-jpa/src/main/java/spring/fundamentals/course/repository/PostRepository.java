package spring.fundamentals.course.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import spring.fundamentals.course.model.Post;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query(
            """
            select p from Post p join p.tags t where t.name = :tagName
            """
    )
    Page<Post> customQuery(String tagName, PageRequest pageRequest);

    @Query(
            """
            select p from Post p where p.user.id = :userId
            """
    )
    List<Post> findByUserId(int userId);

    @Query("SELECT p FROM Post p JOIN p.tags t WHERE t.name = :tagName")
    Page<Post> findByTagsName(String tagName, PageRequest pageRequest);
}
