package com.example.demo.dto;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private Long commentId;
    private Long boardId;
    private String writer;
    private String content;
    private LocalDate postDate;
}
