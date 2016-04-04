package com.android.isem.applesson6;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Student[] arr = new Student[]{
                new Student("Studen 0", "Studik 0", 20),
                new Student("Studen 1", "Studik 1", 21),
                new Student("Studen 2", "Studik 2", 22),
                new Student("Studen 3", "Studik 3", 23),
                new Student("Studen 4", "Studik 4", 24),
        };

        StudentRecyclerAdapter adapter = new StudentRecyclerAdapter(
                this,
                arr,
                R.layout.activity_main3
        );

        RecyclerView view = (RecyclerView) findViewById(R.id.recyclerView3);
        view.getLayoutManager();
        view.setAdapter(adapter);
    }
}
