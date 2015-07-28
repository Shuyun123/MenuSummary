package com.example.classifymenu.view;

import com.example.classifymenu.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	private Button ListListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ListListView = (Button) findViewById(R.id.list);
		ListListView.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.list:
			listListViewClick();
			break;

		default:
			break;
		}

	}

	private void listListViewClick() {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		intent.setClass(this, ListListViewActivity.class);
		startActivity(intent);
	}

}
