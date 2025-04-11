package com.model;

public class Profile {

    private int id;
    private String bio;
    private String profilePictureURL;
    private User user;

    public Profile(int id, String bio, String profilePictureURL, User user) {
        this.id = id;
        this.bio = bio;
        this.profilePictureURL = profilePictureURL;
        this.user = user;
    }

    public Profile(String bio, String profilePictureURL, User user) {
        this.id = id;
        this.profilePictureURL = profilePictureURL;
        this.user = user;
    }

	public Profile() {

	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getProfilePictureURL() {
        return profilePictureURL;
    }

    public void setProfilePictureURL(String profilePictureURL) {
        this.profilePictureURL = profilePictureURL;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", bio='" + bio + '\'' +
                ", profilePictureURL='" + profilePictureURL + '\'' +
                ", user=" + user +
                '}';
    }
}
