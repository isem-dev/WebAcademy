package com.android.isem.applesson7;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class ActivityEditStudent extends AppCompatActivity {

    private Student mStudent;
    private EditText mEditTextFirstName;
    private EditText mEditTextLastName;
    private EditText mEditTextAge;
    private ImageView mImageViewPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Intent intent = getIntent();
        if (intent != null) {
            mStudent = intent.getParcelableExtra(MainActivityHW.EXTRA_STUDENT);
        }

        mImageViewPhoto = (ImageView) findViewById(R.id.imageViewEdit);
        if (mImageViewPhoto != null) {
            mImageViewPhoto.setImageResource((int) mStudent.getPhotoID());
        }

//        registerForContextMenu(mImageViewPhoto);

        mEditTextFirstName = (EditText) findViewById(R.id.editTextFirstName);
        if (mEditTextFirstName != null) {
            mEditTextFirstName.setText(mStudent.getFirstName());
        }

        mEditTextLastName = (EditText) findViewById(R.id.editTextLastName);
        if (mEditTextLastName != null) {
            mEditTextLastName.setText(mStudent.getLastName());
        }

        mEditTextAge = (EditText) findViewById(R.id.editTextAge);
        if (mEditTextAge != null) {
            mEditTextAge.setText(String.valueOf(mStudent.getAge()));
        }
    }

//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        getMenuInflater().inflate(R.menu.context_menu_image, menu);
//    }
//
//    @Override
//    public boolean onContextItemSelected(MenuItem item) {
//        if (item.getItemId() == R.id.editPhotoContext) {
//            Toast.makeText(this, "Photo editing will done via Uri", Toast.LENGTH_SHORT).show();
//        }
//
//        return super.onContextItemSelected(item);
//    }

    public void onClickSave(View view) {
        Intent intent = new Intent();

        mStudent.setFirstName(mEditTextFirstName.getText().toString());
        mStudent.setLastName(mEditTextLastName.getText().toString());
        mStudent.setAge(Integer.parseInt(mEditTextAge.getText().toString()));

        intent.putExtra(MainActivityHW.EXTRA_STUDENT, mStudent);
        setResult(RESULT_OK, intent);

        finish();
    }
}
