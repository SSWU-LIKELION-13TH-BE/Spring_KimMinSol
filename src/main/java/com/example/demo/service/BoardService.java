package com.example.demo.service;

import com.example.demo.dto.BoardDTO;
import com.example.demo.entity.Board;
import com.example.demo.repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

@Slf4j // log 찍을 수 있게 해주는 어노테이션
// INFO DEBUG WARN ERROR 등 로그 라벨 제공
// 로그 파일(.log)로 저장하여 운영 환경에서도 확인 가능 등 여러가지 사유로 print 대신 log 로 출력 찍어봄

@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final S3Service s3Service;

    public BoardService(BoardRepository boardRepository, S3Service s3Service) {
        this.boardRepository = boardRepository;
        this.s3Service = s3Service;
    }

    public Optional<Board> getBoard(Long boardId) {
        return boardRepository.findByBoardId(boardId);
    }

    public void postBoard(Board board) {
        boardRepository.save(board);
    }

    @Transactional
    public void putBoard(BoardDTO boardDTO) {
        Board board = Board.builder()
                .boardId(boardDTO.getBoardId())
                .title(boardDTO.getTitle())
                .content(boardDTO.getContent())
                .writer(boardDTO.getWriter())
                .postDate(LocalDate.now())
                .build();

        // save 시 기존에 있는 객체라면 merge
        // id를 전달하지 않을 경우 Insert 수행
        // id를 전달할 경우 해당 id에 대한 데이터가 있다면 Update 수행
        // id를 전달할 경우 해당 id에 대한 데이터가 있다면 Insert 수행

        boardRepository.save(board);
    }

    @Transactional
    public void ImageBoard(BoardDTO request) throws IOException {

        String savedImageURI = s3Service.upload(request.getImage());

        Board board = Board.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .writer(request.getWriter())
                .image(savedImageURI)
                .build();

        boardRepository.save(board);
    }

    @Transactional
    public void deleteBoard(Long boardId) {
        Optional<Board> optionalBoard = boardRepository.findByBoardId(boardId);

        if (optionalBoard.isPresent()) {
            Board board = optionalBoard.get();

            if (board.getImage() != null) {
                s3Service.delete(board.getImage());
            }
            boardRepository.deleteByBoardId(boardId);
        }
        else {
            throw new IllegalArgumentException("게시글이 존재하지 않습니다.");
        }
    }

}
