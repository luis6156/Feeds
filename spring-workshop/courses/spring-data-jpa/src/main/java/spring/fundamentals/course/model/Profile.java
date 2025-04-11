package spring.fundamentals.course.model;

import jakarta.persistence.*;

@Entity
@Table(name = "profiles")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String bio;
    @Column(name = "profile_picture_url")
    private String profilePictureURL;

    @OneToOne(cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    @JoinColumn(name = "user_id")
    private User user;

    public Profile(int id, String bio, String profilePictureURL, User user) {
        this.id = id;
        this.bio = bio;
        this.profilePictureURL = profilePictureURL;
        this.user = user;
    }

    public Profile(String bio, String profilePictureURL, User user) {
        this.bio = bio;
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
