package com.android.isem.applesson10;


import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

public class StudentsLoader extends AsyncTaskLoader<List<Student>> {

    private Context mContext;
    private List<Student> mStudents;

    public StudentsLoader(Context context) {
        super(context);

        this.mContext = context;
    }

    @Override
    public List<Student> loadInBackground() {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(mContext);

        mStudents = dataBaseHelper.getStudents();

        return mStudents;
    }

    @Override
    public void deliverResult(List<Student> data) {
        if (isReset()) {
            return;
        }

        mStudents = data;

        if (isStarted()) {
            super.deliverResult(data);
        }
    }

    @Override
    protected void onStartLoading() {
        if (mStudents != null) {
            deliverResult(mStudents);
        }

        if (takeContentChanged() || mStudents == null) {
            forceLoad();
        }
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }

    @Override
    protected void onReset() {
        onStopLoading();

        if (mStudents != null) {
            mStudents = null;
        }
    }
}
