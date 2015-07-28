package com.example.classifymenu.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.classifymenu.DataModel;
import com.example.classifymenu.R;
import com.example.classifymenu.adapter.ClassifyMainAdapter;
import com.example.classifymenu.adapter.ClassifyMoreAdapter;

public class ListListViewActivity extends Activity {

	private ClassifyMainAdapter classifyMainAdapter;
	private ClassifyMoreAdapter classifyMoreAdapter;
	private List<Map<String, Object>> mainList;
	private ListView mainlist;
	private ListView morelist;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listlistview_layout);
		initModelData();
		initView();

	}

	// 显示视图
	private void initView() {
		// TODO Auto-generated method stub
		mainlist = (ListView) findViewById(R.id.main_view);
		morelist = (ListView) findViewById(R.id.more_view);

		classifyMainAdapter = new ClassifyMainAdapter(
				ListListViewActivity.this, mainList);
		// 默认选中第一个选项
		mainlist.setSelection(0);
		// 建立数据适配
		mainlist.setAdapter(classifyMainAdapter);

		// 设置listView当中的每个单项点击的事件变化逻辑处理
		mainlist.setOnItemClickListener(new OnItemClickListener() {

			// 主目录的点击事件发生后，就要为侧目进行数据的交互
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				

			}
		});

	}

	// 初始化化数据的设定（String在java中为对象存储的,不是基本的常量）
	private void initModelData() {
		// TODO Auto-generated method stub
		mainList = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < DataModel.LISTVIEWTXT.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			// 根据键值对存储到HashMap中去
			map.put("img", DataModel.LISTVIEWIMG[i]);
			map.put("txt", DataModel.LISTVIEWTXT[i]);
			mainList.add(map);
		}

	}

}
