package com.example.app.classroom;

import lombok.Getter;

@Getter
public class testDO {
    private int score;
    private int age;

    public testDO(int score, int age) {
        this.score = score;
        this.age = age;
    }
}
