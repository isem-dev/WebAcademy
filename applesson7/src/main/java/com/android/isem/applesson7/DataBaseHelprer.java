package com.android.isem.applesson7;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelprer extends SQLiteOpenHelper{

    public DataBaseHelprer(Context context) {
        super(context, "MyDB111.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Student.TABLE_NAME + " ("
                        + Student.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + Student.COLUMN_FIRST_NAME + " TEXT NOT NULL,"
                        + Student.COLUMN_LAST_NAME + " TEXT NOT NULL,"
                        + Student.COLUMN_AGE + " INTEGER NOT NULL);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertStudent(Student student) {
        return 0;
    }
}
