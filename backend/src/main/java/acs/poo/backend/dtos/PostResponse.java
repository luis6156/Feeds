package acs.poo.backend.dtos;

import acs.poo.backend.entities.Comment;
import acs.poo.backend.entities.Like;
import acs.poo.backend.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
public class PostResponse {
    private String uid;

    private String content;

    private Timestamp createdAt;

    private User user;

    private List<CommentResponse> comments;

    private List<Like> likes;

}
