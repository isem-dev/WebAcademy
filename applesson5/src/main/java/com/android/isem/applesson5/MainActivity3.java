package com.android.isem.applesson5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Student[] arr = new Student[]{
                new Student("ArrayAdapter1", "BaseAdapter1", 19, R.drawable.student1),
                new Student("ArrayAdapter2", "BaseAdapter2", 20, R.drawable.student2),
                new Student("ArrayAdapter3", "BaseAdapter3", 21, R.drawable.student3),
                new Student("ArrayAdapter4", "BaseAdapter4", 22, R.drawable.student1),
                new Student("ArrayAdapter5", "BaseAdapter5", 23, R.drawable.student2)
        };

        ArrayAdapter<Student> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                arr
        );

        ListView listView = (ListView) findViewById(R.id.listView3);
        if (listView != null) {
            listView.setAdapter(adapter);
        }
    }
}
