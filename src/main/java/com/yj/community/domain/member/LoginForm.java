package com.yj.community.domain.member;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginForm {

    @NotEmpty(message = "ID를 적어주세요.")
    private String username;
    @NotEmpty(message = "비밀번호를 적어주세요.")
    private String password;

}
