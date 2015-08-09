package com.example.classifymenu.view;

import com.example.classifymenu.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * @author anumbrella
 *
 * @date 2015-8-1
 *
 * app的启动入口
 */
public class MainActivity extends Activity implements OnClickListener {

	private Button ListListView;

	private Button listGridView;

	private Button expandAbleListView;

	private Button expandAbleGridView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ListListView = (Button) findViewById(R.id.list);
		ListListView.setOnClickListener(this);
		listGridView = (Button) findViewById(R.id.grid);
		listGridView.setOnClickListener(this);
		expandAbleGridView = (Button) findViewById(R.id.expandableGridView);
		expandAbleGridView.setOnClickListener(this);
		expandAbleListView = (Button) findViewById(R.id.expandableListView);
		expandAbleListView.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case (R.id.list):
			listListViewClick();
			break;

		case (R.id.grid):
			listGridViewClick();
			break;

		case (R.id.expandableListView):
			expandAbleListViewClick();
			break;

		case (R.id.expandableGridView):
			expandAbleGridViewClick();
			break;

		default:
			break;
		}

	}

	private void expandAbleGridViewClick() {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		intent.setClass(this, ExpandAbleGridViewActivity.class);
		startActivity(intent);

	}

	private void expandAbleListViewClick() {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		intent.setClass(this, ExpandableListViewActivity.class);
		startActivity(intent);

	}

	private void listGridViewClick() {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		intent.setClass(this, ListGridViewActivity.class);
		startActivity(intent);

	}

	private void listListViewClick() {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		intent.setClass(this, ListListViewActivity.class);
		startActivity(intent);
	}

}
