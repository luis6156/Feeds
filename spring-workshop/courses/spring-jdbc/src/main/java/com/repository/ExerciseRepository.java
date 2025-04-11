package com.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import com.model.Post;
import java.sql.PreparedStatement;
import java.util.Objects;

@Repository
public class ExerciseRepository {

    private final JdbcTemplate jdbcTemplate;

    public ExerciseRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Post insertPost(Post post) {
        String sql = """
                INSERT INTO posts (user_id, title, content) VALUES (?,?,?)
                """;

        //TODO add implementation

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, post.getUser().getId());
            ps.setString(2, post.getTitle());
            ps.setString(3, post.getContent());
            return ps;
        }, keyHolder);

        Objects.requireNonNull(keyHolder.getKeys()).get("id");

        return post;
    }

    public void insertTag(String name) {
        String sql = "INSERT INTO tags (name) VALUES (?)";
        //TODO add implementation
    }

    public void removePostById(int id) {
        String sql = """
                DELETE FROM posts p WHERE id=?
                """;

        //TODO implement this method
    }
}
