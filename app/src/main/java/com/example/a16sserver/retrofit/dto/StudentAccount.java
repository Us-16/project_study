package com.example.a16sserver.retrofit.dto;

public class StudentAccount {
    private String name;
    private String P_id1; //주민등록번호
    private String P_id2;
    private String username;
    private String password;
    private String email;
    private String school;
    private String grade;

    public void setName(String name) {
        this.name = name;
    }

    public void setP_id1(String p_id1) {
        P_id1 = p_id1;
    }

    public void setP_id2(String p_id2) {
        P_id2 = p_id2;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public String getP_id1() {
        return P_id1;
    }

    public String getP_id2() {
        return P_id2;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getSchool() {
        return school;
    }

    public String getGrade() {
        return grade;
    }
}
