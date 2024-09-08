package com.seongmin.restapipractice.controller;

import com.seongmin.restapipractice.domain.dto.CreateCommentDTO;
import com.seongmin.restapipractice.domain.dto.CreatePostDTO;
import com.seongmin.restapipractice.domain.dto.UpdateCommentDTO;
import com.seongmin.restapipractice.domain.dto.UpdatePostDTO;
import com.seongmin.restapipractice.message.ResponseMessage;
import com.seongmin.restapipractice.service.Service;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/practice")
public class Controller {

    private final Service service;

    public Controller(Service service) {
        this.service = service;
    }

    @Operation(summary = "전체 게시글 조회", description = "작성된 모든 게시글 조회한다.")
    @GetMapping("/posts")
    public ResponseEntity<ResponseMessage> findAllPosts() {
        return ResponseEntity.ok()
                .body(new ResponseMessage(
                        200,
                        "전체 게시글 조회 성공",
                        service.findAllPost()
                ));
    }

    @Operation(summary = "아이디로 게시글 조회", description = "아이디를 입력하여 게시글을 조회한다.")
    @GetMapping("/posts/{postId}")
    public ResponseEntity<ResponseMessage> findPostById(@PathVariable Long postId) {
        return ResponseEntity.ok()
                .body(new ResponseMessage(
                        200,
                        "아이디로 게시글 조회 성공"
                        , service.findPostById(postId)
                ));
    }

    @Operation(summary = "새로운 게시글 작성", description = "새로운 게시글을 작성한다.")
    @PostMapping("/posts")
    public ResponseEntity<?> createPost(@RequestBody CreatePostDTO createPost) {

        CreatePostDTO newPost = service.createPost(createPost);

        return ResponseEntity.ok()
                .body(new ResponseMessage(
                        201,
                        "게시글 등록 성공",
                        newPost
                ));
    }

    @Operation(summary = "게시글 수정", description = "아이디와 내용을 입력하여 게시글을 수정한다.")
    @PutMapping("/posts/{postId}")
    public ResponseEntity<?> updatePost(@PathVariable Long postId, @RequestBody UpdatePostDTO updateInfo) {

        UpdatePostDTO updatePost = service.updatePost(postId, updateInfo);

        return ResponseEntity.ok()
                .body(new ResponseMessage(
                        201,
                        "게시글 수정 성공",
                        updatePost
                ));
    }

    @Operation(summary = "게시글 삭제", description = "아이디를 입력하여 해당 게시글을 삭제한다.")
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId) {

        service.deletePost(postId);

        return ResponseEntity.ok()
                .body(new ResponseMessage(
                        204,
                        "게시글 삭제 성공"
                        , null
                ));
    }

    @Operation(summary = "댓글 작성", description = "게시글에 댓글을 추가한다.")
    @PostMapping("/comments/{postId}")
    public ResponseEntity<?> addComment(@PathVariable Long postId, @RequestBody CreateCommentDTO createCommentDTO) {

        CreateCommentDTO newComment = service.addComment(postId, createCommentDTO);

        return ResponseEntity.ok()
                .body(new ResponseMessage(
                        201,
                        "댓글 등록 성공",
                        newComment
                ));
    }

    @Operation(summary = "댓글 수정", description = "댓글을 수정한다.")
    @PutMapping("/comments/{commentId}")
    public ResponseEntity<?> updateComment(@PathVariable Long commentId, @RequestBody UpdateCommentDTO updateInfo) {

        UpdateCommentDTO updateComment = service.updateCommentDTO(commentId, updateInfo);

        return ResponseEntity.ok()
                .body(new ResponseMessage(
                        201,
                        "댓글 수정 성공",
                        updateComment
                ));
    }

    @Operation(summary = "댓글 삭제", description = "아이디를 입력하여 해당 댓글을 삭제한다.")
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long commentId) {

        service.deleteComment(commentId);

        return ResponseEntity.ok()
                .body(new ResponseMessage(
                        204,
                        "댓글 삭제 성공"
                        , null
                ));
    }
}
