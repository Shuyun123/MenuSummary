package net.anumbrella.swipemenulibrary;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * author：anumbrella
 * Date:17/2/24 上午11:04
 * 自定义视图可滑动删除菜单
 */

public class SwipeMenuListView extends ListView {


    /**
     * 手势触摸屏幕的状态(如:左右滑动,上下滑动)
     */
    private static final int TOUCH_STATE_NONE = 0;
    private static final int TOUCH_STATE_X = 1;
    private static final int TOUCH_STATE_Y = 2;


    /**
     * 左右滑动距离判断值
     */
    private int MAX_X = 3;
    /**
     * 上下滑动距离判断值
     */
    private int MAX_Y = 5;


    /**
     * 操作菜单生成接口
     */
    private SwipeMenuCreator menuCreator;

    /**
     * 设置滑动关闭动画插值器
     */
    private Interpolator mCloseInterpolator;

    /**
     * 设置滑动动画打开插值器
     */
    private Interpolator mOpenInterpolator;

    /**
     * 菜单点击回调接口引用
     */
    private OnMenuItemClickListener onMenuItemClickListener;


    /**
     * 滑动菜单前后处理操作接口
     */
    private OnSwipeListener onSwipeListener;

    /**
     * 每个listView的item布局
     */
    private SwipeMenuLayout mTouchView;

    /**
     * 触摸屏幕坐标保存位置
     */
    private int mTouchPosition;

    private float mDownX;
    private float mDownY;

    private int mTouchState;

    /**
     * 没有指定xml调用
     *
     * @param context
     */
    public SwipeMenuListView(Context context) {
        super(context);
        init();
    }

    /**
     * 指定xml,没有默认style样式调用
     *
     * @param context
     * @param attrs
     */
    public SwipeMenuListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     * 指定xml,指定默认style样式调用
     *
     * @param context
     * @param attrs
     * @param defStyle
     */
    public SwipeMenuListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    /**
     * 初始化一些数据
     */
    private void init() {
        MAX_X = dp2px(MAX_X);
        MAX_Y = dp2px(MAX_Y);
        // 初始化触摸手势状态
        mTouchState = TOUCH_STATE_NONE;
    }

    /**
     * 重写setAdapter方法,传入自定的adapter和视图样式
     */
    @Override
    public void setAdapter(ListAdapter adapter) {
        // 在这里重写自定义的adapter的方法
        super.setAdapter(new SwipeMenuAdapter(getContext(), adapter) {

            // 重写生成菜单的方法,在这里回调activity的SwipeMenuCreator接口
            @Override
            public void createMenu(SwipeMenu menu) {
                if (menuCreator != null) {
                    menuCreator.create(menu);
                }
            }

            // 重写SwipeMenuView里面的回调函数(注意:意味着在SwipeMenuListView里实现SwipeMenuView的接口————回调方法)
            @Override
            public void onItemClick(SwipeMenuView view, SwipeMenu menu, int index) {
                if (onMenuItemClickListener != null) {
                    // 调用回调函数(在activity中实现重写)
                    onMenuItemClickListener.onMenuItemClick(view.getPosition(), menu, index);
                }
                if (mTouchView != null) {
                    mTouchView.smoothCloseMenu();
                }
            }
        });
    }


