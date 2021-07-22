package com.yj.community.board;

import com.yj.community.domain.board.BoardInfo;
import com.yj.community.domain.board.comment.Comment;
import com.yj.community.repository.BoardRepository;
import com.yj.community.repository.CommentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
public class boardListTest {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    CommentRepository commentRepository;

    @Test
    void boardListView() {
        // ArrayList<BoardInfo> boardList = boardRepository.getBoardList();
        // 페이징 처리 추가중이어서 주석 처리했음

        // Assertions.assertThat(boardList.size()).isEqualTo(10);
        // 페이징 처리 안된 게시판 조회
    }

    @Test
    void boardListCount() {
        System.out.println("boardListCount : " + boardRepository.selectListCount() + "개의 게시물");
    }

    @Test
    void boardDetailView() {

    }

    @Test
    void commentListView() {
        long seq = 30;
        ArrayList<Comment> comments = commentRepository.selectList(seq);
        System.out.println(comments);

    }
}
