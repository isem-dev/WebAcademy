package com.android.isem.applesson7;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickMethod(View view) {
        DataBaseHelper dataBaseHelprer = new DataBaseHelper(this);
        SQLiteDatabase database = dataBaseHelprer.getWritableDatabase();

        switch (view.getId()) {
            case R.id.button1:
                ContentValues contentValues = new ContentValues();
                contentValues.put("FirstName", "Ivan");
                contentValues.put("LastName", "Ivanov");
                contentValues.put("Age", "22");

                long id = database.insert("Students", null, contentValues);
                Toast.makeText(this, String.valueOf(id), Toast.LENGTH_SHORT).show();
                break;
            case R.id.button2:
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("FirstName", "Petro");

                int count = database.update("Students", contentValues2, "_id > 5", null);
                Toast.makeText(this, String.valueOf(count), Toast.LENGTH_SHORT).show();
                break;
            case R.id.button3:
                Cursor cursor = null;
                try {
                    cursor = database.query("Students", null, null, null, null, null, null);

                    if (cursor.moveToFirst()) { //if table has any record return true
                        while(!cursor.isAfterLast()) {
                            String firstName = cursor.getString(cursor.getColumnIndex("FirstName"));

                            Log.d(LOG_TAG, firstName);

                            cursor.moveToNext();
                        }
                    }
                } catch (Exception e) {
                    Log.e(LOG_TAG, e.getMessage(), e);
                } finally {
                    if (cursor != null) {
                        cursor.close();
                    }
                }
                break;
        }
    }
}
