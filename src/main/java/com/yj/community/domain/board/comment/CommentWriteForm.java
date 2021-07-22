package com.yj.community.domain.board.comment;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Data
public class CommentWriteForm {

    private String writer;
    @Length(max = 400, message = "내용은 400자 이하로 적어주세요.")
    @NotEmpty(message = "내용을 적어주세요.")
    private String content;
}
