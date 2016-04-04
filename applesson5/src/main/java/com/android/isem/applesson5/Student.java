package com.android.isem.applesson5;

import android.os.Parcel;
import android.os.Parcelable;

public class Student implements Parcelable {
    private String firstName;
    private String lastName;
    private int age;
    private int photoID;

    public Student(String firstName, String lastName, int age, int photoID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.photoID = photoID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPhotoID() {
        return photoID;
    }

    public void setPhotoID(int photoID) {
        this.photoID = photoID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.firstName);
        dest.writeString(this.lastName);
        dest.writeInt(this.age);
        dest.writeInt(this.photoID);
    }

    protected Student(Parcel in) {
        this.firstName = in.readString();
        this.lastName = in.readString();
        this.age = in.readInt();
        this.photoID = in.readInt();
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        public Student createFromParcel(Parcel source) {
            return new Student(source);
        }

        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    @Override
    public String toString() {
        return String.format("%s\n%s\nAge %s", firstName, lastName, age);
    }
}
