package com.android.isem.applesson9;

import android.app.Activity;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MyIntentService extends IntentService {

    private static final String LOG_TAG = MyIntentService.class.getSimpleName();

    public static final String INSERT_STUDENT = "com.android.isem.applesson9.action.INSERT_STUDENT";

    public static final String EXTRA_PENDING_INTENT = "com.android.isem.applesson9.PENDING_INTENT";
    public static final String EXTRA_STUDENT = "com.android.isem.applesson9.extra.STUDENT";
    public static final String EXTRA_ID = "com.android.isem.applesson9.extra.ID";

    public static final int REQUEST_CODE_INSERT_STUDENT = 2;

    public MyIntentService() {
        super("MyIntentService");
    }

    public static void insertStudent(Context context, Student student) {
        Intent intent = new Intent(context, MyIntentService.class);

        intent.setAction(INSERT_STUDENT);

//        intent.putExtra(EXTRA_STUDENT, student);

        PendingIntent pendingIntent = ((AppCompatActivity) context)
                .createPendingResult(REQUEST_CODE_INSERT_STUDENT, intent, 0);
//                .createPendingResult(REQUEST_CODE_INSERT_STUDENT, new Intent(context, MainActivity.class), 0);
        intent.putExtra(EXTRA_PENDING_INTENT, pendingIntent);

        intent.putExtra(EXTRA_STUDENT, student);

        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Intent resIntent = new Intent();

        if (intent != null) {
            final String action = intent.getAction();

            if (INSERT_STUDENT.equals(action)) {
                Student student = intent.getParcelableExtra(EXTRA_STUDENT);

                DataBaseHelper helper = new DataBaseHelper(getApplicationContext());
                long id = helper.insertStudent(student);

                dbChangeChecker();

                resIntent.putExtra(EXTRA_ID, id);
            }

            PendingIntent pendingIntent = intent.getParcelableExtra(EXTRA_PENDING_INTENT);

            try {
                pendingIntent.send(this, Activity.RESULT_OK, resIntent);
            } catch (PendingIntent.CanceledException e) {
                Log.e(LOG_TAG, e.getMessage());
            }
        }
    }

    private void dbChangeChecker() {
        Cursor cursor = null;
        DataBaseHelper dbHelper = new DataBaseHelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try {
            cursor = db.query(Student.TABLE_NAME, null, null, null, null, null, null);

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