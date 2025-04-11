-- Insert into USERS
INSERT INTO users (username, email) VALUES ('popescu_ion', 'popescu.ion@example.com');
INSERT INTO users (username, email) VALUES ('ionescu_alina', 'ionescu.alina@example.com');

-- -- Insert into PROFILES
INSERT INTO profiles (user_id, bio, profile_picture_url)
VALUES (1, 'Software Developer', 'http://example.com/ionescu.jpg');

-- -- Insert into POSTS
INSERT INTO posts (user_id, title, content)
VALUES (1, 'Popescu Ion post', 'This is Ion popescu first comment');

-- -- Insert into TAGS
INSERT INTO tags (name) VALUES ('Spring');
INSERT INTO tags (name) VALUES ('Database');

-- -- Insert into POST_TAGS (Many-to-Many)
INSERT INTO post_tags (post_id, tag_id) VALUES (1, 1);
INSERT INTO post_tags (post_id, tag_id) VALUES (1, 2);
