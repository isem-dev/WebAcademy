package com.example.android.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Activity5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_5);
    }

    public void onClickMethod(View view) {
        Intent intent = new Intent();
        intent.putExtra(MainActivity.EXTRA_TEXT, "I am result");
        setResult(RESULT_OK, intent);

        finish();
    }
}
