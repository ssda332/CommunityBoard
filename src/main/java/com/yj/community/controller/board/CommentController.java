package com.yj.community.controller.board;



import com.yj.community.domain.board.comment.Comment;
import com.yj.community.domain.board.comment.CommentWriteForm;
import com.yj.community.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/board/comment")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("{seq}")
    public ArrayList<Comment> commentList(@PathVariable long seq) {

        ArrayList<Comment> cList = commentService.selectList(seq);

/*        Gson gson =  new GsonBuilder().create();
        return gson.toJson(cList);*/

        return cList;
    }

    @PostMapping("{seq}")
    public String writeComment(@Validated @ModelAttribute CommentWriteForm form, BindingResult bindingResult, @PathVariable long seq) {

        if (bindingResult.hasErrors()) {
            return "bindingError";
        }

        int result = commentService.writeComment(form, seq);

        if (result > 0) {
            return "success";
        } else {
            return "error";
        }
    }

    @PostMapping("update/{seq}")
    public String updateComment(@Validated @ModelAttribute CommentWriteForm form, BindingResult bindingResult, @PathVariable long seq) {
        if (bindingResult.hasErrors()) {
            return "bindingError";
        }

        int result = commentService.updateComment(form, seq);

        if (result > 0) {
            return "success";
        } else {
            return "error";
        }
    }

    @PostMapping("delete/{seq}")
    public String deleteComment(@PathVariable long seq) {

        int result = commentService.deleteComment(seq);

        if (result > 0) {
            return "success";
        } else {
            return "error";
        }
    }
}
