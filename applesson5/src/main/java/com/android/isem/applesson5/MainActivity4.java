package com.android.isem.applesson5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity4 extends AppCompatActivity {

    ArrayList<Student> mStudentsArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        mStudentsArray.add(new Student("FirstName0", "LastName0", 20, R.drawable.student1));
        mStudentsArray.add(new Student("FirstName1", "LastName1", 21, R.drawable.student2));
        mStudentsArray.add(new Student("FirstName2", "LastName2", 22, R.drawable.student3));

        StudentAdapter adapter = new StudentAdapter(
                this,
                R.layout.item_content,
                mStudentsArray
        );

        ListView listView = (ListView) findViewById(R.id.listView4);
        if (listView != null) {
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Student student = mStudentsArray.get(position);
                    Toast.makeText(MainActivity4.this, "Click to: " + student.toString(), Toast.LENGTH_SHORT).show();
                }
            });

            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    Student student = mStudentsArray.get(position);
                    Toast.makeText(MainActivity4.this, "Long click to: " + student.toString(), Toast.LENGTH_LONG).show();

                    return true;
                }
            });

        }

    }
}
