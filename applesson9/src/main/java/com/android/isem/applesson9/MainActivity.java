package com.android.isem.applesson9;

import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    public static final String PENDING_INTENT_KEY = "PendingIntent";

    private ProgressDialog progressDialog;

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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //MyService.java
        if (requestCode == 1 && resultCode == RESULT_OK) {

            try {
                TimeUnit.SECONDS.sleep(3); //Emulation 3 s waiting for ProgressDialog
            } catch (InterruptedException e) {
                Log.e("log", e.getMessage());
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
}
