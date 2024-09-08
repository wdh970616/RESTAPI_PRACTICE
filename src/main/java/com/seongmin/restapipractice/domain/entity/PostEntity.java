package com.seongmin.restapipractice.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_post")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class PostEntity {

    @Id
    @Column(name = "POST_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(name = "POST_TITLE")
    private String postTitle;

    @Column(name = "POST_CONTENT")
    private String postContent;

    @Column(name = "COMMENTS")
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CommentEntity> comments = new ArrayList<>();

    @Builder(toBuilder = true)
    public PostEntity(String postTitle, String postContent) {
        this.postTitle = postTitle;
        this.postContent = postContent;
    }

    public void addComment(CommentEntity comment) {
        comments.add(comment);
        comment.setPost(this);
    }

    public void removeComment(CommentEntity comment) {
        comments.remove(comment);
        comment.setPost(null);
    }
}
