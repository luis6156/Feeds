package com.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import com.model.Profile;
import com.model.Tag;
import com.model.User;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PlayGroundRepository {

    private final JdbcTemplate jdbcTemplate;

    PlayGroundRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(int userId, String bio, String profilePictureUrl) {
        String sql = """
                INSERT INTO profiles (user_id, bio, profile_picture_url) VALUES (?, ?, ?)
                """;
        jdbcTemplate.update(sql, userId, bio, profilePictureUrl);
    }

    public int insertReturnId(int userId, String bio, String profilePictureUrl) {
        String sql = """
                INSERT INTO profiles (user_id, bio, profile_picture_url) VALUES (?, ?, ?)
                """;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, userId);
            ps.setString(2, bio);
            ps.setString(3, profilePictureUrl);
            return ps;
        }, keyHolder);

        return (int) keyHolder.getKeys().get("id");
    }

    public List<Profile> findAll() {
        String sql = """
                SELECT p.*, u.* FROM profiles p, users u where u.id = p.user_id
                """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Profile profile = new Profile();
            profile.setId(rs.getInt("id")); // profile id
            profile.setBio(rs.getString("bio"));

            User user = new User();
            user.setId(rs.getInt("user_id"));
            user.setUsername(rs.getString("username"));
            user.setEmail(rs.getString("email"));

            profile.setUser(user);
            return profile;
        });
    }


    public int deleteByUserId(int userId) {
        String sql = """
            DELETE FROM profiles WHERE user_id=?
            """;
        return jdbcTemplate.update(sql, userId);}

    public User findUserById(int id) {
        String sql = """
            SELECT * FROM users WHERE id=?
            """;

        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setEmail(rs.getString("email"));
            return user;
        });
    }

}
