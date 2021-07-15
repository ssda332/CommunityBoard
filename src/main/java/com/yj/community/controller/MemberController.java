package com.yj.community.controller;

import com.yj.community.SessionConst;
import com.yj.community.domain.LoginForm;
import com.yj.community.domain.Member;
import com.yj.community.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/add")
    public String signUp(@ModelAttribute("member") Member member) {
        return "/members/signUp";
    }

    @PostMapping("/add")
    public String save(@ModelAttribute Member member) {

        int result = memberService.save(member);
        return "redirect:/";

    }

    @GetMapping("/login")
    public String signInForm(@ModelAttribute("loginForm") LoginForm form) {

        return "/members/signIn";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginForm form, HttpServletRequest request) {

        Member loginMember = memberService.login(form.getLoginId(), form.getPassword());

        if (loginMember == null) {
            return "/members/signIn";
        }

        // 로그인 성공 처리
        // 세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성
        HttpSession session = request.getSession();
        // 세션에 로그인 회원 정보 보관
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logoutV3(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return "redirect:/";
    }

}
