package com.example.app.classroom.qna;

import com.example.app.classroom.ClassRoom;
import com.example.app.user.student.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class QnA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Student student;

    @ManyToOne
    private ClassRoom classRoom;

    @Column(nullable = false)
    private String title;
    @Column(columnDefinition = "LongText", nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
}
