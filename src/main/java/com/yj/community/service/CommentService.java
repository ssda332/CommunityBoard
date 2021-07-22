package com.yj.community.service;

import com.yj.community.domain.board.comment.Comment;
import com.yj.community.domain.board.comment.CommentWriteForm;
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

    public int writeComment(CommentWriteForm form, long seq) {
        return commentRepository.writeComment(form, seq);
    }

    public int updateComment(CommentWriteForm form, long seq) {
        return commentRepository.updateComment(form, seq);
    }

    public int deleteComment(long seq) {
        return commentRepository.deleteComment(seq);
    }
}
