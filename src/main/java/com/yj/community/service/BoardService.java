package com.yj.community.service;

import com.yj.community.domain.board.BoardInfo;
import com.yj.community.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public ArrayList<BoardInfo> getBoardList() {
        return boardRepository.getBoardList();
    }
}
