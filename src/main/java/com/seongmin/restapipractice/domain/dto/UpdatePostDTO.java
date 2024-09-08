package com.seongmin.restapipractice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UpdatePostDTO {
    private String postTitle;
    private String postContent;
}
