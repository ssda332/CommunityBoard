package com.yj.community.domain.board.comment;

import lombok.Data;

@Data
public class Comment {
    private long seq;
    private String content;
    private String writer;
    private String regDate;

}
