package com.yj.community.repository;

import com.yj.community.domain.board.BoardInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface BoardRepository {

    @Select("SELECT BD_SEQ, BD_REG_MEM, BD_SUBJECT, TO_CHAR(BD_REG_DATE, 'YYYY-MM-DD') REG_DATE, BD_VIEW_COUNT FROM TB_BOARD WHERE ROWNUM <= 10 ORDER BY 1 DESC")
    @Results({
            @Result(property = "seq", column = "BD_SEQ"),
            @Result(property = "registerMember", column = "BD_REG_MEM"),
            @Result(property = "subject", column = "BD_SUBJECT"),
            @Result(property = "registerDate", column = "REG_DATE"),
            @Result(property = "viewCount", column = "BD_VIEW_COUNT")
    })
    ArrayList<BoardInfo> getBoardList();
}
