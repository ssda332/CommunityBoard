package com.yj.community.service;

import com.yj.community.domain.Member;
import com.yj.community.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public int save(Member member) {
        System.out.println("member.getName() = " + member.getName());
        // validateDuplicateMember(member); // 중복 회원 검증
        int result = memberRepository.save(member);
        return result;
    }

}
