package com.example.a16sserver.springdo;

import android.os.Parcel;
import android.os.Parcelable;

public class ParcelableTest implements Parcelable {
    private Long id;
    private String title;

    protected ParcelableTest(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        title = in.readString();
        choice1 = in.readString();
        choice2 = in.readString();
        choice3 = in.readString();
        choice4 = in.readString();
        choice5 = in.readString();
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

    private String choice1;
    private String choice2;
    private String choice3;
    private String choice4;
    private String choice5;

}
