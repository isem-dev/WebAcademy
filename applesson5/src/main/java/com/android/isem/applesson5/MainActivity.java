package com.android.isem.applesson5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<HashMap<String, String>> arr = new ArrayList<>();
        HashMap<String, String> map;

        map = new HashMap<>();
        map.put("Name", "SimpleAdapter1");
        map.put("Age", "23");
        arr.add(map);

        map = new HashMap<>();
        map.put("Name", "SimpleAdapter2");
        map.put("Age", "23");
        arr.add(map);

        map = new HashMap<>();
        map.put("Name", "SimpleAdapter3");
        map.put("Age", "23");
        arr.add(map);

        SimpleAdapter adapter = new SimpleAdapter(
                this, // Context
                arr, // Data array
                android.R.layout.simple_list_item_2, // Default Android layout
                new String[] {"Name", "Age"}, // Keys (from)
                new int[] {android.R.id.text1, android.R.id.text2} // Default Android TextViews (to)
        );

        ListView listView = (ListView) findViewById(R.id.listView);
        if (listView != null) {
            listView.setAdapter(adapter);
        }

    }
}
