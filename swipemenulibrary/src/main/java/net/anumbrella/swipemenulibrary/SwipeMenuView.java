package net.anumbrella.swipemenulibrary;

import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * author：anumbrella
 * Date:17/2/24 上午9:55
 */

public class SwipeMenuView extends LinearLayout implements View.OnClickListener {


    /**
     * listView中每个item的布局
     */
    private SwipeMenuLayout swipeMenuLayout;

    /**
     * listView每个操作菜单选项bean
     */
    private SwipeMenu mMenu;


    /**
     * 操作菜单点击接口引用
     */
    private OnSwipeItemClickListener swipeItemClickListener;

    private int position;

    public SwipeMenuView(SwipeMenu menu) {
        super(menu.getContext());
        mMenu = menu;

        // 获得单个操作菜单中的数据(菜单的名称,背景,id..)
        List<SwipeMenuItem> list = menu.getMenuItems();
        int id = 0;
        for (SwipeMenuItem item : list) {
            addItem(item, id++);
        }
    }


    /**
     * 将布局数据添加到操作菜单
     *
     * @param item
     * @param index
     */
    private void addItem(SwipeMenuItem item, int index) {
        LayoutParams params = new LayoutParams(item.getWidth(), LayoutParams.MATCH_PARENT);
        LinearLayout parent = new LinearLayout(getContext());
        // 给操作菜单添加id
        parent.setId(index);
        parent.setGravity(Gravity.CENTER);
        parent.setOrientation(LinearLayout.VERTICAL);
        parent.setLayoutParams(params);
        parent.setBackgroundDrawable(item.getBackground());
        // 给菜单添加点击事件
        parent.setOnClickListener(this);
        addView(parent);

        if (item.getIcon() != null) {
            parent.addView(createIcon(item));
        }

        if (!TextUtils.isEmpty(item.getTitle())) {
            parent.addView(createTitle(item));
        }

    }



    /**
     * 添加带标题背景的操作菜单选项
     *
     * @param item
     * @return
     */
    private View createTitle(SwipeMenuItem item) {
        TextView textView = new TextView(getContext());
        textView.setText(item.getTitle());
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(item.getTitleColor());
        textView.setTextSize(item.getTitleSize());
        return textView;
    }



    /**
     *
     * 添加带图标背景的操作菜单选项
     *
     * @param item
     * @return
     */
    private View createIcon(SwipeMenuItem item) {
        ImageView imageView = new ImageView(getContext());
        imageView.setImageDrawable(item.getIcon());
        return imageView;
    }


    public void setSwipeMenuLayout(SwipeMenuLayout mLayout) {
        this.swipeMenuLayout = mLayout;
    }


    /**
     * 操作菜单的点击事件
     *
     */
    @Override
    public void onClick(View view) {
        // 如果回调引用不为空并且开启滑动
        if (swipeItemClickListener != null && swipeMenuLayout.isOpen()) {
            swipeItemClickListener.onItemClick(this, mMenu, view.getId());
        }
    }


    public void setPosition(int index) {
        this.position = index;
    }

    public int getPosition() {
        return position;
    }

    public void setOnSwipeItemClickListener(OnSwipeItemClickListener listener) {
        this.swipeItemClickListener = listener;
    }

    public OnSwipeItemClickListener getOnSwipeItemClickListener() {
        return this.swipeItemClickListener;
    }


    /**
     * 操作菜单点击回调接口(在SwipeMenuAdapter重写实现回调)
     */
    public static interface OnSwipeItemClickListener {
        public void onItemClick(SwipeMenuView view, SwipeMenu menu, int index);
    }
}
