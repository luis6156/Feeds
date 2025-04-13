package acs.poo.backend.dtos;

import acs.poo.backend.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LikeResponse {
    private Long id;
    private User user;
}
