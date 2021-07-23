package com.yj.community.controller.board;

import com.yj.community.domain.board.Board;
import com.yj.community.domain.board.BoardInfo;
import com.yj.community.domain.board.BoardUpdateForm;
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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("list")
    public ModelAndView listForm(ModelAndView mv,
                                 @RequestParam(value="currentPage", required=false, defaultValue="1") Integer page) {


        int currentPage = page != null ? page : 1;

        int listCount = boardService.selectListCount(); // 전체 게시글 갯수

        PageInfo pi = Pagination.getPageInfo(currentPage, listCount);

        ArrayList<BoardInfo> boardList = boardService.getBoardList(pi);

        log.info("getBoardList = {}", boardList);

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
        return "board/writeBoard";
    }

    @PostMapping("write")
    public String write(@Validated @ModelAttribute BoardWriteForm board, BindingResult bindingResult, HttpServletRequest request) throws Exception {

        if (bindingResult.hasErrors()) {
            return "board/writeBoard";
        }

        board.setWriter(request.getParameter("writer"));

        int result = boardService.write(board);

        if (result > 0) {
            return "redirect:/board/list";
        } else {
            throw new Exception();
        }
    }

    @GetMapping("{seq}")
    public String item(@PathVariable long seq, Model model, HttpServletRequest request, HttpServletResponse response) {

        // 쿠키 값을 이용하여 게시글 읽음 여부 확인
        boolean flag = false;
        Cookie[] cookies = request.getCookies();

        Board board = null;

        if(cookies != null) {
            for(Cookie c : cookies) {
                if(c.getName().equals("seq"+seq)) {
                    // 해당 게시글에 대한 쿠키 존재(이미 게시글을 읽었음)
                    flag = true;
                }
            }
            if(!flag) { // 게시글을 처음 읽을 경우 쿠키 저장하기
                Cookie c = new Cookie("seq"+seq, String.valueOf(seq));
                c.setMaxAge(1 * 24 * 60 * 60); // 하루동안 저장
                response.addCookie(c);

            }
            board = boardService.findById(seq, flag);

        }
        model.addAttribute("boardSeq", seq);
        model.addAttribute("board", board);

        return "board/boardDetail";
    }

    @GetMapping("update/{seq}")
    public String updateForm(@PathVariable long seq, Model model) {


        // 작성자 여부 확인 검증은 인터셉터에서
        Board boardForm = boardService.findById(seq, true);
        model.addAttribute("boardForm", boardForm);

        return "board/updateBoard";
    }

    @PostMapping("update/{seq}")
    public String update(@Validated @ModelAttribute BoardUpdateForm update, BindingResult bindingResult, @PathVariable long seq) throws Exception {

        if (bindingResult.hasErrors()) {
            return "board/updateBoard";
        }

        int result = boardService.updateBoard(update, seq);

        if (result > 0) {
            return "redirect:/board/" + seq;
        } else {
            throw new Exception();
        }

    }

    @GetMapping("delete/{seq}")
    public String delete(@PathVariable long seq) throws Exception {

        log.info("delete method init");

        int result = boardService.deleteBoard(seq);

        //log.info("delete result = {}", result);

        if (result > 0) {
            return "redirect:/board/list";
        } else {
            throw new Exception();
        }
    }
}
