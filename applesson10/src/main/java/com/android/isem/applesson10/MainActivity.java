package com.android.isem.applesson10;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<List<Student>> {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private ProgressDialog mProgressDialog;
    private SaveTask mSaveTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickMethod(View view) {
        switch (view.getId()) {
            case R.id.button1:
                mSaveTask = new SaveTask();
                mSaveTask.execute(
                        new Student("Joon", "Lee", 22, R.drawable.student0),
                        new Student("Kate", "Nilson", 21, R.drawable.student0),
                        new Student("Steve", "Jobs", 22, R.drawable.student0),
                        new Student("Andrew", "Garfield", 23, R.drawable.student0),
                        new Student("Bob", "Dylan", 24, R.drawable.student0),
                        new Student("Robbie ", "Williams", 23, R.drawable.student0),
                        new Student("John", "Bongiovi", 24, R.drawable.student0),
                        new Student("Robert", "De Niro", 22, R.drawable.student0),
                        new Student("Leonardo", "Dicaprio", 23, R.drawable.student0),
                        new Student("Robert ", "Pattinson", 24, R.drawable.student0)
                );

                break;
            case R.id.button2:
                getSupportLoaderManager().restartLoader(0, null, this);
                break;
            case R.id.button3:
                break;
            case R.id.button4:
                break;
        }
    }

    @Override
    public Loader<List<Student>> onCreateLoader(int i, Bundle bundle) {
        return new StudentsLoader(MainActivity.this);
    }

    @Override
    public void onLoadFinished(Loader<List<Student>> loader, List<Student> students) {
        String names = "";

        for (Student s : students) {
            names += String.format(" %s", s.getFirstName());
        }

        Toast.makeText(this, names, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoaderReset(Loader<List<Student>> loader) {

    }

    class SaveTask extends AsyncTask<Student, Integer, String> {

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Wait");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(Student... params) {
            DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);

            for (int i = 0; i < params.length; i++) {
                if (!isCancelled()) {
                    dataBaseHelper.insertStudent(params[i]);

                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        Log.e(LOG_TAG, e.getMessage());
                    }

                    publishProgress(i + 1);
                }
            }

            return "Finished";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressDialog.setMessage(String.valueOf(values[0]));
        }

        @Override
        protected void onPostExecute(String message) {
            if (progressDialog != null) {
                progressDialog.dismiss();
            }

            Toast.makeText(MainActivity.this, String.valueOf(message), Toast.LENGTH_SHORT).show();
//            ((Button) findViewById(R.id.button3)).setText("Rotate");
        }
    }
}