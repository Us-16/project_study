package com.example.a16sserver.springdo;

import android.os.Parcel;
import android.os.Parcelable;

public class ParcelableTest implements Parcelable {
    Long id;
    String title;
    String choice1;
    String choice2;
    String choice3;
    String choice4;
    String choice5;

    public ParcelableTest(){

    }

    public ParcelableTest(Parcel in) {
        readFromParcel(in);
    }

    public ParcelableTest(Long id, String title, String choice1, String choice2, String choice3, String choice4, String choice5){
        this.id = id;
        this.title = title;
        this.choice1 = choice1;
        this.choice2 = choice2;
        this.choice3 = choice3;
        this.choice4 = choice4;
        this.choice5 = choice5;
    }

    private void readFromParcel(Parcel in){
        this.id = in.readLong();
        this.title = in.readString();
        this.choice1 = in.readString();
        this.choice2 = in.readString();
        this.choice3 = in.readString();
        this.choice4 = in.readString();
        this.choice5 = in.readString();
    }


    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(title);
        parcel.writeString(choice1);
        parcel.writeString(choice2);
        parcel.writeString(choice3);
        parcel.writeString(choice4);
        parcel.writeString(choice5);

    }


    public static final Creator<ParcelableTest> CREATOR = new Creator<ParcelableTest>() {
        @Override
        public ParcelableTest createFromParcel(Parcel in) {
            return new ParcelableTest(in);
        }

        @Override
        public ParcelableTest[] newArray(int size) {
            return new ParcelableTest[size];
        }
    };

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
