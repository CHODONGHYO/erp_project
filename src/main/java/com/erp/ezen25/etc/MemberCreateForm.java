package com.erp.ezen25.etc;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberCreateForm {
    @Size(min = 3, max = 25)
    @NotEmpty(message = "아이디는 필수 항목임")
    private String userId;

    @NotEmpty(message = "비밀번호는 필수 항목")
    private String password1;

    @NotEmpty(message = "비밀번호 확인은 필수 항목")
    private String password2;

    @NotEmpty(message = "이름은 필수항목")
    private String name;

    @NotEmpty(message = "이메일은 필수 항목")
    @Email
    private String email;
}
