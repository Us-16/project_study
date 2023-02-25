package com.example.a16sserver.image;

import java.time.LocalDateTime;
import java.util.List;

public class Teacher {
    private Long id;
    private String username; //대소문자 구분해야함
    private String password; //대소문자 구분해야함 -> 얜 암호화 걸 때 대소문자 자체가 차이가 나는걸로 알고 있음
    private String name;
    private String personalId;
    private String email;
    private String school;


    private LocalDateTime createDate;

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getPersonalId() {
        return personalId;
    }

    public String getEmail() {
        return email;
    }

    public String getSchool() {
        return school;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    private LocalDateTime modifiedDate;

    private List<Question> questionList;
}
