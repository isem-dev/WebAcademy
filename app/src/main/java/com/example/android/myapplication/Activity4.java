package com.example.android.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Activity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);

        Intent intent = getIntent();
        Group group = intent.getParcelableExtra(MainActivity.EXTRA_GROUP);

        TextView textView = (TextView) findViewById(R.id.textView4);
        textView.setText(String.format("%s, students: %s", group.name, group.students.length));

//        textView.setText(String.valueOf(group.students.length)); // should use valueOf to avoid error
    }
}
