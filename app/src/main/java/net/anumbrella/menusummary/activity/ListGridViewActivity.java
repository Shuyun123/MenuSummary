package net.anumbrella.menusummary.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import net.anumbrella.menusummary.R;
import net.anumbrella.menusummary.adapter.DetailAdapter;
import net.anumbrella.menusummary.config.Config;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author：anumbrella
 * Date:17/2/23 下午1:21
 */

public class ListGridViewActivity extends AppCompatActivity {


    private String[] list;

    private DetailAdapter detailAdapter;

    private View[] views;

    private TextView[] textList;

    private LayoutInflater inflater;


    @BindView(R.id.list_scrollView)
    ScrollView scrollView;


    @BindView(R.id.detail_layout)
    ViewPager viewPager;

    /**
     * 默认的ViewPager选中的项
     */
    private int currentItem = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listgridview_layout);
        ButterKnife.bind(this);
        inflater = LayoutInflater.from(this);
        initList();
        initViewPager();
    }


    private void initList() {
        list = Config.list;
        views = new View[list.length];
        textList = new TextView[list.length];
        LinearLayout listLayout = (LinearLayout) findViewById(R.id.list);

        for (int i = 0; i < list.length; i++) {
            View view = inflater.inflate(R.layout.list_item_layout, null);
            // 给每个View设定唯一标识
            view.setId(i);
            // 给每个view添加点击监控事件
            view.setOnClickListener(listItemClickListener);
            // 获取到左侧栏的的TextView的组件
            TextView textView = (TextView) view.findViewById(R.id.textView);
            textView.setText(list[i]);
            listLayout.addView(view);
            // 传入的是地址不是复制的值
            textList[i] = textView;
            views[i] = view;
        }
        // 开始默认是第一个被选中的情况
        changeTextColor(0);
    }

    private void initViewPager() {

        // 由于使用了支持包所以最终必须确保所有的导入包都是来自支持包
        detailAdapter = new DetailAdapter(getSupportFragmentManager(), list);
        viewPager.setAdapter(detailAdapter);
        // 为ViewPager设置页面变化的监控
        viewPager.setOnPageChangeListener(onPageChangeListener);
    }

    /**
     * ViewPager监控事件处理 监听ViewPager选项卡变化事的事件
     */
    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int index) {
            if (viewPager.getCurrentItem() != index) {
                viewPager.setCurrentItem(index);
            }
            // 通过ViewPager监听点击字体颜色和背景的改变
            if (currentItem != index) {
                changeTextColor(index);
                changeTextLocation(index);
            }
            currentItem = index;

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    };


    /**
     * 模拟点击时TextView文本字体颜色的改变情况
     *
     * @param id
     */
    private void changeTextColor(int id) {
        for (int i = 0; i < list.length; i++) {
            if (id != i) {
                textList[i].setBackgroundColor(0x00000000);
                textList[i].setTextColor(0xFF000000);
            }
        }
        textList[id].setBackgroundColor(0xFFFFFFFF);
        textList[id].setTextColor(0xFFFF5D5E);

    }


    /**
     * view的点击事件,
     * <p>
     * 通过点击view来是ViewPager的状态发生改变，同时ViewPager监控自己的改变 可以用来处理一些事件的情况
     */
    private View.OnClickListener listItemClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            viewPager.setCurrentItem(v.getId());
        }
    };


    /**
     * 改变左侧栏选中项的位置
     *
     * @param clickPosition
     */
    private void changeTextLocation(int clickPosition) {
        // 获得点击的view视图距离屏幕顶部的距离
        int y = (views[clickPosition].getTop());
        // 如果滑动条可以滑动的情况下就把点击的视图移动到顶部
        scrollView.smoothScrollTo(0, y);

    }


}
