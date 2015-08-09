package com.example.classifymenu.adapter;

import java.util.List;
import java.util.Map;

import com.example.classifymenu.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * @author anumbrella
 * 
 * @date 2015-8-9 下午1:04:19
 * 
 *       扩展视图的适配器
 */
public class ExpandableListAdapter extends BaseExpandableListAdapter {

	private String[][] text_array;
	private Context context;
	private List<Map<String, Object>> list;

	public ExpandableListAdapter(Context context,
			List<Map<String, Object>> list, String[][] array) {
		this.context = context;
		// 子菜单的选项的数据
		this.text_array = array;
		// 父菜单的选项的数据
		this.list = list;
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
		return text_array[groupPosition].length;
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
	 * 获取一级菜单下二级菜单选项的id
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
		return true;
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

		convertView = (LinearLayout) convertView.inflate(context,
				R.layout.item_expandablelist_layout, null);
		ImageView imageView = (ImageView) convertView
				.findViewById(R.id.img_icon);
		TextView textView = (TextView) convertView
				.findViewById(R.id.expandablelist_item_txt);
		// 是否可以点击扩展开来,设置字体显示的位置
		if (isExpanded) {
			textView.setCompoundDrawablesWithIntrinsicBounds(0, 0,
					R.drawable.group_down, 0);
		} else {
			textView.setCompoundDrawablesWithIntrinsicBounds(0, 0,
					R.drawable.group_up, 0);
		}

		// 设置图片和字体的内容
		imageView.setImageResource(Integer.parseInt(list.get(groupPosition)
				.get("img").toString()));
		textView.setText(list.get(groupPosition).get("txt")
				.toString());

		return convertView;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.ExpandableListAdapter#getChildView(int, int, boolean,
	 * android.view.View, android.view.ViewGroup)
	 * 
	 * 
	 * 设置一级菜单下二级菜单的内容
	 */
	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		convertView = (RelativeLayout) convertView.inflate(context,
				R.layout.item_expandablelist_child_layout, null);

		TextView textView = (TextView) convertView
				.findViewById(R.id.expandablelist_child_item);

		textView.setText(text_array[groupPosition][childPosition]);

		return convertView;
	}

	/**
	 * 当选择子节点的时候，调用该方法
	 * 
	 * */
	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return true;
	}

}
