package com.android.isem.applesson5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        final String[] arr = new String[]{"Item 0", "Item 1", "Item 2", "Item 3", "Item 4"};

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_multiple_choice,
                android.R.id.text1,
                arr
        );

        final ListView listView = (ListView) findViewById(R.id.listView5);
        if (listView != null) {
            listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
            listView.setAdapter(adapter);
        }

        Button button = (Button) findViewById(R.id.button5);
        if (button != null && listView != null) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    int position = listView.getCheckedItemPosition();
                    SparseBooleanArray itemPositions = listView.getCheckedItemPositions();

                    String position = "";
                    for (int i = 0; i < itemPositions.size(); i++) {
                        if (itemPositions.get(i)) {
                            //item selected
                            position += String.valueOf(i);
                        }
                    }

                    Toast.makeText(MainActivity5.this, String.valueOf(position), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
