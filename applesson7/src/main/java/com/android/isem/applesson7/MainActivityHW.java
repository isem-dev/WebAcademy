package com.android.isem.applesson7;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;

public class MainActivityHW extends AppCompatActivity {

    public static final String EXTRA_STUDENT = "com.android.isem.applesson7.STUDENT";
    public static final int REQUEST_CODE_EDIT = 5;

    private DataBaseHelper mDataBaseHelprer;
    private SQLiteDatabase mDatabase;
    private Cursor mStudentsCursor;
    private SimpleCursorAdapter mAdapter;

    private ArrayList<Student> mStudents; //Temp students list
    private int mPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_hw);

        mDataBaseHelprer = new DataBaseHelper(this);
        mDatabase = mDataBaseHelprer.getWritableDatabase();

        mStudentsCursor = mDatabase.query(Student.TABLE_NAME, null, null, null, null, null, null);

        mAdapter = new SimpleCursorAdapter(
                this,
                R.layout.activity_main_hw,
                mStudentsCursor,
                new String[]{Student.COLUMN_FIRST_NAME, Student.COLUMN_LAST_NAME, Student.COLUMN_AGE, Student.COLUMN_PHOTO_ID},
                new int[]{R.id.textViewFirstName, R.id.textViewLastName, R.id.textViewAge, R.id.imageViewPhoto}
        );

        ListView listView = (ListView) findViewById(R.id.listViewHW7);
        if (listView != null) {
            listView.setAdapter(mAdapter);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.new_student) {
            Intent intentCreate = new Intent(MainActivityHW.this, ActivityEditStudent.class);

            mStudents = new ArrayList<>();
            mStudents.add(new Student("", "", 0, R.drawable.student0));

            mPosition = mStudents.size() - 1;

            intentCreate.putExtra(EXTRA_STUDENT, mStudents.get(mPosition));
            startActivityForResult(intentCreate, REQUEST_CODE_EDIT);

        } else if (item.getItemId() == R.id.new_group) {
            //TODO new group creation in DB
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.edit_student) {
            //TODO edit student in DB
        } else if (item.getItemId() == R.id.delete_student) {
            //TODO delete student from DB
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_EDIT && resultCode == RESULT_OK) {
            Student student = data.getParcelableExtra(EXTRA_STUDENT);

            mDataBaseHelprer.insertStudent(student);

            mAdapter.notifyDataSetChanged();

            //TODO new cursor init
        }
    }
}