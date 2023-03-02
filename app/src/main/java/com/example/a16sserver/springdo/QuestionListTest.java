package com.example.a16sserver.springdo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class QuestionListTest implements Parcelable {
    private ArrayList<Question> questions;
    public QuestionListTest(Parcel in){
        readFromParcel(in);
    }
    public QuestionListTest(ArrayList<Question> questions){
        this.questions = questions;
    }

    private void readFromParcel(Parcel in) {
        if(in.readByte() == 0x01){
            questions = new ArrayList<Question>();
            in.readArrayList(Question.class.getClassLoader());
        }else{
            questions = null;
        }

    }


    public static final Creator<QuestionListTest> CREATOR = new Creator<QuestionListTest>() {
        @Override
        public QuestionListTest createFromParcel(Parcel in) {
            return new QuestionListTest(in);
        }

        @Override
        public QuestionListTest[] newArray(int size) {
            return new QuestionListTest[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if(questions == null){
            parcel.writeByte((byte) (0x00));
        }else{
            parcel.writeByte((byte) (0x01));
            parcel.writeList(questions);
        }
    }

    public ArrayList<Question> getQuestions(){
        return this.questions;
    }
}
