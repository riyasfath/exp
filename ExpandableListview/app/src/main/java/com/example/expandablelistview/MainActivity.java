package com.example.expandablelistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ExpandableListView expandableListView;
    List<String> lang;
    Map<String, List<String>> topic;
    ExpandableListAdapter expandableListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expandableListView = (ExpandableListView) findViewById(R.id.ExList);

        fillData();
        expandableListAdapter = new ExListViewAdapter(this, lang, topic);
        expandableListView.setAdapter(expandableListAdapter);

     
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(), lang.get(groupPosition) + " group expanded", Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(MainActivity.this, lang.get(groupPosition) + " group collapsed", Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long l) {
                Toast.makeText(MainActivity.this, lang.get(groupPosition) + "'s child node " +
                        topic.get(lang.get(groupPosition)).get(childPosition) + " have clicked",
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    public void fillData(){

        lang = new ArrayList<>();
        topic = new HashMap<String, List<String>>();
        lang.add("Java");
        lang.add("C");

        List<String> java = new ArrayList<>();
        List<String> c = new ArrayList<>();

        java.add("Encapsulation");
        java.add("Polymorphism");
        java.add("Inheritance");

        c.add("Procedure");
        c.add("Pointer");
        c.add("Array");

        topic.put(lang.get(0),java);
        topic.put(lang.get(1),c);
    }


}