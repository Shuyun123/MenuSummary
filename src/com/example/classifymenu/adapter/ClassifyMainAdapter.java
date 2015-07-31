package com.example.classifymenu.adapter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.classifymenu.R;

/**
 * created anumbrella 2015 7 28
 * 
 * 左侧的数据适配器
 * */
public class ClassifyMainAdapter extends BaseAdapter {

	private Context mContext;
	private List<Map<String, Object>> list;
	private Hoder hoder;
	// 是否要加载显示图片
	private boolean isLoadingImage = true;

	// 默认第一个图片是选中了的
	private int selectPosition = 0;

	public ClassifyMainAdapter(Context context, List<Map<String, Object>> list) {
		this.mContext = context;
		this.list = list;
	}

	public ClassifyMainAdapter(Context context, List<Map<String, Object>> list,
			boolean isLoadingImage) {
		this.mContext = context;
		this.list = list;
		this.isLoadingImage = isLoadingImage;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	public void setSelectItem(int position) {
		this.selectPosition = position;
	}

	public int getSelectItem() {
		return this.selectPosition;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			convertView = View.inflate(mContext,
					R.layout.item_classify_mainlist, null);
			hoder = new Hoder(convertView);
			// 把数据存储到convertView当中去
			convertView.setTag(hoder);
		} else {
			hoder = (Hoder) convertView.getTag();
		}

		if (isLoadingImage == true) {
			hoder.imageView.setImageResource(Integer.parseInt(list
					.get(position).get("img").toString()));

		}

		hoder.layout.setBackgroundColor(0xFFEBEBEB);
		hoder.textView.setText(list.get(position).get("txt").toString());
		if (position == selectPosition) {
			hoder.layout.setBackgroundColor(0xFFFFFFFF);
		}
		return convertView;
	}

	private static class Hoder {

		private ImageView imageView;
		private TextView textView;
		private LinearLayout layout;

		public Hoder(View view) {
			imageView = (ImageView) view.findViewById(R.id.mainItem_img);
			textView = (TextView) view.findViewById(R.id.mainItem_txt);
			layout = (LinearLayout) view.findViewById(R.id.mainList_layout);

		}

	}

}
