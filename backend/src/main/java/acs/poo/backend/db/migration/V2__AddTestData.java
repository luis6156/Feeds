package acs.poo.backend.db.migration;


import com.github.javafaker.Faker;
import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class V2__AddTestData extends BaseJavaMigration {
    private final Faker faker = new Faker();
    private final List<UUID> userIds = new ArrayList<>();

    @Override
    public void migrate(Context context) throws Exception {
        addUsers(context.getConnection());
        addPosts(context.getConnection());
    }

    public void addUsers(Connection connection) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(
                "INSERT INTO app_users (uid, img_url) VALUES (?, ?);")) {
            for (int i = 0; i < 10; i++) {
                UUID uuid = UUID.randomUUID();
                stmt.setString(1, uuid.toString());
                stmt.setString(2, "https://placehold.co/200x200");
                stmt.executeUpdate();
                userIds.add(uuid);
            }
        }
    }

    private void addPosts(Connection connection) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(
                "INSERT INTO posts (uid, content, created_at, user_uid) VALUES (?, ?, NOW(), ?);")) {
            for (UUID userId : userIds) {
                UUID postId = UUID.randomUUID();
                stmt.setString(1, postId.toString());
                stmt.setString(2, faker.lorem().sentence());
                stmt.setString(3, userId.toString());
                stmt.executeUpdate();
            }
        }
    }
}
