package com.yj.community.board;

import com.yj.community.domain.board.BoardInfo;
import com.yj.community.repository.BoardRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
public class boardListTest {

    @Autowired
    BoardRepository boardRepository;

    @Test
    void boardListView() {
        ArrayList<BoardInfo> boardList = boardRepository.getBoardList();

        Assertions.assertThat(boardList.size()).isEqualTo(10);
        // 페이징 처리 안된 게시판 조회
    }
}