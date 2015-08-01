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
import android.widget.Toast;

import com.example.classifymenu.DataModel;
import com.example.classifymenu.R;
import com.example.classifymenu.adapter.ClassifyMainAdapter;
import com.example.classifymenu.adapter.ClassifyMoreAdapter;

/**
 * @author anumbrella
 * 
 * @date 2015-7-31
 * 
 */
public class ListListViewActivity extends Activity {

	private ClassifyMainAdapter classifyMainAdapter;
	private ClassifyMoreAdapter classifyMoreAdapter;
	private List<Map<String, Object>> mainList;
	private ListView mainlist;
	private ListView morelist;
	
	private int mainSelectPostion = 0;

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
				mainSelectPostion = position;
				// 主目录一位数组的大小和侧目录二维数组的行的数目是一致的
				// 点击传入二维数组的一行的数据
				inintAdapter(DataModel.MORELISTVIEWTXT[position]);
				// 设置选中的选的id
				classifyMainAdapter.setSelectItem(position);
				// 更新数据的变更
				classifyMainAdapter.notifyDataSetChanged();

			}

		});

		/**
		 * CHOICE_MODE_NONE是普通模式， CHOICE_MODE_SINGLE是单选模式，不常用，
		 * CHOICE_MODE_MULTIPLE和CHOICE_MODE_MULTIPLE_MODAL都是多选模式
		 * 
		 * 设置选着的模式
		 * */
		mainlist.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

		// 设置还没有点击时默认的显示页面的内容
		inintAdapter(DataModel.MORELISTVIEWTXT[0]);

		// 设置详细列表的点击事件处理逻辑
		morelist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub

				classifyMoreAdapter.setSelectItem(position);
				classifyMoreAdapter.notifyDataSetChanged();

				Toast.makeText(getApplicationContext(),
						"你点击的是" + DataModel.MORELISTVIEWTXT[mainSelectPostion][position],
						Toast.LENGTH_SHORT).show();
			}
		});

	}

	// 为侧目录(详细目录)进行数据的匹配处理
	private void inintAdapter(String[] array) {
		// TODO Auto-generated method stub
		classifyMoreAdapter = new ClassifyMoreAdapter(this, array);
		morelist.setAdapter(classifyMoreAdapter);
		classifyMoreAdapter.notifyDataSetChanged();
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
