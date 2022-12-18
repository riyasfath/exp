package com.example.expandablelistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

public class ExListViewAdapter extends BaseExpandableListAdapter {
    Context context;
    List<String> langs;
    Map<String, List<String>> topics;

    public ExListViewAdapter(Context context, List<String> langs, Map<String, List<String>> topics) {
        this.context = context;
        this.langs = langs;
        this.topics = topics;
    }

    @Override
    public int getGroupCount() {
        return langs.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return topics.get(langs.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return langs.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return topics.get(langs.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean b, View view, ViewGroup viewGroup) {
        String lang = (String) getGroup(groupPosition);
        if(view == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.list_parent, null);
        }
        TextView pText = (TextView) view.findViewById(R.id.parent);
        pText.setText(lang);
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean b, View view, ViewGroup viewGroup) {
        String topic = (String) getChild(groupPosition,childPosition);
        if(view == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.list_child, null);
        }
        TextView cText = (TextView) view.findViewById(R.id.childText);
        cText.setText(topic);
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
