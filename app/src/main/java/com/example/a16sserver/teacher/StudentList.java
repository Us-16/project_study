package com.example.a16sserver.teacher;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class StudentList implements Parcelable {
    private ArrayList<StudentParcelable> students;

    public StudentList(ArrayList<StudentParcelable> students){
        this.students = students;
    }

    public ArrayList<StudentParcelable> getStudents() {
        return students;
    }

    protected StudentList(Parcel in){
        students = in.createTypedArrayList(StudentParcelable.CREATOR);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(students);
    }

    public static final Creator<StudentList> CREATOR = new Creator<StudentList>() {
        @Override
        public StudentList createFromParcel(Parcel in) {
            return new StudentList(in);
        }

        @Override
        public StudentList[] newArray(int size) {
            return new StudentList[size];
        }
    };
}
