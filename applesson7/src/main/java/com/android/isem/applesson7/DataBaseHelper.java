package com.android.isem.applesson7;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper{

    private final String LOG_TAG = DataBaseHelper.class.getSimpleName();

    public DataBaseHelper(Context context) {
        //DB Creation
        super(context, "Students.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //DB Creation
        db.execSQL("CREATE TABLE " + Student.TABLE_NAME + " ("
                        + Student.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + Student.COLUMN_FIRST_NAME + " TEXT NOT NULL,"
                        + Student.COLUMN_LAST_NAME + " TEXT NOT NULL,"
                        + Student.COLUMN_AGE + " INTEGER NOT NULL,"
                        + Student.COLUMN_PHOTO_ID + " INTEGER NOT NULL);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //DB Update
        db.execSQL("ALTER TABLE " + Student.TABLE_NAME + " RENAME TO " + Student.TABLE_NAME + "_old;");

        db.execSQL("CREATE TABLE " + Student.TABLE_NAME + " ("
                        + Student.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + Student.COLUMN_FIRST_NAME + " TEXT NOT NULL,"
                        + Student.COLUMN_LAST_NAME + " TEXT NOT NULL,"
                        + Student.COLUMN_AGE + " INTEGER NOT NULL,"
                        + Student.COLUMN_PHOTO_ID + " INTEGER NOT NULL,"
                        + Student.COLUMN_BIRTHDAY + " TEXT NULL);"
        );

        db.execSQL("INSERT INTO " + Student.TABLE_NAME + "("
                        + Student.COLUMN_ID + ", "
                        + Student.COLUMN_FIRST_NAME + ", "
                        + Student.COLUMN_LAST_NAME + ", "
                        + Student.COLUMN_AGE + ") "
                        + "SELECT "
                        + Student.COLUMN_ID + ", "
                        + Student.COLUMN_FIRST_NAME + ", "
                        + Student.COLUMN_LAST_NAME + ", "
                        + Student.COLUMN_AGE
                        + " FROM "
                        + Student.TABLE_NAME + "_old;"
        );

        db.execSQL("DROP TABLE " + Student.TABLE_NAME + "_old;");
    }

    public long insertStudent(Student student) {
        long id = 0;
        SQLiteDatabase database = getWritableDatabase();

        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(Student.COLUMN_FIRST_NAME, student.getFirstName());
            contentValues.put(Student.COLUMN_LAST_NAME, student.getLastName());
            contentValues.put(Student.COLUMN_AGE, student.getAge());
            contentValues.put(Student.COLUMN_PHOTO_ID, student.getPhotoID());

            id = database.insert("Students", null, contentValues);

        } catch (Exception e) {
            Log.e(LOG_TAG, e.getMessage(), e);
        }

        return id;
    }
}
