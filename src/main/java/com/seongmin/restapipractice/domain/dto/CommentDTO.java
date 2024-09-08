package com.seongmin.restapipractice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CommentDTO {
    private Long commentId;
    private String comment;
}
