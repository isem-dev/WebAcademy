package com.android.isem.webacademylesson3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        Intent intent = getIntent();
        Student student = intent.getParcelableExtra(MainActivity.EXTRA_STUDENT);

        ImageView imageView = (ImageView) findViewById(R.id.imageViewView);
        imageView.setImageResource(student.getPhotoID());

        TextView textViewFirstName = (TextView) findViewById(R.id.firstNameView);
        textViewFirstName.setText(student.getFirstName());

        TextView textViewSecondName = (TextView) findViewById(R.id.lastNameView);
        textViewSecondName.setText(student.getLastName());

        TextView textViewAge = (TextView) findViewById(R.id.ageView);
        textViewAge.setText(String.valueOf(student.getAge()));
    }
}
