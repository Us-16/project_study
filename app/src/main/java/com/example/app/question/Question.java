package com.example.app.question;

import com.example.app.QuestionImage;
import com.example.app.answer.Answer;
import com.example.app.user.teacher.Teacher;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //PK

    @JsonIgnore
    @ManyToOne
    private Teacher teacher; //FK

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<QuestionImage> imageList;

    @Column
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String filepath; //image
    private String answer;   //정답
    //1~5번
    private String choice1;
    private String choice2;
    private String choice3;
    private String choice4;
    private String choice5;

    private Time time;

    private long view;
    private long correct;
    private double score;

    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
}
