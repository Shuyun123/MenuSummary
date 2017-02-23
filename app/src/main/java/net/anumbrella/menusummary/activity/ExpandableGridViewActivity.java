package net.anumbrella.menusummary.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import net.anumbrella.menusummary.R;
import net.anumbrella.menusummary.adapter.ExpandableGridAdapter;
import net.anumbrella.menusummary.config.Config;

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

public class ExpandableGridViewActivity extends AppCompatActivity {

    /**
     * 二级菜单显示的数据
     */
    private String[][] textArray;

    /**
     * 展开的标志信息
     */
    private int sign = -1;

    private List<Map<String, Object>> list;

    private ExpandableGridAdapter adapter;


    @BindView(R.id.ExpandableGridView_list)
    ExpandableListView expandableGridView;


    @BindView(R.id.expandableGridView_back)
    ImageView backImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expandgridview_layout);
        ButterKnife.bind(this);
        textArray = Config.EXPANDABLE_MOREGRIDVIEW_TXT;

        // 初始化数据
        initData();

        // 给expandableListView设置监听
        setListener();

    }

    private void setListener() {
        expandableGridView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if (sign == -1) {
                    // 选择展开的项
                    expandableGridView.expandGroup(groupPosition);
                    // 将展开的项置于顶端
                    expandableGridView.setSelectedGroup(groupPosition);
                    sign = groupPosition;

                } else if (sign == groupPosition) {
                    // 如果已近展开,则将其折叠
                    expandableGridView.collapseGroup(groupPosition);
                    sign = -1;
                } else {
                    // 点击其他选项之前，先将之前的关闭掉
                    expandableGridView.collapseGroup(sign);
                    expandableGridView.expandGroup(groupPosition);
                    sign = groupPosition;
                }
                return true;
            }
        });

        backImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void initData() {
        list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < Config.EXPANDABLE_GRIDVIEW_TXT.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("txt", Config.EXPANDABLE_GRIDVIEW_TXT[i]);
            list.add(map);
        }
        adapter = new ExpandableGridAdapter(this, list, textArray);
        expandableGridView.setAdapter(adapter);
    }


}
