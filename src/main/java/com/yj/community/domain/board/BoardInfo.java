package com.yj.community.domain.board;

import lombok.Data;

@Data
public class BoardInfo {
    private long seq;
    private String registerMember;
    private String subject;
    private String registerDate;
    private int viewCount;
}
