package com.example.android.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_TEXT = "com.example.android.myapplication.TEXT_KEY";
    public static final String EXTRA_STUDENT = "com.example.android.myapplication.STUDENT_KEY";
    public static final String EXTRA_GROUP = "com.example.android.myapplication.GROUP_KEY";

    public static final int REQUEST_CODE_5 = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickMethod(View v) {
        switch (v.getId()) {
            case R.id.button:
                Intent intent = new Intent(this, Activity2.class);

                intent.putExtra(EXTRA_TEXT, "I am parameter");
                startActivity(intent);
                break;
            case R.id.button2:
                Intent intent2 = new Intent(this, Activity3.class);
                Student student = new Student("Alex", "Mill", 24);

                intent2.putExtra(EXTRA_STUDENT, student);
                startActivity(intent2);
                break;
            case R.id.button3:
                Intent intent3 = new Intent(this, Activity4.class);

                Group group = new Group();
                group.name = "Android Dev";
                group.students = new Student[] {
                        new Student("Kostia", "Kosmos", 25),
                        new Student("Igor", "Sem", 30),
                        new Student("Zoia", "Zeta", 31)
                };

                intent3.putExtra(EXTRA_GROUP, group);
                startActivity(intent3);
                break;
            case R.id.button4:
                Intent intent4 = new Intent(this, Activity5.class);

                startActivityForResult(intent4, REQUEST_CODE_5);
                break;
            case R.id.button5:
                Intent intent5 = new Intent(this, Activity6.class);

                startActivity(intent5);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_5 && resultCode == RESULT_OK) {
            String text = data.getStringExtra(EXTRA_TEXT);
            Button button = (Button) findViewById(R.id.button4);

            button.setText(text);
        }
    }
}
