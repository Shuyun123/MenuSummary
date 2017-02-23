package net.anumbrella.swipemenulibrary;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * author：anumbrella
 * Date:17/2/23 下午8:10
 * 删除,添加单个菜单项目操作类
 */

public class SwipeMenu {

    private Context mContext;

    private List<SwipeMenuItem> mItems;

    /**
     * 菜单类型
     */
    private int mViewType;

    public SwipeMenu(Context context) {
        mContext = context;
        mItems = new ArrayList<SwipeMenuItem>();
    }

    public Context getContext() {
        return mContext;
    }

    public void addMenuItem(SwipeMenuItem item) {
        mItems.add(item);
    }

    public void removeMenuItem(SwipeMenuItem item) {
        mItems.remove(item);
    }

    public void setViewType(int viewType) {
        this.mViewType = viewType;
    }

    public int getViewType() {
        return mViewType;
    }

    /**
     * 获取单个菜单Item项(每个item中包含数据(名称,图片,背景....))
     *
     * @param index
     * @return
     */
    public SwipeMenuItem getMenuItem(int index) {
        return mItems.get(index);
    }

    /**
     * 获取所有的菜单item项
     *
     * @return
     */
    public List<SwipeMenuItem> getMenuItems() {
        return mItems;
    }


}
