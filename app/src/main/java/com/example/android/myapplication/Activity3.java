package com.example.android.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Activity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        Intent intent = getIntent();
        Student student = intent.getParcelableExtra(MainActivity.EXTRA_STUDENT);

        TextView textView = (TextView) findViewById(R.id.textView3);
        textView.setText(student.toString());

//        textView.setText(student.firstName + " " + student.lastName + ", age: " + student.age);
//        textView.setText(String.format("%s %s, age: %s"
//                , student.firstName
//                , student.lastName
//                , student.age));
    }
}
