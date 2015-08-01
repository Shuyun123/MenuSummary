package com.example.classifymenu.fragment;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.classifymenu.DataModel;
import com.example.classifymenu.R;
import com.example.classifymenu.adapter.GridViewAdapter;
import com.example.classifymenu.bean.Type;

/**
 * @author anumbrella
 * 
 * @date 2015-8-1
 * 
 *       用于右侧栏显示详细信息
 * 
 *       (滑动的ViewPager,每一个滑动的页面都是一个fragment)
 * 
 */
public class TypeFragment extends Fragment {

	private ArrayList<Type> listType;
	// widget网格view
	private GridView gridView;
	// 网格适配器
	private GridViewAdapter adapter;

	// 右侧栏目中的子选项
	private Type type;

	// 子选项名称
	private String typeName;

	// 图片
	private int icon;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		View view = inflater.inflate(R.layout.shopslist_layout, null);

		gridView = (GridView) view.findViewById(R.id.GridViewList);

		int index = getArguments().getInt("index");

		typeName = DataModel.toolist[index];
		icon = DataModel.iconList[index];

		((TextView) view.findViewById(R.id.TypeName)).setText(typeName);

		// 为listType装载数据
		setTypeList();
		
		adapter = new GridViewAdapter(getActivity(), listType);
		gridView.setAdapter(adapter);
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				String typeName = listType.get(position).getTypeName();
				Toast.makeText(getActivity(), "你点击的是"+typeName, Toast.LENGTH_SHORT).show();
				
			}
		});
		

		return view;
	}

	/**
	 * 给listType设定数据
	 */
	private void setTypeList() {
		// TODO Auto-generated method stub
		listType = new ArrayList<Type>();
		// 这里可以根据数据设定要填充的资源
		for (int i = 0; i < 23; i++) {
			type = new Type(i, icon, typeName + i);
			listType.add(type);
		}
	}
}
