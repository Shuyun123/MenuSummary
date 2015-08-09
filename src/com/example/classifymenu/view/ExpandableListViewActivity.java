package com.example.classifymenu.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.classifymenu.DataModel;
import com.example.classifymenu.R;
import com.example.classifymenu.adapter.ExpandableListAdapter;

/**
 * @author anumbrella
 * 
 * @date 2015-8-7 下午2:04:51
 * 
 *       扩展ListView的显示
 */
public class ExpandableListViewActivity extends Activity {

	/**
	 * 定义可扩展的listView
	 * 
	 */
	private ExpandableListView expandableListView;

	/**
	 * 图片指示回退
	 */
	private ImageView backImage;

	/**
	 * 定义可扩展listView的适配器
	 */

	private ExpandableListAdapter adapter;

	private List<Map<String, Object>> list;

	private String[][] textArray;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.expandlistview_layout);

		expandableListView = (ExpandableListView) findViewById(R.id.ExpandableListView);
		backImage = (ImageView) findViewById(R.id.expandlelist_back);
		textArray = DataModel.EXPANDABLE_MORELISTVIEW_TXT;

		// 初始化数据
		initData();

		// 给expandableListView设置监听
		setListener();

	}

	// 设置监听接口
	private void setListener() {
		// TODO Auto-generated method stub

		// 给一级菜单的选项设置监听
		expandableListView.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				// TODO Auto-generated method stub
				return false;
			}
		});

		// 给一级菜单下面的二级菜单的选项设置监听接口
		expandableListView.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(),
						"你点击的是" + textArray[groupPosition][childPosition],
						Toast.LENGTH_SHORT).show();

				return false;
			}
		});

		// 给返回按钮设置点击事件
		backImage.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});

	}

	// 初始化数据的填充
	private void initData() {
		// TODO Auto-generated method stub
		list = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < DataModel.EXPANDABLE_LISTVIEW_TXT.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("img", DataModel.EXPANDABLE_LISTVIEW_IMG[i]);
			map.put("txt", DataModel.EXPANDABLE_LISTVIEW_TXT[i]);
			list.add(map);
		}
		adapter = new ExpandableListAdapter(this, list, textArray);
		expandableListView.setAdapter(adapter);

	}

}
