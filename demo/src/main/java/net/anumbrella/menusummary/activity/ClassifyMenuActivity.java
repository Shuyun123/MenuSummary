package net.anumbrella.menusummary.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import net.anumbrella.menusummary.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author：anumbrella
 * Date:17/2/23 下午1:03
 */

public class ClassifyMenuActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classifymenu_layout);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.list,R.id.grid,R.id.expandableGridView,R.id.expandableListView})
    public void click(View view) {
        switch (view.getId()){
            case R.id.list:
                startActivity(ListListViewActivity.class);
                break;
            case R.id.grid:
                startActivity(ListGridViewActivity.class);
                break;
            case R.id.expandableListView:
                startActivity(ExpandableListViewActivity.class);
                break;
            case R.id.expandableGridView:
                startActivity(ExpandableGridViewActivity.class);
                break;

        }
    }


    public void startActivity(Class<?> name){
        Intent intent = new Intent();
        intent.setClass(this,name);
        startActivity(intent);
    }


}
