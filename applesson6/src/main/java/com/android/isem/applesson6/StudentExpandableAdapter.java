package com.android.isem.applesson6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class StudentExpandableAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private ArrayList<Group> mGroups;
    private int mGroupResourceId;
    private int mChildResourceId;

    public StudentExpandableAdapter(Context mContext,
                                    ArrayList<Group> mGroups,
                                    int mGroupResourceId,
                                    int mChildResourceId) {
        this.mContext = mContext;
        this.mGroups = mGroups;
        this.mGroupResourceId = mGroupResourceId;
        this.mChildResourceId = mChildResourceId;
    }

    @Override
    public int getGroupCount() {
        return mGroups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mGroups.get(groupPosition).students.length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mGroups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mGroups.get(groupPosition).students[childPosition];
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mGroupResourceId, null);

        Group group = (Group) getGroup(groupPosition);

        ((TextView) convertView.findViewById(R.id.textViewNumber)).setText(group.number);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mChildResourceId, null);

        Student student = (Student) getChild(groupPosition, childPosition);

        ((TextView) convertView.findViewById(R.id.textViewFirstName)).setText(student.firstName);
        ((TextView) convertView.findViewById(R.id.textViewLastName)).setText(student.lastName);
        ((TextView) convertView.findViewById(R.id.textViewAge)).setText(String.valueOf(student.age));

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }



    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
