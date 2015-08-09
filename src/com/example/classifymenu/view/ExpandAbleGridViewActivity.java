package com.example.classifymenu.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import com.example.classifymenu.DataModel;
import com.example.classifymenu.R;
import com.example.classifymenu.adapter.ExpandableGridAdapter;

/**
 * @author anumbrella
 * 
 * @date 2015-8-9 下午8:09:59
 * 
 *       扩展GridView显示
 */
public class ExpandAbleGridViewActivity extends Activity {

	/**
	 * 扩展GridView显示
	 * 
	 */
	private ExpandableListView expandableGridView;

	private List<Map<String, Object>> list;

	private ExpandableGridAdapter adapter;

	private ImageView imageView;

	/**
	 * 二级菜单显示的数据
	 */
	private String[][] textArray;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.expandgridview_layout);
		expandableGridView = (ExpandableListView) findViewById(R.id.ExpandableGridView_list);
		imageView = (ImageView) findViewById(R.id.expandableGridView_back);
		textArray = DataModel.EXPANDABLE_MOREGRIDVIEW_TXT;
		// 初始化数据
		initData();
		// 给expandableListView设置监听
		setListener();

	}

	/**
	 * 添加监听接口
	 */
	private void setListener() {
		// TODO Auto-generated method stub

	}

	/**
	 * 初始化进行数据的填充
	 */
	private void initData() {
		// TODO Auto-generated method stub
		list = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < DataModel.EXPANDABLE_GRIDVIEW_TXT.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("txt", DataModel.EXPANDABLE_GRIDVIEW_TXT[i]);
			list.add(map);
		}
		adapter = new ExpandableGridAdapter(this, list, textArray);
		expandableGridView.setAdapter(adapter);

	}

}
