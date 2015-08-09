package com.example.classifymenu.adapter;

import java.util.List;
import java.util.Map;

import com.example.classifymenu.R;
import com.example.classifymenu.customview.MyGridView;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

/**
 * @author anumbrella
 * 
 * @date 2015-8-9 下午8:14:57
 * 
 *       自定义的扩展适配器 (ExpandableGridView适配器)
 * 
 */
public class ExpandableGridAdapter extends BaseExpandableListAdapter {

	/**
	 * 二级菜单选项的显示内容
	 */
	private String[][] text_array;

	/**
	 * 一级菜单选项的显示内容
	 */
	private List<Map<String, Object>> list;

	/**
	 * 自定义的视图
	 */
	private MyGridView myGridView;

	private Context context;

	public ExpandableGridAdapter(Context context,
			List<Map<String, Object>> list, String[][] array) {
		this.context = context;
		this.list = list;
		this.text_array = array;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.ExpandableListAdapter#getGroupCount()
	 * 
	 * 获取第一级菜单的选的总数目
	 */
	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.ExpandableListAdapter#getChildrenCount(int)
	 * 
	 * 获取一级菜单下面二级菜单的选项的总数目
	 */
	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		// 这里返回1是为了让ExpandableListView只显示一个ChildView，否则在展开
		// ExpandableListView时会显示和ChildCount数量相同的GridView
		return 1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.ExpandableListAdapter#getGroup(int)
	 * 
	 * 获取一级菜单的具体的选项的内容
	 */
	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return list.get(groupPosition).get("txt");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.ExpandableListAdapter#getChild(int, int)
	 * 
	 * 
	 * 获取一级菜单下第二级菜单的具体的选项的内容
	 */
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub

		return text_array[groupPosition][childPosition];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.ExpandableListAdapter#getGroupId(int)
	 * 
	 * 获取第一级菜单的选项的id
	 */
	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.ExpandableListAdapter#getChildId(int, int)
	 * 
	 * 获取二级菜单的选项的id
	 */
	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childPosition;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.ExpandableListAdapter#hasStableIds()
	 * 
	 * 指定位置相应的组视图(指定视图相应的id)
	 */
	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.ExpandableListAdapter#getGroupView(int, boolean,
	 * android.view.View, android.view.ViewGroup)
	 * 
	 * 对一级菜单的标签的内容进行设置
	 */
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		convertView.inflate(context, R.layout.item_expandablegrid_layout, null);
		TextView textView = (TextView) convertView
				.findViewById(R.id.item_expandablegrid_text);

		if (isExpanded) {
			textView.setCompoundDrawablesWithIntrinsicBounds(0, 0,
					R.drawable.group_down, 0);
		} else {
			textView.setCompoundDrawablesWithIntrinsicBounds(0, 0,
					R.drawable.group_up, 0);
		}

		textView.setText(list.get(groupPosition).get("txt").toString());
		return convertView;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return false;
	}

}
