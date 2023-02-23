package com.example.app.user.teacher;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Teacher Table 관리
 * username, password, name, personalId(주민등록번호), email, school, createDate, modifiedDate
 */
@Getter
@Setter
@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String username; //대소문자 구분해야함

    @NotNull
    private String password; //대소문자 구분해야함 -> 얜 암호화 걸 때 대소문자 자체가 차이가 나는걸로 알고 있음

    @NotNull
    private String name;

    @NotNull
    @Column(unique = true)
    private String personalId;

    @NotNull
    private String email;

    @NotNull
    private String school;

    @NotNull
    private LocalDateTime createDate;

    private LocalDateTime modifiedDate;
}
