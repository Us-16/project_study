package com.example.app.android;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class SignupDTO {
    private String stu_name;
    private String stu_username;
    private String stu_password;
    private String stu_pid;
    private String stu_email;
    private String stu_school;
    private String stu_grade;
}
