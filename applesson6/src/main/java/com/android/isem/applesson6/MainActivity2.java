package com.android.isem.applesson6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ArrayList<Group> groups = new ArrayList<>();

        groups.add(new Group(
                    "Group 0",
                    new Student[]{
                        new Student("Ivan 0", "Ivanov 0", 20)
                    })
        );

        groups.add(new Group(
                        "Group 1",
                        new Student[]{
                                new Student("Ivan 1", "Ivanov 1", 21),
                                new Student("Ivan 2", "Ivanov 2", 22)
                        })
        );

        groups.add(new Group(
                        "Group 2",
                        new Student[]{
                                new Student("Ivan 3", "Ivanov 3", 23)
                        })
        );

        StudentExpandableAdapter adapter = new StudentExpandableAdapter(
                this,
                groups,
                R.layout.group,
                R.layout.child
        );

        ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.expandableListView2);
        expandableListView.setAdapter(adapter);
    }
}
