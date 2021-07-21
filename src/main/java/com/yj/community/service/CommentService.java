package com.yj.community.service;

import com.yj.community.domain.board.comment.Comment;
import com.yj.community.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public ArrayList<Comment> selectList(long seq) {
        return commentRepository.selectList(seq);
    }
}
