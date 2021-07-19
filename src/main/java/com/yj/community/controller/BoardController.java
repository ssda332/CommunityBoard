package com.yj.community.controller;

import com.yj.community.domain.board.BoardInfo;
import com.yj.community.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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

        ArrayList<BoardInfo> boardList = boardService.getBoardList();

        if(boardList != null) {
            mv.addObject("list", boardList);
            mv.setViewName("board/boardList");
        } else {
            mv.setViewName("home");
        }

        /*return "board/boardList";*/
        return mv;
    }

    @GetMapping("write")
    public String writeBoard(Model model) {
        return "/board/writeBoard";
    }
}
