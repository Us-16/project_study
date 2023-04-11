package com.example.a16sserver.teacher;

import android.os.Parcel;
import android.os.Parcelable;

public class StudentParcelable implements Parcelable {
    private String name;
    private int num;

    public StudentParcelable(String name, int num){
        this.name = name;
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public int getNum() {
        return num;
    }
    protected StudentParcelable(Parcel in){
        name = in.readString();
        num = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(num);
    }

    public static final Creator<StudentParcelable> CREATOR = new Creator<StudentParcelable>() {
        @Override
        public StudentParcelable createFromParcel(Parcel in) {
            return new StudentParcelable(in);
        }

        @Override
        public StudentParcelable[] newArray(int size) {
            return new StudentParcelable[size];
        }
    };
}
