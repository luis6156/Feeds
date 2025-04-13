package acs.poo.backend.dtos;

import acs.poo.backend.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class CommentResponse {
    private String uid;
    private String content;
    private Timestamp createdAt;
    private User user;
}
