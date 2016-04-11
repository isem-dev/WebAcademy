package com.android.isem.applesson10;

import android.os.Parcel;
import android.os.Parcelable;

public class Student implements Parcelable {

    public static final String TABLE_NAME = "Students";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_FIRST_NAME = "FirstName";
    public static final String COLUMN_LAST_NAME = "LastName";
    public static final String COLUMN_AGE = "Age";
    public static final String COLUMN_PHOTO_ID = "PhotoId";

    public static final String COLUMN_BIRTHDAY = "Birthday";

    private long id;
    private String firstName;
    private String lastName;
    private long age;
    private long photoID;

    public Student() {
    }

    public Student(String firstName, String lastName, long age, long photoID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.photoID = photoID;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
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

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    public long getPhotoID() {
        return photoID;
    }

    public void setPhotoID(long photoID) {
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
        dest.writeLong(this.age);
        dest.writeLong(this.photoID);
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
