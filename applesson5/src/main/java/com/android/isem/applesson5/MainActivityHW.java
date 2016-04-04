package com.android.isem.applesson5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivityHW extends AppCompatActivity {

    public static final String EXTRA_STUDENT = "com.android.isem.applesson5.STUDENT";
    public static final int REQUEST_CODE_EDIT = 5;

    private ArrayList<Student> mStudents;
    private StudentAdapter mAdapter;
    private int mPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_hw);

        mStudents = new ArrayList<>();

        mStudents.add(new Student("FirstName0", "LastName0", 20, R.drawable.student1));
        mStudents.add(new Student("FirstName1", "LastName1", 21, R.drawable.student2));
        mStudents.add(new Student("FirstName2", "LastName2", 22, R.drawable.student3));
        mStudents.add(new Student("FirstName3", "LastName3", 23, R.drawable.student1));
        mStudents.add(new Student("FirstName4", "LastName4", 24, R.drawable.student2));
        mStudents.add(new Student("FirstName5", "LastName5", 25, R.drawable.student3));
        mStudents.add(new Student("FirstName6", "LastName6", 26, R.drawable.student1));
        mStudents.add(new Student("FirstName7", "LastName7", 27, R.drawable.student2));

        mAdapter = new StudentAdapter(
                this,
                R.layout.item_content,
                mStudents
        );

        ListView listView = (ListView) findViewById(R.id.listViewHW5);
        if (listView != null) {
            listView.setAdapter(mAdapter);
        }

        mAdapter.setOnStudentClickListener(new StudentAdapter.OnStudentClickListener() {
            @Override
            public void onEditClick(Student student, int position) {
//                Toast.makeText(MainActivityHW.this, "Edit clicked!", Toast.LENGTH_SHORT).show();
                mPosition = position;

                Intent intentEdit = new Intent(MainActivityHW.this, ActivityEdit.class);

                intentEdit.putExtra(EXTRA_STUDENT, student);
                startActivityForResult(intentEdit, REQUEST_CODE_EDIT);
            }

            @Override
            public void onDeleteClick(Student student, int position) {
//                Toast.makeText(MainActivityHW.this, "Delete clicked!", Toast.LENGTH_SHORT).show();
                mStudents.remove(position);
                mAdapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.new_student) {
            Intent intentCreate = new Intent(MainActivityHW.this, ActivityEdit.class);

            mStudents.add(new Student("", "", 0, R.drawable.student0));

            mPosition = mStudents.size() - 1;

            intentCreate.putExtra(EXTRA_STUDENT, mStudents.get(mPosition));
            startActivityForResult(intentCreate, REQUEST_CODE_EDIT);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_EDIT && resultCode == RESULT_OK) {
            Student student = data.getParcelableExtra(EXTRA_STUDENT);

            mStudents.get(mPosition).setFirstName(student.getFirstName());
            mStudents.get(mPosition).setLastName(student.getLastName());
            mStudents.get(mPosition).setAge(student.getAge());

            mAdapter.notifyDataSetChanged();
        }
    }
}
