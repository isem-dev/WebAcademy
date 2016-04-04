package com.android.isem.webacademylesson3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ActivityAbout extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        mTextView = (TextView) findViewById(R.id.textViewAbout);
        mTextView.setText(R.string.about_text);
    }
}
