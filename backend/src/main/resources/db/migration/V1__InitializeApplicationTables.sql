CREATE SEQUENCE IF NOT EXISTS frienships_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS likes_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE app_users
(
    uid     VARCHAR(255) NOT NULL,
    img_url VARCHAR(255),
    CONSTRAINT pk_app_users PRIMARY KEY (uid)
);

CREATE TABLE comments
(
    uid        VARCHAR(255) NOT NULL,
    content    VARCHAR(255),
    created_at TIMESTAMP WITHOUT TIME ZONE,
    user_uid   VARCHAR(255),
    CONSTRAINT pk_comments PRIMARY KEY (uid)
);

CREATE TABLE frienships
(
    id          BIGINT  NOT NULL,
    sender      VARCHAR(255),
    receiver    VARCHAR(255),
    is_accepted BOOLEAN NOT NULL,
    created_at  TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_frienships PRIMARY KEY (id)
);

CREATE TABLE likes
(
    id        BIGINT NOT NULL,
    user_uid  VARCHAR(255),
    post_like VARCHAR(255),
    CONSTRAINT pk_likes PRIMARY KEY (id)
);

CREATE TABLE posts
(
    uid        VARCHAR(255) NOT NULL,
    content    VARCHAR(255),
    created_at TIMESTAMP WITHOUT TIME ZONE,
    user_uid   VARCHAR(255),
    CONSTRAINT pk_posts PRIMARY KEY (uid)
);

CREATE TABLE posts_comments
(
    post_uid     VARCHAR(255) NOT NULL,
    comments_uid VARCHAR(255) NOT NULL
);

ALTER TABLE posts_comments
    ADD CONSTRAINT uc_posts_comments_comments_uid UNIQUE (comments_uid);

ALTER TABLE comments
    ADD CONSTRAINT FK_COMMENTS_ON_USER_UID FOREIGN KEY (user_uid) REFERENCES app_users (uid);

ALTER TABLE frienships
    ADD CONSTRAINT FK_FRIENSHIPS_ON_RECEIVER FOREIGN KEY (receiver) REFERENCES app_users (uid);

ALTER TABLE frienships
    ADD CONSTRAINT FK_FRIENSHIPS_ON_SENDER FOREIGN KEY (sender) REFERENCES app_users (uid);

ALTER TABLE likes
    ADD CONSTRAINT FK_LIKES_ON_POST_LIKE FOREIGN KEY (post_like) REFERENCES posts (uid);

ALTER TABLE likes
    ADD CONSTRAINT FK_LIKES_ON_USER_UID FOREIGN KEY (user_uid) REFERENCES app_users (uid);

ALTER TABLE posts
    ADD CONSTRAINT FK_POSTS_ON_USER_UID FOREIGN KEY (user_uid) REFERENCES app_users (uid);

ALTER TABLE posts_comments
    ADD CONSTRAINT fk_poscom_on_comment FOREIGN KEY (comments_uid) REFERENCES comments (uid);

ALTER TABLE posts_comments
    ADD CONSTRAINT fk_poscom_on_post FOREIGN KEY (post_uid) REFERENCES posts (uid);