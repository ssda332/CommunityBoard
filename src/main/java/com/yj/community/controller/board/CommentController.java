package com.yj.community.controller.board;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yj.community.domain.board.comment.Comment;
import com.yj.community.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{seq}")
    @ResponseBody
    public String commentList(@PathVariable long seq) {
        log.info("commentList init");

        ArrayList<Comment> cList = commentService.selectList(seq);
        log.info("commentList init = {}", cList.toString());

        Gson gson =  new GsonBuilder().create();


        return gson.toJson(cList);
    }
}
