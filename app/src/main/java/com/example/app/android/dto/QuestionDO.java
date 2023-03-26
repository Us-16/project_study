package com.example.app.android.dto;

import com.example.app.question.question_image.QuestionImage;
import com.example.app.user.teacher.Teacher;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@Setter
public class QuestionDO {
    private Long id; //PK
    private Teacher teacher; //FK
    //private List<QuestionImage> imageList;
    private String filePath;

    private String subject;

    private String content;

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
