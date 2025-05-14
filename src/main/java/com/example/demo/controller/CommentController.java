package com.example.demo.controller;

import com.example.demo.dto.CommentDTO;
import com.example.demo.entity.Comment;
import com.example.demo.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    /*
    public Optional<Comment> getComment(@RequestParam Long commentId) {
        return commentService.getComment(commentId);
    }
    */
    // Optional<Comment> 반환이 안돼서 DTO 형태로 반환
    public CommentDTO getComment(@RequestParam Long commentId) {
        Comment comment = commentService.getComment(commentId)
                .orElseThrow(() -> new RuntimeException("댓글이 없습니다"));

        return new CommentDTO(
                comment.getCommentId(),
                comment.getBoard().getBoardId(),
                comment.getWriter(),
                comment.getContent(),
                comment.getPostDate()
        );
    }

    @PostMapping
    public void postComment(@RequestBody CommentDTO commentDTO) {
        commentService.postComment(commentDTO);
    }

    @PutMapping
    public void putComment(@RequestBody CommentDTO commentDTO) {
        commentService.putComment(commentDTO);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
    }
}
