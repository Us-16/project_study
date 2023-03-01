package com.example.a16sserver.dtos;

import java.time.LocalDateTime;

public class Question {
    private Long id; //PK
    private Long teacher_id; //FK
    private String subject; //과목
    private String title; //제목
    private String content;
    private String filepath; //image
    private String answer;   //정답
    //1~5번
    private String choice1;
    private String choice2;

    public void setId(Long id) {
        this.id = id;
    }

    public void setTeacher_id(Long teacher_id) {
        this.teacher_id = teacher_id;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setChoice1(String choice1) {
        this.choice1 = choice1;
    }

    public void setChoice2(String choice2) {
        this.choice2 = choice2;
    }

    public void setChoice3(String choice3) {
        this.choice3 = choice3;
    }

    public void setChoice4(String choice4) {
        this.choice4 = choice4;
    }

    public void setChoice5(String choice5) {
        this.choice5 = choice5;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setView(long view) {
        this.view = view;
    }

    public void setCorrect(long correct) {
        this.correct = correct;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Long getId() {
        return id;
    }

    public Long getTeacher_id() {
        return teacher_id;
    }

    public String getSubject() {
        return subject;
    }

    public String getTitle() {
        return title;
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

    public String getTime() {
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

    public String getModifyDate() {
        return modifyDate;
    }

    private String choice3;
    private String choice4;
    private String choice5;
    private String time;
    private long view;
    private long correct;
    private double score;
    private String createDate;
    private String modifyDate;
}
