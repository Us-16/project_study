package com.example.a16sserver.springdo;

import java.io.Serializable;
import java.util.ArrayList;

public class ContainerDO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private int score;
    private String timerMin;
    private String timerSec;
    private int amount;
    private int id;
    private ArrayList<Question> questions;

    @Override
    public String toString() {
        return "ContainerDO{" +
                "name='" + name + '\'' +
                ", score=" + score +
                ", timerMin='" + timerMin + '\'' +
                ", timerSec='" + timerSec + '\'' +
                ", amount=" + amount +
                ", id=" + id +
                ", questions=" + questions +
                '}';
    }

    public ContainerDO(String name, int score, String timerMin, String timerSec, int amount, int id, ArrayList<Question> questions) {
        this.name = name;
        this.score = score;
        this.timerMin = timerMin;
        this.timerSec = timerSec;
        this.amount = amount;
        this.id = id;
        this.questions = questions;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public String getTimerMin() {
        return timerMin;
    }

    public String getTimerSec() {
        return timerSec;
    }

    public int getAmount() {
        return amount;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }
}
