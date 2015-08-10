package com.example.classifymenu.adapter;

import java.util.ArrayList;
import java.util.List;

import com.example.classifymenu.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * @author anumbrella
 * 
 * @date 2015-8-10 下午12:54:46
 * 
 *       expandableGridView二级菜单文本适配器
 */
public class ExpandableGridTextAdapter extends BaseAdapter {

	private List<String> child_text_array = new ArrayList<String>();

	private Context context;

	public ExpandableGridTextAdapter(List<String> list, Context context) {
		this.context = context;
		this.child_text_array = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return child_text_array.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return child_text_array.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		TextView textView = null;
		if (convertView == null) {
			convertView = convertView.inflate(context,
					R.layout.item_giridview_text, null);
			textView = (TextView) convertView
					.findViewById(R.id.item_expandablegridview_text);
			convertView.setTag(textView);
		} else {
			textView = (TextView) convertView.getTag();
		}

		textView.setText(child_text_array.get(position));
		return convertView;
	}

}
