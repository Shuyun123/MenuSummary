package com.example.classifymenu.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * @author anumbrella
 * 
 * @date 2015-8-9 下午8:44:54
 * 
 *       自定义的视图显示(GridView)
 */
public class MyGridView extends GridView {

	public MyGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.GridView#onMeasure(int, int)
	 * 
	 * 设置视图不滚动
	 */
	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);

	}

}
