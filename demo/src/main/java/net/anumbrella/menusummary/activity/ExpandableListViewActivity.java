package net.anumbrella.menusummary.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import net.anumbrella.menusummary.R;
import net.anumbrella.menusummary.adapter.ExpandableListAdapter;
import net.anumbrella.menusummary.config.Config;
import net.anumbrella.menusummary.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author：anumbrella
 * Date:17/2/23 下午1:20
 */

public class ExpandableListViewActivity extends AppCompatActivity {


    /**
     * 定义可扩展listView的适配器
     */
    private ExpandableListAdapter adapter;

    private List<Map<String, Object>> list;

    private String[][] textArray;


    @BindView(R.id.ExpandableListView)
    ExpandableListView expandableListView;


    @BindView(R.id.expandlelist_back)
    LinearLayout backImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandlistview_layout);
        ButterKnife.bind(this);
        textArray = Config.EXPANDABLE_MORELISTVIEW_TXT;

        // 初始化数据
        initData();

        // 给expandableListView设置监听
        setListener();

    }


    private void initData() {
        list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < Config.EXPANDABLE_LISTVIEW_TXT.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("img", Config.EXPANDABLE_LISTVIEW_IMG[i]);
            map.put("txt", Config.EXPANDABLE_LISTVIEW_TXT[i]);
            list.add(map);
        }
        adapter = new ExpandableListAdapter(this, list, textArray);
        expandableListView.setAdapter(adapter);

    }


    private void setListener() {
        // 给一级菜单的选项设置监听
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return false;
            }
        });

        // 给一级菜单下面的二级菜单的选项设置监听接口
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                ToastUtils.showToast("你点击的是" + textArray[groupPosition][childPosition]);

                return false;
            }
        });

        // 给返回按钮设置点击事件
        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }


}
