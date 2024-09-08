package com.seongmin.restapipractice.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_comment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CommentEntity {

    @Id
    @Column(name = "COMMENT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(name = "COMMENT")
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID")
    private PostEntity post;

    @Builder(toBuilder = true)
    public CommentEntity(String comment) {
        this.comment = comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setPost(PostEntity post) {
        this.post = post;
    }
}
