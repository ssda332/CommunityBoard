package com.yj.community.service;

import com.yj.community.domain.member.Member;
import com.yj.community.domain.member.MyUserDetails;
import com.yj.community.repository.MemberRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MemberService implements UserDetailsService{

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public MemberService(MemberRepository memberRepository, BCryptPasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public int save(Member member) {
        // validateDuplicateMember(member); // 중복 회원 검증

        member.setPassword(passwordEncoder.encode(member.getPassword())); // 비밀번호 암호화

        int result = memberRepository.save(member);
        if (result > 0) {
            memberRepository.grantRole();
        }
        return result;
    }

    public Member login(String loginId, String password) {
        return memberRepository.findByLoginId(loginId).filter(m -> m.getPassword().equals(password))
                .orElse(null);


    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        MyUserDetails loginId = memberRepository.findByUsername(username);

        if(loginId == null) {
            throw new UsernameNotFoundException("User "+username+" Not Found!");
        }else {
            return loginId;
        }

    }

}
