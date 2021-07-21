package com.yj.community.domain.board;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class BoardUpdateForm {

    @NotEmpty(message = "제목을 적어주세요.")
    private String subject; // 제목
    @NotEmpty(message = "내용을 적어주세요.")
    private String editordata; // 내용
}
