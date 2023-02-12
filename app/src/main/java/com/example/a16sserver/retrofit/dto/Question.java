package com.example.a16sserver.retrofit.dto;

import java.time.LocalDateTime;

public class Question {
    private long id;
    private String subject;
    private String content;
    private String createDate;

    public long getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    public String getCreateDate() {
        return createDate;
    }
}
