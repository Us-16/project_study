package com.example.app.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateForm {
    @Size(min=3, max=16) //카트라이더 최신 게임의 최대 닉네임이 16자인거 아십니까?ㅋㅋㅋ
    @NotEmpty(message = "최소 3글자 이상, 최대 16자 이내로 작성해주세요")
    private String username;

    @NotEmpty(message = "비밀번호는 필수항목입니다.")
    private String password1;

    @NotEmpty(message="비밀번호 확인은 필수입니다.")
    private String password2;

    @NotEmpty(message = "성함을 입력해주세요")
    private String name;

    @NotEmpty(message = "주민등록번호를 입력해주세요")
    private String personalId1;

    @NotEmpty(message = "주민등록번호를 입력해주세요")
    private String personalId2;

    @NotEmpty(message="이메일은 필수항목입니다.")
    @Email
    private String email;

    @NotEmpty(message = "선생님들의 소속을 입력주세요")
    private String school;
}
