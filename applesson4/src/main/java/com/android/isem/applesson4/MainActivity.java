package com.android.isem.applesson4;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        registerForContextMenu(imageView); //long click on ImageView
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.main, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu1:
                Toast.makeText(this, "ContextMenu item 1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu2:
                Toast.makeText(this, "ContextMenu item 2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu3:
                Toast.makeText(this, "ContextMenu item 3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu4:
                Toast.makeText(this, "ContextMenu item 4", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, 0, R.string.app_name); //First method of menu filling (onPrepareOptionsMenu)

        getMenuInflater().inflate(R.menu.main, menu); //Second method of menu filling (menu/main.xml)

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu1:
                Toast.makeText(this, "Menu item 1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu2:
                Toast.makeText(this, "Menu item 2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu3:
                Toast.makeText(this, "Menu item 3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu4:
                Toast.makeText(this, "Menu item 4", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClickMethod(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        switch (view.getId()) {
            case R.id.button1:
                Toast toast = Toast.makeText(this, "I am Toast", Toast.LENGTH_SHORT);

                toast.setGravity(Gravity.CENTER|Gravity.RIGHT, 0, 0);
                toast.show();
                break;
            case R.id.button2:
                Toast toast2 = Toast.makeText(this, "I am Toast with Image", Toast.LENGTH_SHORT);
                LinearLayout layout = (LinearLayout) toast2.getView();
                ImageView imageView = new ImageView(this);

                imageView.setImageResource(R.mipmap.ic_launcher);
                layout.addView(imageView, 0);

                toast2.setGravity(Gravity.CENTER, 0, 0);
                toast2.show();
                break;
            case R.id.button3:
                LayoutInflater layoutInflater = getLayoutInflater();
                View view3 = layoutInflater.inflate(R.layout.toast, null);
                Toast toast3 = new Toast(this);

                TextView textView1 = (TextView) view3.findViewById(R.id.textView1);
                TextView textView2 = (TextView) view3.findViewById(R.id.textView2);

                textView1.setText("Caption");
                textView2.setText("Body");

                toast3.setDuration(Toast.LENGTH_SHORT);
                toast3.setView(view3);
                toast3.setGravity(Gravity.CENTER|Gravity.LEFT, 0, 0);
                toast3.show();
                break;
            case R.id.button4:
                Notification notification = new NotificationCompat.Builder(this)
                        .setAutoCancel(true)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setTicker("Notification!")
                        .setContentTitle("Notification title")
                        .setContentText("Notification text")
                        .setWhen(System.currentTimeMillis())
                        .setContentIntent(pendingIntent)
                        .build();

                notificationManager.notify(1, notification);
                break;
            case R.id.button5:
                RemoteViews remoteViews = new RemoteViews(this.getPackageName(), R.layout.toast);
                remoteViews.setTextViewText(R.id.textView1, "Custom notification title");
                remoteViews.setTextViewText(R.id.textView2, "Custom notification text");

                Notification notification5 = new NotificationCompat.Builder(this)
                        .setAutoCancel(true)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setTicker("Notification!")
                        .setContentIntent(pendingIntent)
                        .build();

                notification5.contentView = remoteViews;
                notificationManager.notify(2, notification5);
                break;
            case R.id.button6:
                new TimePickerDialog(
                        this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                Toast.makeText(
                                        MainActivity.this,
                                        String.format("%s:%s", hourOfDay, minute),
                                        Toast.LENGTH_SHORT)
                                        .show();
                            }
                        },
                        0,
                        0,
                        true
                ).show();
                break;
            case R.id.button7:
                new DatePickerDialog(
                        this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                Toast.makeText(
                                        MainActivity.this,
                                        String.format("%s.%s.%s", dayOfMonth, monthOfYear, year),
                                        Toast.LENGTH_SHORT)
                                        .show();
                            }
                        },
                        0,
                        0,
                        0
                ).show();
                break;
            case R.id.button8:
                AlertDialog.Builder adb = new AlertDialog.Builder(this);
                adb.setTitle("Warning!");
                adb.setMessage("Are you sure?");
                adb.setIcon(android.R.drawable.ic_dialog_alert);
                adb.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this, "YES", Toast.LENGTH_SHORT).show();
                            }
                        }
                );
                adb.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this, "NO", Toast.LENGTH_SHORT).show();
                            }
                        }
                );
                adb.setNeutralButton("Don't know",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this, "Are you woman?", Toast.LENGTH_SHORT).show();
                            }
                        }
                );
                adb.create().show();
                break;
            case R.id.button9:
                ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setTitle("Title");
                progressDialog.setMessage("Message");
                progressDialog.setButton(Dialog.BUTTON_POSITIVE, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                progressDialog.show();

//                progressDialog.dismiss();
                break;
            case R.id.button10:
                PopupMenu popupMenu = new PopupMenu(this, view);
                popupMenu.inflate(R.menu.main);
                popupMenu.setOnMenuItemClickListener(
                        new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem menuItem) {
                                switch (menuItem.getItemId()) {
                                    case R.id.menu1:
                                        Toast.makeText(MainActivity.this, "PopupMenu item 1", Toast.LENGTH_SHORT).show();
                                        break;
                                    case R.id.menu2:
                                        Toast.makeText(MainActivity.this, "PopupMenu item 2", Toast.LENGTH_SHORT).show();
                                        break;
                                    case R.id.menu3:
                                        Toast.makeText(MainActivity.this, "PopupMenu item 3", Toast.LENGTH_SHORT).show();
                                        break;
                                    case R.id.menu4:
                                        Toast.makeText(MainActivity.this, "PopupMenu item 4", Toast.LENGTH_SHORT).show();
                                        break;
                                }
                                return true;
                            }
                        });
                popupMenu.show();
                break;
        }
    }
}
