package com.android.isem.applesson6;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class StudentViewHolder extends RecyclerView.ViewHolder {
    public TextView firstName;
    public TextView lastName;
    public TextView age;


    public StudentViewHolder(View itemView) {
        super(itemView);

        firstName = (TextView) itemView.findViewById(R.id.textViewFirstName);
        lastName = (TextView) itemView.findViewById(R.id.textViewLastName);
        age = (TextView) itemView.findViewById(R.id.textViewAge);
    }
}
