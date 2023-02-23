package com.example.app.user.student;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    private String personalId;

    @Column(unique = true)
    private String username;

    private String password;

    private String email;

    private String school;

    private int grade;

    private LocalDateTime createDate;
}
