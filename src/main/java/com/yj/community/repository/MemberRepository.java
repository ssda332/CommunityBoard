package com.yj.community.repository;

import com.yj.community.domain.Member;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MemberRepository {

    int save(Member member);
}