    /**
     * listView中每个item的触摸点击后事件处理
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() != MotionEvent.ACTION_DOWN && mTouchView == null) {
            return super.onTouchEvent(event);
        }
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                // 默认开始为0
                int oldPos = mTouchPosition;
                mDownX = event.getX();
                mDownY = event.getY();
                // 每次重新定义状态
                mTouchState = TOUCH_STATE_NONE;
                // 依据触摸点的坐标计算出点击的是ListView的哪个Item的位置值
                mTouchPosition = pointToPosition((int) event.getX(),
                        (int) event.getY());
                // 处理当滑动后打开菜单后,点击操作后菜单自动关闭
                if (mTouchPosition == oldPos && mTouchView != null && mTouchView.isOpen()) {
                    // 进行的是左右滑动打开了菜单
                    mTouchState = TOUCH_STATE_X;
                    // 传递给子视图进行相关操作
                    mTouchView.onSwipe(event);
                    // 结束
                    return true;
                }
                // 获取点击视图(防止当mTouchView为空时,产生的异常)
                View view = getChildAt(mTouchPosition - getFirstVisiblePosition());

                // 处理打开一个菜单后,又点击另一个菜单操作
                if (mTouchView != null && mTouchView.isOpen()) {
                    mTouchView.smoothCloseMenu();
                    mTouchView = null;
                    return super.onTouchEvent(event);
                }

                if (view instanceof SwipeMenuLayout) {
                    mTouchView = (SwipeMenuLayout) view;
                }

                if (mTouchView != null) {
                    mTouchView.onSwipe(event);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                // 在滑动过程当中,ACTION_MOVE会一直触发
                float dx = Math.abs((event.getX() - mDownX));
                float dy = Math.abs((event.getY() - mDownY));
                // 如果左右滑动
                if (mTouchState == TOUCH_STATE_X) {
                    if (mTouchView != null) {
                        mTouchView.onSwipe(event);
                    }
                    // 终止当前手势动作
                    event.setAction(MotionEvent.ACTION_CANCEL);
                    super.onTouchEvent(event);
                } else if (mTouchState == TOUCH_STATE_NONE) {
                    // 开始会根据滑动的动作调用这里
                    if (Math.abs(dy) > MAX_Y) {
                        // 上下滑动
                        mTouchState = TOUCH_STATE_Y;
                    } else if (Math.abs(dx) > MAX_X) {
                        // 左右滑动
                        mTouchState = TOUCH_STATE_X;
                        if (onSwipeListener != null) {
                            // 滑动菜单前操作(调用回调函数)
                            onSwipeListener.onSwipeStart(mTouchPosition);
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                if (mTouchState == TOUCH_STATE_X) {
                    if (mTouchView != null) {
                        mTouchView.onSwipe(event);
                        // 如果菜单还没关闭
                        if (!mTouchView.isOpen()) {
                            // 一个不存在的指针值
                            mTouchPosition = -1;
                            mTouchView = null;
                        }
                    }
                    if (onSwipeListener != null) {
                        // 滑动菜单后操作(调用回调函数)
                        onSwipeListener.onSwipeEnd(mTouchPosition);
                    }
                    event.setAction(MotionEvent.ACTION_CANCEL);
                    super.onTouchEvent(event);
                    return true;
                }
                break;
        }
        return super.onTouchEvent(event);



    }


    public void setOnMenuItemClickListener(OnMenuItemClickListener listener) {
        this.onMenuItemClickListener = listener;
    }

    public void setOnSwipeListener(OnSwipeListener listener) {
        this.onSwipeListener = listener;
    }

    public void setMenuCreator(SwipeMenuCreator menuCreator) {
        this.menuCreator = menuCreator;
    }

    public void setCloseInterpolator(Interpolator interpolator) {
        this.mCloseInterpolator = interpolator;
    }

    public Interpolator getCloseInterpolator() {
        return mCloseInterpolator;
    }

    public void setOpenInterpolator(Interpolator interpolator) {
        this.mOpenInterpolator = interpolator;
    }

    public Interpolator getOpenInterpolator() {
        return mOpenInterpolator;
    }

    /**
     * dp转px
     *
     * @param dp
     * @return
     */
    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getContext().getResources().getDisplayMetrics());
    }


    /**
     * 操作菜单操作回调函数(在activity中实现接口回调)
     */
    public static interface OnMenuItemClickListener {
        public void onMenuItemClick(int position, SwipeMenu menu, int index);
    }


    /**
     * 滑动菜单前后操作接口(在activity中实现回调)
     */
    public static interface OnSwipeListener {
        // 滑动菜单前的操作接口
        public void onSwipeStart(int position);

        // 滑动菜单后的操作接口
        public void onSwipeEnd(int position);
    }

}
