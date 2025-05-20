package com.example.demo.repository;

import com.example.demo.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board,Long> {
    // Optional은 값이 있을 수도 있고 없을 수도 있는 상황을 처리하는 컨테이너 객체
    Optional<Board> findByBoardId(Long boardId);

    void deleteByBoardId(Long boardId);
}