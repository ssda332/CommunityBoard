package com.yj.community.controller;

import com.yj.community.SessionConst;
import com.yj.community.domain.member.LoginForm;
import com.yj.community.domain.member.Member;
import com.yj.community.domain.member.MyUserDetails;
import com.yj.community.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public String save(@Validated @ModelAttribute Member member, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "members/signUp";
        }

        int result = memberService.save(member);

        return "redirect:/";

    }

    @GetMapping("/login")
    public String signInForm(@ModelAttribute("loginForm") LoginForm form) {

        return "/members/signIn";
    }

    //@PostMapping("/login")
    public String login(@Validated @ModelAttribute LoginForm form, BindingResult bindingResult,
                        @RequestParam(defaultValue = "/") String redirectURL, HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            return "/members/signIn";
        }

        Member loginMember = memberService.login(form.getLoginId(), form.getPassword());

        if (loginMember == null) {

            request.setAttribute("failAlert", "아이디와 비밀번호를 다시 확인해주세요.");

            return "/members/signIn";
        }

        // 로그인 성공 처리
        // 세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성
        HttpSession session = request.getSession();

        // 세션에 로그인 회원 정보 보관
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        return "redirect:" + redirectURL;
    }

    //@PostMapping("/login")
    public String loginV2(@Validated @ModelAttribute LoginForm form, BindingResult bindingResult,
                        @RequestParam(defaultValue = "/") String redirectURL, HttpServletRequest request) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails myUserDetails = (MyUserDetails) auth.getPrincipal();
        //위의 두줄로 로그인 한 계정이 어떤 권한을 가지고 있는지 이름은 무었인지를 가져옵니다
        System.out.println("username : "+myUserDetails.getUsername());
        HttpSession session = request.getSession();

        System.out.println("username : "+myUserDetails.getUsername());
        System.out.println("authorities : "+myUserDetails.getAuthorities());

        session.setAttribute("member", myUserDetails.getUsername());
        //session.setAttribute("principal", myUserDetails.getAuthorities());


        return "redirect:" + redirectURL;
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
