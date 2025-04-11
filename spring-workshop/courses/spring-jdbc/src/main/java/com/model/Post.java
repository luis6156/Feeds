package com.model;

import java.util.List;

public class Post {
    private int id;
    private User user;
    private String title;
    private String content;
    private List<Tag> tags;

    public Post(User user, String title, String content, List<Tag> tags) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.tags = tags;
    }

    public Post(int id, User user, String title, String content, List<Tag> tags) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.content = content;
        this.tags = tags;
    }

	public Post() {

	}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", user=" + user +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", tags=" + tags +
                '}';
    }
}
