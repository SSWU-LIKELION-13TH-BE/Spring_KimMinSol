package com.example.demo.dto;

import com.example.demo.entity.Board;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BoardDTO {
    private Long boardId;
    private String title;
    private String content;
    private String writer;
    private Date postDate;
    private MultipartFile image;
    private String imageUrl;

    public static BoardDTO fromEntity(Board board) {
        return BoardDTO.builder()
                .boardId(board.getBoardId())
                .title(board.getTitle())
                .content(board.getContent())
                .writer(board.getWriter())
                .postDate(java.sql.Date.valueOf(board.getPostDate()))
                .imageUrl(board.getImage())
                .build();
    }

}