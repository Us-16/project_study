package com.example.app.classroom;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClassroomForm {
    private String code;
    @NotEmpty(message = "제목을 입력해주세요")
    @Size(max=30)
    private String title;

    private String students;
}
