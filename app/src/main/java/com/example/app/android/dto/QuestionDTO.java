package com.example.app.android.dto;

import com.example.app.question.Question;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.LocalDateTime;

public class QuestionDTO {
    @JsonProperty
    private long id;
    @JsonProperty
    private String content;
    @JsonProperty
    private String subject;
    @JsonProperty
    private LocalDateTime createDate;

    public QuestionDTO (Question entity){
        this.id = entity.getId();
        this.content = entity.getContent();
        this.subject = entity.getSubject();
        this.createDate = entity.getCreateDate();
    }

    @Override
    public String toString() {
        return "QuestionDTO = " + this.id + ":" + this.subject + ":"+this.content+":"+this.createDate;
    }
}
