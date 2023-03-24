package com.example.app.classroom.qna;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QnAForm {
    @NotEmpty(message = "제목을 입력해주세요")
    @Size(min=1, max=50)
    private String title;
    @NotEmpty(message = "질문 내용을 입력해주세요")
    @Size(min = 1)
    private String content;
}
