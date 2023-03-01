package com.example.a16sserver.dtos;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

public class QuizContent implements Serializable{
    private String name;

    @Override
    public String toString() {
        return "QuizContent{" +
                "name='" + name + '\'' +
                ", score='" + score + '\'' +
                ", timerMin='" + timerMin + '\'' +
                ", timerSec='" + timerSec + '\'' +
                ", total='" + total + '\'' +
                ", id='" + id + '\'' +
                ", questions=" + questions +
                '}';
    }

    private String score;
    private String timerMin;
    private String timerSec;
    private String total;
    private String id;

    public QuizContent(String name, String score,
                       String timerMin, String timerSec, String total, String id,
                       ArrayList<Question> questions){
        this.name = name;
        this.score = score;
        this.timerMin = timerMin;
        this.timerSec = timerSec;
        this.total = total;
        this.id = id;
        this.questions = questions;
    }
    private ArrayList<Question> questions;

    public String getName() {
        return name;
    }

    public String getScore() {
        return score;
    }

    public String getTimerMin() {
        return timerMin;
    }
    public String getTimerSec(){
        return timerSec;
    }

    public String getTotal() {
        return total;
    }

    public String getId() {
        return id;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

}
