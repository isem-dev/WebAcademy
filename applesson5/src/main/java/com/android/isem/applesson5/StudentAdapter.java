package com.android.isem.applesson5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class StudentAdapter extends ArrayAdapter<Student> {

    private Context mContext;
    private int mResource;
    private ArrayList<Student> mObjects;

    public StudentAdapter(Context context, int resource, ArrayList<Student> objects) {
        super(context, resource, objects);

        mContext = context;
        mResource = resource;
        mObjects = objects;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, null);

        final Student student = mObjects.get(position);

        ((ImageView)convertView.findViewById(R.id.imageViewPhoto)).setImageResource(student.getPhotoID());
        ((TextView)convertView.findViewById(R.id.textViewFirstName)).setText(student.getFirstName());
        ((TextView)convertView.findViewById(R.id.textViewLastName)).setText(student.getLastName());
//        ((TextView)convertView.findViewById(R.id.textViewAge)).setText(String.valueOf(student.age));

        TextView textView = (TextView)convertView.findViewById(R.id.textViewAge);
        textView.setText(String.valueOf(student.getAge()));

//        if (position == 2) {
//            textView.setTextColor(Color.RED);
            textView.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
//            textView.setVisibility(View.GONE);
//        }

        Button editButton = (Button) convertView.findViewById(R.id.editButton);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Check that someone called the mListener
                if (mListener != null) {
                    mListener.onEditClick(student, position);
                }
            }
        });

        Button deleteButton = (Button) convertView.findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onDeleteClick(student, position);
                }
            }
        });

        return convertView;
    }

    //Create variable type of interface
    private OnStudentClickListener mListener;

    //Create method which initialize variable type of interface
    public void setOnStudentClickListener(OnStudentClickListener listener) {
        mListener = listener;
    }

    //Create interface for adapter
    public interface OnStudentClickListener {
        void onEditClick(Student student, int position);
        void onDeleteClick(Student student, int position);
    }

}
