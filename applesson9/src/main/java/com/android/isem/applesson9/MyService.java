package com.android.isem.applesson9;

import android.app.Activity;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    public static final String LOG_TAG = MyService.class.getSimpleName();

    public MyService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        PendingIntent pendingIntent = intent.getParcelableExtra(MainActivity.PENDING_INTENT_KEY);

        Intent resultIntent = new Intent();
        resultIntent.putExtra("ResultKey", "Result data");

        try {
            pendingIntent.send(this, Activity.RESULT_OK, resultIntent);
        } catch (PendingIntent.CanceledException e) {
            Log.e(LOG_TAG, e.getMessage());
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
