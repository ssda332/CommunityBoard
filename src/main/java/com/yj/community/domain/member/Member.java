package com.yj.community.domain.member;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class Member {
    private Long id;

    @Pattern(regexp = "^[a-zA-Z0-9]{4,12}$", message = "ID는 숫자와 영문 대,소문자 조합으로 4자 이상 12자 이하입니다.")
    private String loginId; // 로그인 ID
    @Pattern(regexp = "^[a-zA-Z0-9가-힣]{2,6}", message = "별명은 한글, 숫자, 영문 대,소문자 2자 이상 6자 이하입니다.")
    private String name; // 사용자 별명
    @Pattern(regexp = "^[A-Za-z0-9]{8,12}$", message = "비밀번호는 영문 대,소문자와 숫자 8자 이상 12자 이하입니다.")
    private String password; // 사용자 비밀번호
}
