package com.android.isem.applesson8;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickMethod(View v) {
        switch (v.getId()) {
            case R.id.button1:
                // MainActivity.xml will be created
                SharedPreferences activityPreferences = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor editor = activityPreferences.edit();

                editor.putString("Name", "Kostia");
                editor.commit();

                break;
            case R.id.button2:
                // MySharedPref.xml will be created
                SharedPreferences sharedPreferences =
                        getSharedPreferences("MySharedPref", MODE_PRIVATE);
                SharedPreferences.Editor editor2 = sharedPreferences.edit();

                editor2.putString("Name", "Denis");
                editor2.commit();

                break;
            case R.id.button3:
                SharedPreferences activityPreferences3 = getPreferences(MODE_PRIVATE);
                Toast.makeText(this,
                        activityPreferences3.getString("Name", "Default value"),
                        Toast.LENGTH_SHORT).show();

                break;
            case R.id.button4:
                startActivity(new Intent(this, SettingsActivity.class));

                break;
            case R.id.button5:
                SharedPreferences defaultSharedPreferences5 =
                        PreferenceManager.getDefaultSharedPreferences(this);
                Toast.makeText(this,
                        defaultSharedPreferences5.getString("pref3_key", "Default value"),
                        Toast.LENGTH_SHORT).show();

                break;
            case R.id.button6:
                saveInternalFile("MyFile.txt", "Text to file");

                break;
            case R.id.button7:
                Toast.makeText(this, readInternalFile("MyFile.txt"), Toast.LENGTH_SHORT).show();

                break;
            case R.id.button8:
                try {
                    if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                        File folder = Environment.getExternalStorageDirectory();

                        folder = new File(folder.getAbsolutePath() + "/MyFolder");

                        if (!folder.exists()) {
                            folder.mkdirs();
                        }

                        saveExternalFile(folder, "MyFile.txt", "External file text");
                    }

                } catch (Exception e) {
                    Log.e(LOG_TAG, e.getMessage());
                }

                break;
            case R.id.button9:
                try {
                    if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                        File folder = Environment.getExternalStorageDirectory();

                        folder = new File(folder.getAbsolutePath() + "/MyFolder");

                        Toast.makeText(this,
                                readExternalFile(folder, "MyFile.txt"),
                                Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    Log.e(LOG_TAG, e.getMessage());
                }

                break;
            case R.id.button10:
                Student student = new Student("Ivan", "Ivanov", 22);

                Gson gson = new GsonBuilder().setDateFormat("dd.MM.yyyy HH:mm:ss").create();
                String json = gson.toJson(student);

                saveInternalFile("Student.txt", json);
                break;
            case R.id.button11:
                String json2 = readInternalFile("Student.txt");

                Gson gson2 = new GsonBuilder().create();
                Student student2 = gson2.fromJson(json2, Student.class);

                Toast.makeText(this,
                        student2.getFirstName() + " " + student2.getLastName(),
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.button12:
                Serializer serializer = new Persister();
                Student student12 = new Student("Ivan12", "Ivanov12", 12);
                File result = new File(getFilesDir(), "student.xml");

                try {
                    serializer.write(student12, result);
                } catch (Exception e) {
                    Log.e(LOG_TAG, e.getMessage());
                }
        }
    }

    private void saveInternalFile(String fileName, String data) {
        try {
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(
                            openFileOutput(fileName, Context.MODE_PRIVATE)
                    )
            );

            writer.write(data);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            Log.e(LOG_TAG, e.getMessage());
        }
    }

    private String readInternalFile(String fileName) {
        try {
            StringBuilder builder = new StringBuilder();

            InputStream inputStream = openFileInput(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

            BufferedReader reader = new BufferedReader(inputStreamReader);

            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }

            reader.close();
            return builder.toString();

        } catch (Exception e) {
            Log.e(LOG_TAG, e.getMessage());
        }
        return null;
    }

    private void saveExternalFile(File folder, String fileName, String data) {
        File file = new File(folder, fileName);
        try {
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(file), "UTF8"
                    )
            );

            writer.write(data);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            Log.e(LOG_TAG, e.getMessage());
        }
    }

    private String readExternalFile(File folder, String fileName) {
        File file = new File(folder, fileName);
        try {
            StringBuilder builder = new StringBuilder();

            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }

            reader.close();
            return builder.toString();

        } catch (Exception e) {
            Log.e(LOG_TAG, e.getMessage());
        }
        return null;
    }
}
