package com.yj.community.controller;

import com.yj.community.domain.board.BoardInfo;
import com.yj.community.domain.board.BoardWriteForm;
import com.yj.community.domain.board.pagination.PageInfo;
import com.yj.community.domain.board.pagination.Pagination;
import com.yj.community.domain.member.LoginForm;
import com.yj.community.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("list")
    public ModelAndView listForm(ModelAndView mv,
                                 @RequestParam(value="currentPage", required=false, defaultValue="1") Integer page) {


        int currentPage = page != null ? page : 1;

        int listCount = boardService.selectListCount(); // 전체 게시글 갯수

        PageInfo pi = Pagination.getPageInfo(currentPage, listCount);

        ArrayList<BoardInfo> boardList = boardService.getBoardList(pi);

        if(boardList != null) {
            mv.addObject("list", boardList);
            mv.addObject("pi", pi);
            mv.setViewName("board/boardList");
        } else {
            mv.setViewName("home");
        }
        
        return mv;
    }

    @GetMapping("write")
    public String writeBoard(@ModelAttribute("board") BoardWriteForm form) {
        return "/board/writeBoard";
    }

    @PostMapping("write")
    public String write(@Validated @ModelAttribute BoardWriteForm board, BindingResult bindingResult, HttpServletRequest request) throws Exception {

        if (bindingResult.hasErrors()) {
            return "/board/writeBoard";
        }

        board.setWriter(request.getParameter("writer"));

        int result = boardService.write(board);

        if (result > 0) {
            return "redirect:/board/list";
        } else {
            throw new Exception();
        }

    }
}
