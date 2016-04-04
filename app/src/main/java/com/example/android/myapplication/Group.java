package com.example.android.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

public class Group implements Parcelable {
    public String name;
    public Student[] students;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeTypedArray(this.students, 0);
    }

    public Group() {
    }

    protected Group(Parcel in) {
        this.name = in.readString();
        this.students = in.createTypedArray(Student.CREATOR);
    }

    public static final Creator<Group> CREATOR = new Creator<Group>() {
        public Group createFromParcel(Parcel source) {
            return new Group(source);
        }

        public Group[] newArray(int size) {
            return new Group[size];
        }
    };
}
