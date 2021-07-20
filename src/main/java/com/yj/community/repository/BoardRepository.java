package com.yj.community.repository;

import com.yj.community.domain.board.Board;
import com.yj.community.domain.board.BoardInfo;
import com.yj.community.domain.board.BoardWriteForm;
import com.yj.community.domain.board.pagination.PageInfo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface BoardRepository {

    @Select("SELECT BD_SEQ, BD_REG_MEM, BD_SUBJECT, TO_CHAR(BD_REG_DATE, 'YYYY-MM-DD') REG_DATE, BD_VIEW_COUNT FROM TB_BOARD ORDER BY BD_SEQ DESC")
    @Results({
            @Result(property = "seq", column = "BD_SEQ"),
            @Result(property = "registerMember", column = "BD_REG_MEM"),
            @Result(property = "subject", column = "BD_SUBJECT"),
            @Result(property = "registerDate", column = "REG_DATE"),
            @Result(property = "viewCount", column = "BD_VIEW_COUNT")
    })
    ArrayList<BoardInfo> getBoardList(RowBounds rowBounds);

    @Insert("INSERT INTO TB_BOARD VALUES(TB_BOARD_SEQ.NEXTVAL, #{board.subject}, #{board.context}, DEFAULT, DEFAULT,  #{board.writer}, DEFAULT, DEFAULT)")
    int write(@Param("board") BoardWriteForm board);

    @Select("SELECT COUNT(*) FROM TB_BOARD WHERE BD_DEL_YN = 'N'")
    int selectListCount();

    @Update("UPDATE TB_BOARD SET BD_VIEW_COUNT=BD_VIEW_COUNT+1 WHERE BD_SEQ=#{seq}")
    void addReadCount(@Param("seq") long seq);

    @Select("SELECT BD_SEQ, BD_REG_MEM, BD_SUBJECT, BD_CONTENT, TO_CHAR(BD_REG_DATE, 'YYYY-MM-DD') REG_DATE, BD_VIEW_COUNT FROM TB_BOARD WHERE BD_SEQ=#{seq} AND BD_DEL_YN='N'")
    @Results({
            @Result(property = "seq", column = "BD_SEQ"),
            @Result(property = "registerMember", column = "BD_REG_MEM"),
            @Result(property = "subject", column = "BD_SUBJECT"),
            @Result(property = "content", column = "BD_CONTENT"),
            @Result(property = "registerDate", column = "REG_DATE"),
            @Result(property = "viewCount", column = "BD_VIEW_COUNT")
    })
    Board selectBoard(@Param("seq") long seq);
}
