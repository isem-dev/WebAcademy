package com.android.isem.applesson9;

import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private ProgressDialog progressDialog;
    private SaveTask saveTask;

    public static final String PENDING_INTENT_KEY = "PendingIntent";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickMethod(View view) {
        switch (view.getId()) {
            case R.id.button1:

                progressDialog = new ProgressDialog(this);
                progressDialog.setMessage("Wait...");
                progressDialog.setCancelable(false);
                progressDialog.show();

                Intent intent = new Intent(this, MyService.class);

                PendingIntent pendingIntent = createPendingResult(1, intent, 0);
                intent.putExtra(PENDING_INTENT_KEY, pendingIntent);

                startService(intent);
                break;
            case R.id.button2:
                MyIntentService.insertStudent(
                        this,
                        new Student("Kostia", "Semenov", 1, R.drawable.student1));

                break;
            case R.id.button3:
                saveTask = new SaveTask();
                saveTask.execute(
                        new Student("AsyncTask", "Thread", 20, R.drawable.student0),
                        new Student("AsyncTask", "Thread", 21, R.drawable.student0),
                        new Student("AsyncTask", "Thread", 22, R.drawable.student0),
                        new Student("AsyncTask", "Thread", 23, R.drawable.student0),
                        new Student("AsyncTask", "Thread", 24, R.drawable.student0),
                        new Student("AsyncTask", "Thread", 25, R.drawable.student0),
                        new Student("AsyncTask", "Thread", 26, R.drawable.student0),
                        new Student("AsyncTask", "Thread", 27, R.drawable.student0),
                        new Student("AsyncTask", "Thread", 28, R.drawable.student0),
                        new Student("AsyncTask", "Thread", 29, R.drawable.student0)
                );

                break;
            case R.id.button4:
                break;
            case R.id.button5:
                break;
            case R.id.button6:
                break;
            case R.id.button7:
                break;
            case R.id.button8:
                break;
            case R.id.button9:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (saveTask != null) {
            saveTask.cancel(true);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //MyService.java
        if (requestCode == 1 && resultCode == RESULT_OK) {

            try {
                TimeUnit.SECONDS.sleep(3); //Emulation 3 s waiting for ProgressDialog
            } catch (InterruptedException e) {
                Log.e(LOG_TAG, e.getMessage());
            }

            progressDialog.dismiss();

            Toast.makeText(this, data.getStringExtra("ResultKey"), Toast.LENGTH_SHORT).show();
        }

        //MyIntentService.java
        if (requestCode == MyIntentService.REQUEST_CODE_INSERT_STUDENT && resultCode == RESULT_OK) {
            long id = data.getLongExtra(MyIntentService.EXTRA_ID, 0);

            Toast.makeText(this, String.valueOf(id), Toast.LENGTH_SHORT).show();
        }

        super.onActivityResult(requestCode, resultCode, data);
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
