package acs.poo.backend.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PostListResponse {
    private List<PostResponse> posts;
}
