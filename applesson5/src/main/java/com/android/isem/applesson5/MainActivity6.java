package com.android.isem.applesson5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity6 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

//        final String[] arr = new String[]{"Item 1", "Item 2", "Item 3"};
//
//        ArrayAdapter<String> arrayAdapterForSpinner = new ArrayAdapter<>(
//                this,
//                android.R.layout.simple_list_item_1,
//                arr
//        );

//        // Getting data from Resource
//        ArrayAdapter<CharSequence> arrayAdapterForSpinner = ArrayAdapter.createFromResource(
//                this,
//                R.array.dayOfWeek,
//                android.R.layout.simple_spinner_item
//        );

//        arrayAdapterForSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Custom layouts
        final String[] arr = new String[]{"Item 1", "Item 2", "Item 3", "Item 4", "Item 5"};

        ArrayAdapter<String> arrayAdapterForSpinner = new ArrayAdapter<>(
                this,
                R.layout.item_spinner,
                R.id.textViewSpinner,
                arr
        );

        arrayAdapterForSpinner.setDropDownViewResource(R.layout.item_spinner_selected);

        Spinner spinner = (Spinner) findViewById(R.id.spinner6);
        if (spinner != null) {
            spinner.setAdapter(arrayAdapterForSpinner);
        }


//        SpinnerAdapter spinnerAdapter = ArrayAdapter.createFromResource(
//                this,
//                R.array.dayOfWeek,
//                android.R.layout.simple_spinner_item
//        );
//        ((ArrayAdapter<String>) spinnerAdapter)
//                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        Spinner spinner = (Spinner) findViewById(R.id.spinner6);
//        if (spinner != null) {
//            spinner.setAdapter(spinnerAdapter);
//        }
    }
}
