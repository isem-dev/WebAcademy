package com.android.isem.webacademylesson3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_STUDENT = "com.android.isem.webacademylesson3.STUDENT";
    public static final int REQUEST_CODE_EDIT = 2;

    private Student mStudent;
    private TextView mTextViewFN;
    private TextView mTextViewLN;
    private TextView mTextViewA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStudent = new Student("Student", "Studentenko", 23, R.drawable.student1);

        Log.d("R.drawable.student1", String.valueOf(R.drawable.student1));

        mTextViewFN = (TextView) findViewById(R.id.firstNameMain);
        mTextViewFN.setText(mStudent.getFirstName());

        mTextViewLN = (TextView) findViewById(R.id.lastNameMain);
        mTextViewLN.setText(mStudent.getLastName());

        mTextViewA = (TextView) findViewById(R.id.ageMain);
        mTextViewA.setText(String.valueOf(mStudent.getAge()));

        registerForContextMenu(mTextViewFN);
        registerForContextMenu(mTextViewLN);
        registerForContextMenu(mTextViewA);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.viewItemContext:
                Intent intentView = new Intent(this, ActivityView.class);

                intentView.putExtra(EXTRA_STUDENT, mStudent);
                startActivity(intentView);
                break;
            case R.id.editItemContext:
                Intent intentEdit = new Intent(this, ActivityEdit.class);

                intentEdit.putExtra(EXTRA_STUDENT, mStudent);
                startActivityForResult(intentEdit, REQUEST_CODE_EDIT);
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about_item:
                Intent intent = new Intent(this, ActivityAbout.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClickMethod(View view) {
        switch (view.getId()) {
            case R.id.viewButton:
                Intent intentView = new Intent(this, ActivityView.class);

                intentView.putExtra(EXTRA_STUDENT, mStudent);
                startActivity(intentView);
                break;
            case R.id.editButton:
                Intent intentEdit = new Intent(this, ActivityEdit.class);

                intentEdit.putExtra(EXTRA_STUDENT, mStudent);
                startActivityForResult(intentEdit, REQUEST_CODE_EDIT);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_EDIT && resultCode == RESULT_OK) {
            mStudent = data.getParcelableExtra(EXTRA_STUDENT);

            mTextViewFN.setText(mStudent.getFirstName());
            mTextViewLN.setText(mStudent.getLastName());
            mTextViewA.setText(String.valueOf(mStudent.getAge()));
        }
    }
}
