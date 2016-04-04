package com.android.isem.applesson5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ArrayList<HashMap<String, String>> arr = new ArrayList<>();
        HashMap<String, String> map;

        map = new HashMap<>();
        map.put("FirstName", "Adapter1");
        map.put("LastName", "SimpleAdapter1");
        map.put("Age", "23");
        arr.add(map);

        map = new HashMap<>();
        map.put("FirstName", "Adapter2");
        map.put("LastName", "SimpleAdapter2");
        map.put("Age", "23");
        arr.add(map);

        map = new HashMap<>();
        map.put("FirstName", "Adapter3");
        map.put("LastName", "SimpleAdapter3");
        map.put("Age", "23");
        arr.add(map);

        SimpleAdapter adapter = new SimpleAdapter(
                this, // Context
                arr, // Data array
                R.layout.item_content, // Custom layout
                new String[] {"FirstName", "LastName", "Age"}, // Keys (from)
                new int[] {
                        // Custom TextViews (to)
                        R.id.textViewFirstName,
                        R.id.textViewLastName,
                        R.id.textViewAge
                }
        );

        ListView listView = (ListView) findViewById(R.id.listView2);
        if (listView != null) {
            listView.setAdapter(adapter);
        }
    }


}
