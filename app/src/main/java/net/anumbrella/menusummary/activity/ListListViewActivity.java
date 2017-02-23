package net.anumbrella.menusummary.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import net.anumbrella.menusummary.R;
import net.anumbrella.menusummary.adapter.ClassifyMainAdapter;
import net.anumbrella.menusummary.adapter.ClassifyMoreAdapter;
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

public class ListListViewActivity extends AppCompatActivity {

    private ClassifyMainAdapter classifyMainAdapter;

    private ClassifyMoreAdapter classifyMoreAdapter;

    private List<Map<String, Object>> mainList;


    @BindView(R.id.main_view)
    ListView mainlist;

    @BindView(R.id.more_view)
    ListView morelist;


    private int mainSelectPostion = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listlistview_layout);
        ButterKnife.bind(this);
        initModelData();
        initView();
    }

    private void initView() {
        classifyMainAdapter = new ClassifyMainAdapter(this, mainList);
        // 默认选中第一个选项
        mainlist.setSelection(0);
        // 建立数据适配
        mainlist.setAdapter(classifyMainAdapter);

        // 设置listView当中的每个单项点击的事件变化逻辑处理
        mainlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            // 主目录的点击事件发生后，就要为侧目进行数据的交互
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mainSelectPostion = position;
                // 主目录一位数组的大小和侧目录二维数组的行的数目是一致的
                // 点击传入二维数组的一行的数据
                inintAdapter(Config.MORELISTVIEWTXT[position]);
                // 设置选中的选的id
                classifyMainAdapter.setSelectItem(position);
                // 更新数据的变更
                classifyMainAdapter.notifyDataSetChanged();

            }

        });

        /**
         * CHOICE_MODE_NONE是普通模式
         *
         * CHOICE_MODE_SINGLE是单选模式，不常用
         *
         * CHOICE_MODE_MULTIPLE和CHOICE_MODE_MULTIPLE_MODAL都是多选模式
         *
         * 设置选着的模式
         * */
        mainlist.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        // 设置还没有点击时默认的显示页面的内容
        inintAdapter(Config.MORELISTVIEWTXT[0]);

        // 设置详细列表的点击事件处理逻辑
        morelist.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                classifyMoreAdapter.setSelectItem(position);
                classifyMoreAdapter.notifyDataSetChanged();
                ToastUtils.showToast("你点击的是" + Config.MORELISTVIEWTXT[mainSelectPostion][position]);

            }
        });


    }


    /**
     * 为侧目录(详细目录)进行数据的匹配处理
     * @param array
     */
    private void inintAdapter(String[] array) {
        classifyMoreAdapter = new ClassifyMoreAdapter(this, array);
        morelist.setAdapter(classifyMoreAdapter);
        classifyMoreAdapter.notifyDataSetChanged();
    }



    /**
     * 初始化化数据的设定（String在java中为对象存储的,不是基本的常量）
     */
    private void initModelData() {

        mainList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < Config.LISTVIEWTXT.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            // 根据键值对存储到HashMap中去
            map.put("img", Config.LISTVIEWIMG[i]);
            map.put("txt", Config.LISTVIEWTXT[i]);
            mainList.add(map);
        }

    }


}
