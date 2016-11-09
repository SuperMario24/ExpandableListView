package com.example.expandablelistadapterdemo;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class MyExpandableListAdapter extends BaseExpandableListAdapter{

	private Context context;
	private List<String> groups;
	private Map<Integer, List<String>> childs;
	private LayoutInflater inflater;
	
	
	public MyExpandableListAdapter(Context context, List<String> groups,
			Map<Integer, List<String>> childs) {
		super();
		this.context = context;
		this.groups = groups;
		this.childs = childs;
		this.inflater = LayoutInflater.from(context);
	}

	
	@Override
	public int getGroupCount() {
		return groups.size();
	}

	/**
	 * 根据childs map的key获取value是一个list，list.size()获取数量
	 */
	@Override
	public int getChildrenCount(int groupPosition) {
		return childs.get(groupPosition).size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return groups.get(groupPosition);
	}

	/**
	 * 获取child的value
	 */
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return childs.get(groupPosition).get(childPosition);
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		GroupHolder groupHolder = null;
		if(convertView == null){
			groupHolder = new GroupHolder();
			convertView = inflater.inflate(R.layout.lv_group_item, null);
			groupHolder.tvGroupTitle = (TextView) convertView.findViewById(R.id.tv_group_item);
			convertView.setTag(groupHolder);
		}
		groupHolder = (GroupHolder) convertView.getTag();
		groupHolder.tvGroupTitle.setText(groups.get(groupPosition));
		return convertView;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		String string = childs.get(groupPosition).get(childPosition);
		ChildHolder childHolder = null;
		if(convertView == null){
			childHolder = new ChildHolder();
			convertView = inflater.inflate(R.layout.lv_child_item, null);
			childHolder.tvChildTitle = (TextView) convertView.findViewById(R.id.tv_child_item);
			convertView.setTag(childHolder);
		}
		childHolder = (ChildHolder) convertView.getTag();
		childHolder.tvChildTitle.setText(string);
		return convertView;
	}

	/**
	 * 返回true，不然不能点击child的 item
	 */
	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return true;
	}
	
	class GroupHolder{
		TextView tvGroupTitle;
	}
	class ChildHolder{
		TextView tvChildTitle;
	}
	

}
