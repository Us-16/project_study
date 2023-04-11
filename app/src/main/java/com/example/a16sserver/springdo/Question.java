package com.example.a16sserver.springdo;

import java.io.Serializable;

public class Question implements Serializable {

    Long id;
    String subject;
    String content;
    String choice1;
    String choice2;
    String choice3;
    String choice4;
    String choice5;
    String filepath;

    public String getFilepath() {
        return filepath;
    }

    public String getContent() {
        return content;
    }

    public Question(Long id, String subject, String content, String choice1, String choice2, String choice3, String choice4, String choice5, String filepath){
        this.id = id;
        this.subject = subject;
        this.content = content;
        this.choice1 = choice1;
        this.choice2 = choice2;
        this.choice3 = choice3;
        this.choice4 = choice4;
        this.choice5 = choice5;
        this.filepath = filepath;
    }

    public Long getId() {
        return id;
    }

    public String getSubject() {
        return subject;
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
}
