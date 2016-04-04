package com.android.isem.applesson6;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        String[] arr = new String[] {"q", "w", "e", "r", "t", "y", "u", "i", "o"};
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(
//                this,
//                android.R.layout.simple_list_item_1,
//                android.R.id.text1,
//                arr
//        );
//
//        GridView gridView = (GridView) findViewById(R.id.gridView);
//        gridView.setAdapter(adapter);

        ArrayList<HashMap<String, String>> groups = new ArrayList<>();
        HashMap<String, String> group;

        group = new HashMap<>();
        group.put("Number", "Group 0");
        groups.add(group);

        group = new HashMap<>();
        group.put("Number", "Group 1");
        groups.add(group);

        group = new HashMap<>();
        group.put("Number", "Group 2");
        groups.add(group);

        ArrayList<ArrayList<HashMap<String, String>>> allChilds = new ArrayList<>();
        ArrayList<HashMap<String, String>> childs;
        HashMap<String, String> child;

        childs = new ArrayList<>(); //Group 0
        child = new HashMap<>();
        child.put("Name", "Ivan 0");
        child.put("Age", "20");
        childs.add(child);

        allChilds.add(childs);

        childs = new ArrayList<>(); //Group 1

        child = new HashMap<>();
        child.put("Name", "Ivan 1");
        child.put("Age", "21");
        childs.add(child);

        child = new HashMap<>();
        child.put("Name", "Ivan 2");
        child.put("Age", "22");
        childs.add(child);

        allChilds.add(childs);

        childs = new ArrayList<>(); //Group 3

        allChilds.add(childs);

        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(
                this,
                groups,
                android.R.layout.simple_list_item_1,
                new String[] {"Number"},
                new int[]{android.R.id.text1},
                allChilds,
                android.R.layout.simple_expandable_list_item_2,
                new String[]{"Name", "Age"},
                new int[]{android.R.id.text1, android.R.id.text2}
        );

        ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        if (expandableListView != null) {
            expandableListView.setAdapter(adapter);
        }
    }
}
