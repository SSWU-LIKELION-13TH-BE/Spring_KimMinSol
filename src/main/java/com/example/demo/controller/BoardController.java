package com.example.demo.controller;

import com.example.demo.dto.BoardDTO;
import com.example.demo.entity.Board;
import com.example.demo.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
// import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
@Slf4j // log 찍을 수 있게 해주는 어노테이션

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

//    @GetMapping("/getBoard")
//    public Optional<Board> getBoard(@RequestParam(name = "boardId") Long boardId) {
//        return boardService.getBoard(boardId);
//    }

    @PostMapping("/postBoard")
    public void postBoard(@RequestBody BoardDTO boardDTO) {
        Board board = Board.builder() // new board -> board.setter()
                .title(boardDTO.getTitle())
                .content(boardDTO.getContent())
                .writer(boardDTO.getWriter())
                .build();
        boardService.postBoard(board);

        // 객체에 builder를 붙이면 필드 개수를 1가지, 2가지 ... 자유자재로 객체생성 가능
    }

    @PutMapping("/putBoard")
    public void putBoard(@RequestBody BoardDTO boardDTO) {
        boardService.putBoard(boardDTO);
    }

    @DeleteMapping("/deleteBoard/{boardId}")
    public void deleteBoard(@PathVariable(name = "boardId") Long boardId) {
        boardService.deleteBoard(boardId);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@ModelAttribute BoardDTO imageBoardDTO) {
        try {
            BoardDTO request = BoardDTO.builder()
                    .title(imageBoardDTO.getTitle())
                    .content(imageBoardDTO.getContent())
                    .image(imageBoardDTO.getImage())
                    .writer(imageBoardDTO.getWriter())
                    .build();
            // 'postman' 에서 실습할 때 title, content, image, writer 모두 쓰고 send
            // POST->http://localhost:8080/api/board/upload->Body->form-data

            boardService.ImageBoard(request);

            return ResponseEntity.ok("파일 업로드 성공");
        } catch (Exception e) {
            log.error("파일 업로드 실패", e);
            return ResponseEntity.status(400).build();
        }
    }

    @GetMapping("/getBoard")
    public ResponseEntity<BoardDTO> getBoard(@RequestParam(name = "boardId") Long boardId) {
        Optional<Board> board = boardService.getBoard(boardId);

        return board.map(value -> ResponseEntity.ok(BoardDTO.fromEntity(value)))
                .orElse(ResponseEntity.notFound().build());
    }
}

// http://localhost:8080/boardId=1 (null 허용)
// http://localhost:8080/1 (null X; id나 고유값 찾는데에 많이 씀)