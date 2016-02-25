package com.tabjin.feng.medicalrecord.util;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.tabjin.feng.medicalrecord.bean.Record;

import java.util.List;

/**
 * Created by Administrator on 2016/2/24.
 */
public class MyExpendableListAdapter extends BaseExpandableListAdapter {

    private List<Record> parent;
    private List<Record> child;
    private  Context context;

    public MyExpendableListAdapter(Context context,List<Record> parent ,List<Record> child) {

        this.parent = parent;
        this.child = child;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return parent.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
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

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
