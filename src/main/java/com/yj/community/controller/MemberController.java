package com.yj.community.controller;

import com.yj.community.domain.Member;
import com.yj.community.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        if(result == 1) {
            System.out.println("result = 1");
            return "redirect:/";
        } else {
            System.out.println("result = else");
            return "redirect:/";
        }

    }

    @GetMapping("/login")
    public String login() {

        return "/members/signIn";
    }
}
