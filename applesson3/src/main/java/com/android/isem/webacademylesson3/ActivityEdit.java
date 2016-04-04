package com.android.isem.webacademylesson3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class ActivityEdit extends AppCompatActivity {

    private Student mStudent;
    private EditText textViewFirstName;
    private EditText textViewLastName;
    private EditText textViewAge;
    private ImageView imageViewPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Intent intent = getIntent();
        if (intent != null) {
            mStudent = intent.getParcelableExtra(MainActivity.EXTRA_STUDENT);
        }

        imageViewPhoto = (ImageView) findViewById(R.id.imageViewEdit);
        if (imageViewPhoto != null) {
            imageViewPhoto.setImageResource(mStudent.getPhotoID());
        }

        registerForContextMenu(imageViewPhoto);

        textViewFirstName = (EditText) findViewById(R.id.editTextFirstName);
        if (textViewFirstName != null) {
            textViewFirstName.setText(mStudent.getFirstName());
        }

        textViewLastName = (EditText) findViewById(R.id.editTextLastName);
        if (textViewLastName != null) {
            textViewLastName.setText(mStudent.getLastName());
        }

        textViewAge = (EditText) findViewById(R.id.editTextAge);
        if (textViewAge != null) {
            textViewAge.setText(String.valueOf(mStudent.getAge()));
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu_image, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.editPhotoContext) {
            Toast.makeText(this, "Photo editing will done via Uri", Toast.LENGTH_SHORT).show();
        }

        return super.onContextItemSelected(item);
    }

    public void onClickSave(View view) {
        Intent intent = new Intent();

        mStudent.setFirstName(textViewFirstName.getText().toString());
        mStudent.setLastName(textViewLastName.getText().toString());
        mStudent.setAge(Integer.parseInt(textViewAge.getText().toString()));

        intent.putExtra(MainActivity.EXTRA_STUDENT, mStudent);
        setResult(RESULT_OK, intent);

        finish();
    }
}
