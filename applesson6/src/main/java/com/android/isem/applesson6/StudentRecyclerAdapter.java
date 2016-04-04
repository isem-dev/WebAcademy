package com.android.isem.applesson6;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class StudentRecyclerAdapter extends RecyclerView.Adapter<StudentViewHolder> {
    private Context mContext;
    private Student[] mStudents;
    private int mRecourceId;

    public StudentRecyclerAdapter(Context mContext, Student[] mStudents, int mRecourceId) {
        this.mContext = mContext;
        this.mStudents = mStudents;
        this.mRecourceId = mRecourceId;
    }


    @Override
    public StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(mRecourceId, null);

        StudentViewHolder viewHolder = new StudentViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(StudentViewHolder holder, int position) {
        Student student = mStudents[position];

        holder.firstName.setText(student.firstName);
        holder.lastName.setText(student.lastName);
        holder.age.setText(student.age);

    }

    @Override
    public int getItemCount() {
        return mStudents.length;
    }
}
