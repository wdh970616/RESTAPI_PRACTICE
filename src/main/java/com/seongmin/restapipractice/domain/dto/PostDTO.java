package com.seongmin.restapipractice.domain.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@Getter
public class PostDTO {
    private Long postId;
    private String postTitle;
    private String postContent;
    private List<CommentDTO> comments;
}
