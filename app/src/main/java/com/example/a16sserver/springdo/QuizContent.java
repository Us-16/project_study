package com.example.a16sserver.springdo;

public class QuizContent {
    private String name;
    private int score;
    private String timerMin;
    private String timerSec;
    private int amount;
    private int id;

    @Override
    public String toString() {
        return "QuizContent{" +
                "name='" + name + '\'' +
                ", score='" + score + '\'' +
                ", timerMin='" + timerMin + '\'' +
                ", timerSec='" + timerSec + '\'' +
                ", amount='" + amount + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getTimerMin() {
        return timerMin;
    }

    public void setTimerMin(String timerMin) {
        this.timerMin = timerMin;
    }

    public String getTimerSec() {
        return timerSec;
    }

    public void setTimerSec(String timerSec) {
        this.timerSec = timerSec;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
