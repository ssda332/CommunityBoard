package com.yj.community.service;

import com.yj.community.domain.board.Board;
import com.yj.community.domain.board.BoardInfo;
import com.yj.community.domain.board.BoardWriteForm;
import com.yj.community.domain.board.pagination.PageInfo;
import com.yj.community.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public ArrayList<BoardInfo> getBoardList(PageInfo pi) {

        // 몇 개의 게시글을 건너 뛸 것인지
        int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
        RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());

        return boardRepository.getBoardList(rowBounds);
    }

    public int write(BoardWriteForm board) {

        return boardRepository.write(board);
    }

    public int selectListCount() {
        return boardRepository.selectListCount();
    }

    public Board findById(long seq, boolean flag) {

        if(!flag) {
            boardRepository.addReadCount(seq);
        }

        return boardRepository.selectBoard(seq);
    }
}
