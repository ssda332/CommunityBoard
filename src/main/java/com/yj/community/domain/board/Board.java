package com.yj.community.domain.board;

import lombok.Data;

@Data
public class Board {
    private long seq;
    private String subject;
    private String content;
    private String registerDate;
    private String registerMember;
    private int viewCount;
}
