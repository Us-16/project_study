package com.example.a16sserver.springdo;

import com.example.a16sserver.image.Teacher;

import java.sql.Time;
import java.time.LocalDateTime;

public class Question {
    private Long id; //PK

    private Teacher teacher; //FK

    private String subject;

    @Override
    public String toString() {
        return "Question{" +
                "content='" + content + '\'' +
                ", filepath='" + filepath + '\'' +
                ", choice1='" + choice1 + '\'' +
                ", choice2='" + choice2 + '\'' +
                ", choice3='" + choice3 + '\'' +
                ", choice4='" + choice4 + '\'' +
                ", choice5='" + choice5 + '\'' +
                ", createDate='" + createDate + '\'' +
                '}';
    }

    private String content;

    public Long getId() {
        return id;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    public String getFilepath() {
        return filepath;
    }

    public String getAnswer() {
        return answer;
    }

    public String getChoice1() {
        return choice1;
    }

    public String getChoice2() {
        return choice2;
    }

    public String getChoice3() {
        return choice3;
    }

    public String getChoice4() {
        return choice4;
    }

    public String getChoice5() {
        return choice5;
    }

    public Time getTime() {
        return time;
    }

    public long getView() {
        return view;
    }

    public long getCorrect() {
        return correct;
    }

    public double getScore() {
        return score;
    }

    public String getCreateDate() {
        return createDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

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

    private String createDate; //야이 XXX야
    private String modifiedDate;
}
