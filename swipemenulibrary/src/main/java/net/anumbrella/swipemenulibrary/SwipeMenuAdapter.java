package net.anumbrella.swipemenulibrary;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.WrapperListAdapter;

/**
 * author：anumbrella
 * Date:17/2/24 上午11:05
 * listView菜单(item)适配器(装饰模式)
 */

public class SwipeMenuAdapter implements WrapperListAdapter, SwipeMenuView.OnSwipeItemClickListener {

    /**
     * SwipeMenuListView中接口引用
     */
    private SwipeMenuListView.OnMenuItemClickListener onMenuItemClickListener;


    private ListAdapter listAdapter;

    private Context mContext;


    public SwipeMenuAdapter(Context context, ListAdapter adapter) {
        this.listAdapter = adapter;
        this.mContext = context;
    }


    @Override
    public ListAdapter getWrappedAdapter() {
        return listAdapter;
    }

    /*
     * 是否listView中的所有的项目都可以有用(可点击、可选择)
     */
    @Override
    public boolean areAllItemsEnabled() {
        return listAdapter.areAllItemsEnabled();
    }

    /*
     * 如果这个项目不是一个隔离(separator)项目则返回真。 隔离项目(不可点击、不可选择)
     */
    @Override
    public boolean isEnabled(int position) {
        return listAdapter.isEnabled(position);
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {
        listAdapter.registerDataSetObserver(dataSetObserver);
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
        listAdapter.unregisterDataSetObserver(dataSetObserver);
    }

    @Override
    public int getCount() {
        return listAdapter.getCount();
    }

    @Override
    public Object getItem(int position) {
        return listAdapter.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return listAdapter.getItemId(position);
    }

    /*
     * 组和子元素是否持有稳定的ID,也就是底层数据的改变不会影响到它们。
	 * 返回一个Boolean类型的值，如果为TRUE，意味着相同的ID永远引用相同的对象
	 */
    @Override
    public boolean hasStableIds() {
        return listAdapter.hasStableIds();
    }

    /**
     * listView 每一个item项的布局视图
     *
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SwipeMenuLayout layout = null;
        if (convertView == null) {
            // 获得原本的布局视图
            View contentView = listAdapter.getView(position, convertView, parent);
            SwipeMenu menu = new SwipeMenu(mContext);
            menu.setViewType(listAdapter.getItemViewType(position));
            createMenu(menu);
            SwipeMenuView menuView = new SwipeMenuView(menu);
            // 设置回调引用
            menuView.setOnSwipeItemClickListener(this);
            // 获取滑动插值器
            SwipeMenuListView listView = (SwipeMenuListView) parent;

            layout = new SwipeMenuLayout(contentView, menuView,
                    listView.getCloseInterpolator(),
                    listView.getOpenInterpolator());
            layout.setPosition(position);
        } else {
            layout = (SwipeMenuLayout) convertView;
            layout.closeMenu();
            layout.setPosition(position);
            // 注意:getView是匹配新的数据,如果没有数据不为null,但为原来的数据
            // 会自动更新为正确的数据
            listAdapter.getView(position, layout.getContentView(), parent);
        }

        return layout;
    }

    /**
     * 生成操作菜单的数据
     *
     * @param menu
     */
    public void createMenu(SwipeMenu menu) {
        // 这个方法在activity会被重写。。代码没有用,可以是空函数(如果为空,必须覆写防止数据为空)
        SwipeMenuItem item = new SwipeMenuItem(mContext);
        item.setTitle("Item 1");
        item.setBackground(new ColorDrawable(Color.GRAY));
        item.setWidth(300);
        menu.addMenuItem(item);

        item = new SwipeMenuItem(mContext);
        item.setTitle("Item 2");
        item.setBackground(new ColorDrawable(Color.RED));
        item.setWidth(300);
        menu.addMenuItem(item);

    }


    @Override
    public int getItemViewType(int position) {
        return listAdapter.getItemViewType(position);
    }

    @Override
    public int getViewTypeCount() {
        return listAdapter.getViewTypeCount();
    }

    @Override
    public boolean isEmpty() {
        return listAdapter.isEmpty();
    }

    /**
     * 覆写回调方法,实现SwipeMenuView中的接口,在SwipeMenuView方法onClick()中实现回调
     */
    @Override
    public void onItemClick(SwipeMenuView view, SwipeMenu menu, int index) {

        // 这里实现回调(注意:比较特别的是可以在这里为空函数,在SwipeMenuListView中重写了该方法)
        if (onMenuItemClickListener != null) {
            // 传入参数:第几条数据,操作菜单数据,点击菜单的id(0或者1)
            onMenuItemClickListener.onMenuItemClick(view.getPosition(), menu, index);
        }
    }

    public void setOnMenuItemClickListener(SwipeMenuListView.OnMenuItemClickListener listener) {
        this.onMenuItemClickListener = listener;
    }
}
