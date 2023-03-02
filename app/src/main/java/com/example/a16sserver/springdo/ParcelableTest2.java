package com.example.a16sserver.springdo;

import java.io.Serializable;

public class ParcelableTest2 implements Serializable {
    private static final long serialVersionUID = 1L;

    Long id;
    String title;
    String choice1;
    String choice2;
    String choice3;
    String choice4;
    String choice5;
    public ParcelableTest2(Long id, String title, String choice1, String choice2, String choice3, String choice4, String choice5){
        this.id = id;
        this.title = title;
        this.choice1 = choice1;
        this.choice2 = choice2;
        this.choice3 = choice3;
        this.choice4 = choice4;
        this.choice5 = choice5;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
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
