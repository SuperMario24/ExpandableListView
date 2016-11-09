package com.example.expandablelistadapterdemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;
import android.widget.ExpandableListView.OnChildClickListener;

public class MainActivity extends Activity {

	private MyExpandableListAdapter adapter;
	private List<String> groupTitle;
	private Map<Integer, List<String>> child;
	private ExpandableListView lv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		lv = (ExpandableListView) findViewById(R.id.lv);
		
		getData();
		showData();
		setListeners();
	}
	private void setListeners() {
		lv.setOnChildClickListener(new OnChildClickListener() {
			
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				groupPosition++;
				childPosition++;
				Toast.makeText(MainActivity.this, groupPosition+"."+childPosition+" 被点击了", Toast.LENGTH_SHORT).show();
				
				return true;
			}
		});
		
	}
	/**
	 * 显示数据
	 */
	private void showData() {
		adapter = new MyExpandableListAdapter(MainActivity.this, groupTitle, child);
		lv.setAdapter(adapter);
	}
	/**
	 * 获取数据
	 */
	private void getData() {
		groupTitle = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			groupTitle.add(i+1+"个group");
		}
		
		List<String> childItems = new ArrayList<String>();
		for (int i = 0; i < 4; i++) {
			childItems.add(i+1+"个child");
		}
		child = new HashMap<Integer, List<String>>();
		for (int i = 0; i < 10; i++) {
			child.put(i, childItems);
		}
		
	}

}
