package com.yj.community.repository;

import com.yj.community.domain.board.comment.Comment;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface CommentRepository {

    @Select("SELECT CMT_SEQ, CMT_CONTENT, CMT_REG_MEM, TO_CHAR(CMT_REG_DATE, 'YYYY-MM-DD') CMT_REG_DATE FROM TB_COMMENT WHERE CMT_DEL_YN='N' AND BD_SEQ=#{board}")
    @Results({
            @Result(property = "seq", column = "CMT_SEQ"),
            @Result(property = "content", column = "CMT_CONTENT"),
            @Result(property = "writer", column = "CMT_REG_MEM"),
            @Result(property = "regDate", column = "CMT_REG_DATE")
    })
    ArrayList<Comment> selectList(@Param("board") long seq);

}
