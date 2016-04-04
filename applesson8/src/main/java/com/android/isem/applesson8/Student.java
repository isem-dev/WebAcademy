package com.android.isem.applesson8;

//import com.google.gson.annotations.SerializedName;
//
//public class Student {
//    @SerializedName("firstName")
//    public String firstName;
//
//    @SerializedName("lastName")
//    public String lastName;
//
//    @SerializedName("age")
//    public int Age;
//
//    public Student(String firstName, String lastName, int age) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        Age = age;
//    }
//}

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root
public class Student {
    @Attribute
    private String firstName;

    @Attribute
    private String lastName;

    @Attribute
    private int age;

    public Student(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
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
}
