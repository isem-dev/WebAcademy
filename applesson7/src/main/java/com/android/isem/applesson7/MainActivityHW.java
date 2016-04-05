package com.android.isem.applesson7;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivityHW extends AppCompatActivity {

    private final String LOG_TAG = MainActivityHW.class.getSimpleName();

    public static final String EXTRA_STUDENT = "com.android.isem.applesson7.STUDENT";
    public static final int REQUEST_CODE_NEW = 4;
    public static final int REQUEST_CODE_EDIT = 5;

    private DataBaseHelper mDataBaseHelper;
    private SQLiteDatabase mDatabase;

    private Cursor mStudentsCursor;
    private SimpleCursorAdapter mAdapter;

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_hw);

        mDataBaseHelper = new DataBaseHelper(this);
        mDatabase = mDataBaseHelper.getWritableDatabase();

        mListView = (ListView) findViewById(R.id.listViewHW7);
        registerForContextMenu(mListView);

        initSudentsListView();
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
            Student student = new Student("", "", 0, R.drawable.student0);

            intentCreate.putExtra(EXTRA_STUDENT, student);
            startActivityForResult(intentCreate, REQUEST_CODE_NEW);

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
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int listItemPosition = menuInfo.position;

        if (item.getItemId() == R.id.edit_student) {
            //TODO edit student in DB
            Intent intentEdit = new Intent(MainActivityHW.this, ActivityEditStudent.class);
            Cursor studentCursor = (Cursor) mListView.getItemAtPosition(listItemPosition);// .getItem(listItemPosition);
            Student student = new Student(
                    studentCursor.getString(studentCursor.getColumnIndex(Student.COLUMN_FIRST_NAME)),
                    studentCursor.getString(studentCursor.getColumnIndex(Student.COLUMN_LAST_NAME)),
                    studentCursor.getLong(studentCursor.getColumnIndex(Student.COLUMN_AGE)),
                    studentCursor.getLong(studentCursor.getColumnIndex(Student.COLUMN_PHOTO_ID))
            );

            intentEdit.putExtra(EXTRA_STUDENT, student);
            startActivityForResult(intentEdit, REQUEST_CODE_EDIT);

        } else if (item.getItemId() == R.id.delete_student) {
            //TODO delete student from DB
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_NEW) {
                Student student = data.getParcelableExtra(EXTRA_STUDENT);

                mDataBaseHelper.insertStudent(student);

                insertChecker();

                mAdapter.notifyDataSetChanged();

                initSudentsListView();
            } else if (requestCode == REQUEST_CODE_EDIT) {
                Student student = data.getParcelableExtra(EXTRA_STUDENT);

                mDataBaseHelper.editStudent(student);

                insertChecker();

                mAdapter.notifyDataSetChanged();

                initSudentsListView();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


    }

    private void initSudentsListView() {
        mStudentsCursor = mDatabase.query(Student.TABLE_NAME, null, null, null, null, null, null);

        mAdapter = new SimpleCursorAdapter(
                this,
                android.R.layout.simple_list_item_2,
                mStudentsCursor,
                new String[]{Student.COLUMN_FIRST_NAME, Student.COLUMN_LAST_NAME},
                new int[]{android.R.id.text1, android.R.id.text2}
        );

        if (mListView != null) {
            mListView.setAdapter(mAdapter);
        }
    }

    private void insertChecker() {
        Cursor cursor = null;
        try {
            cursor = mDatabase.query(Student.TABLE_NAME, null, null, null, null, null, null);

            if (cursor.moveToFirst()) { //if table has any record return true
                while(!cursor.isAfterLast()) {

                    Log.d("Students.db: ",
                            cursor.getString(cursor.getColumnIndex(Student.COLUMN_FIRST_NAME))
                                    + " " + cursor.getString(cursor.getColumnIndex(Student.COLUMN_LAST_NAME))
                                    + " " + cursor.getString(cursor.getColumnIndex(Student.COLUMN_AGE))
                                    + " " + cursor.getString(cursor.getColumnIndex(Student.COLUMN_PHOTO_ID))

                    );

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
    }
}
