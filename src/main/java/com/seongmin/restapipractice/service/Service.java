package com.seongmin.restapipractice.service;

import com.seongmin.restapipractice.domain.dto.*;
import com.seongmin.restapipractice.domain.entity.CommentEntity;
import com.seongmin.restapipractice.domain.entity.PostEntity;
import com.seongmin.restapipractice.repository.CommentRepository;
import com.seongmin.restapipractice.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    private final PostRepository postRepo;

    @Autowired
    private final CommentRepository commentRepo;

    public Service(PostRepository postRepo, CommentRepository commentRepo) {
        this.postRepo = postRepo;
        this.commentRepo = commentRepo;
    }

    public List<PostDTO> findAllPost() {

        List<PostEntity> postEntityList = postRepo.findAll();

        return postEntityList.stream()
                .map(post -> new PostDTO(
                        post.getPostId(),
                        post.getPostTitle(),
                        post.getPostContent(),
                        post.getComments().stream()
                                .map(comment -> new CommentDTO(
                                        comment.getCommentId(),
                                        comment.getComment()
                                )).collect(Collectors.toList())
                )).collect(Collectors.toList());
    }

    public PostDTO findPostById(Long postId) {

        PostEntity entity = postRepo.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        List<CommentDTO> createCommentDTOS = entity.getComments().stream()
                .map(comment -> new CommentDTO(
                        comment.getCommentId(),
                        comment.getComment()
                )).collect(Collectors.toList());

        return new PostDTO(
                entity.getPostId(),
                entity.getPostTitle(),
                entity.getPostContent(),
                createCommentDTOS
        );
    }

    public CreatePostDTO createPost(CreatePostDTO createPost) {

        PostEntity newPostEntity = PostEntity.builder()
                .postTitle(createPost.getPostTitle())
                .postContent(createPost.getPostContent())
                .build();

        postRepo.save(newPostEntity);

        return new CreatePostDTO(createPost.getPostTitle(), createPost.getPostContent());
    }

    public UpdatePostDTO updatePost(Long postId, UpdatePostDTO updateInfo) {

        PostEntity targetPost = postRepo.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        PostEntity updatePost = targetPost.toBuilder()
                .postTitle(updateInfo.getPostTitle())
                .postContent(updateInfo.getPostContent())
                .build();

        postRepo.save(updatePost);

        return new UpdatePostDTO(updatePost.getPostTitle(), updatePost.getPostContent());
    }

    public void deletePost(Long id) {
        if (postRepo.existsById(id)) {
            postRepo.deleteById(id);
        }
    }

    public CreateCommentDTO addComment(Long postId, CreateCommentDTO newComment) {

        PostEntity postEntity = postRepo.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        CommentEntity commentEntity = CommentEntity.builder()
                .comment(newComment.getComment())
                .build();

        postEntity.addComment(commentEntity);

        postRepo.save(postEntity);

        return new CreateCommentDTO(newComment.getComment());
    }

    public UpdateCommentDTO updateCommentDTO(Long commentId, UpdateCommentDTO updateInfo) {

        CommentEntity targetComment = commentRepo.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다."));

        targetComment.setComment(updateInfo.getComment());

        CommentEntity updateComment = commentRepo.save(targetComment);

        return new UpdateCommentDTO(updateComment.getComment());
    }

    public void deleteComment(Long id) {
        if (commentRepo.existsById(id)) {
            commentRepo.deleteById(id);
        }
    }
}
