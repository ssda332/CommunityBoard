package com.yj.community.repository;

import com.yj.community.domain.member.Member;
import com.yj.community.domain.member.MyUserDetails;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Mapper
@Repository
public interface MemberRepository {

    @Insert("INSERT INTO tb_user VALUES(TB_USER_SEQ.NEXTVAL, #{member.loginId}, #{member.password}, #{member.name}, DEFAULT, DEFAULT, DEFAULT, NULL)")
    int save(@Param("member") Member member);

    @Insert("INSERT INTO TB_USER_ROLE VALUES(TB_USER_SEQ.CURRVAL, 1)")
    int grantRole();

    @Select("SELECT * FROM TB_USER WHERE MEM_USERID = #{loginId}")
    @Results({
            @Result(property = "loginId", column = "MEM_USERID"),
            @Result(property = "password", column = "MEM_PASSWORD"),
            @Result(property = "name", column = "MEM_NICKNAME")
    })
    Optional<Member> findByLoginId(@Param("loginId") String loginId);

    @Select("SELECT * FROM TB_USER WHERE MEM_USERID = #{username}")
    @Results({
            @Result(property = "username", column = "MEM_USERID"),
            @Result(property = "password", column = "MEM_PASSWORD")
    })
    Optional<MyUserDetails> findByUsername(@Param("username") String username);
}
