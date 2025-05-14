package com.example.demo.service;

import com.example.demo.dto.CommentDTO;
import com.example.demo.entity.Board;
import com.example.demo.entity.Comment;
import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.CommentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    public Optional<Comment> getComment(Long commentId) {
        return commentRepository.findByCommentId(commentId);
    }

    public void postComment(CommentDTO commentDTO) {
        Board board = boardRepository.findByBoardId(commentDTO.getBoardId())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다"));

        Comment comment = Comment.builder()
                .board(board)
                .writer(commentDTO.getWriter())
                .content(commentDTO.getContent())
                .postDate(LocalDate.now())
                .build();

        commentRepository.save(comment);
    }

    @Transactional
    public void putComment(CommentDTO commentDTO) {
        Comment comment = commentRepository.findByCommentId(commentDTO.getCommentId())
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다"));

        comment = Comment.builder()
                .commentId(comment.getCommentId())
                .board(comment.getBoard())
                .writer(commentDTO.getWriter())
                .content(commentDTO.getContent())
                .postDate(LocalDate.now())
                .build();

        commentRepository.save(comment);
    }

    @Transactional
    public void deleteComment(Long commentId) {
        commentRepository.deleteByCommentId(commentId);
    }
}
