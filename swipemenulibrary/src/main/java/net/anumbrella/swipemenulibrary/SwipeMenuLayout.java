package net.anumbrella.swipemenulibrary;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.widget.ScrollerCompat;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.AbsListView;
import android.widget.FrameLayout;

/**
 * author：anumbrella
 * Date:17/2/24 上午9:52
 * listView中每个item项的外层布局
 */

public class SwipeMenuLayout extends FrameLayout {

    private static final int STATE_CLOSE = 0;

    private static final int STATE_OPEN = 1;

    /**
     * 设定操作菜单的状态
     */
    private int state = STATE_CLOSE;

    private View mContentView;

    private int mDownX;


    /**
     * listView中每个item操作菜单的布局
     */
    private SwipeMenuView mMenuView;

    /**
     * 滑动关闭菜单的动画插值器
     */
    private Interpolator mCloseInterpolator;

    /**
     * 滑动打开菜单的动画插值器
     */
    private Interpolator mOpenInterpolator;


    /**
     * 手势触发监测类
     */
    private GestureDetectorCompat mGestureDetector;

    /**
     * 手势触发监听类
     */
    private GestureDetector.OnGestureListener mGestureListener;


    /**
     * 设置最小滑动距离
     */
    private int MIN_FLING_DISTANCE = dp2px(15);

    /**
     * 设置最小滑动速度
     */
    private int MIN_VELOCITYX = dp2px(500);


    /**
     * 实现View平滑滚动的帮助类
     */
    private ScrollerCompat mCloseScroller;

    /**
     * 实现View平滑滚动的帮助类
     */
    private ScrollerCompat mOpenScroller;

    /**
     * 是否可以滑动
     */
    private boolean isFling;

    private int position;


    /**
     * 关菜单操作时视图左边相对父视图的绝对距离
     */
    private int mBaseX;

    public SwipeMenuLayout(View contentView, SwipeMenuView menuView) {
        this(contentView, menuView, null, null);
    }

    public SwipeMenuLayout(View contentView, SwipeMenuView menuView, Interpolator closeInterpolator, Interpolator openInterpolator) {
        super(contentView.getContext());

        mCloseInterpolator = closeInterpolator;
        mOpenInterpolator = openInterpolator;
        // 正常显示的内容
        mContentView = contentView;

        // 操作菜单显示的内容
        mMenuView = menuView;
        mMenuView.setSwipeMenuLayout(this);
        // 初始化一些操作
        init();

    }

    /**
     * 初始化操作
     */
    private void init() {

        // 设置布局样式
        setLayoutParams(new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

        mGestureListener = new GestureDetector.SimpleOnGestureListener() {
            /**
             * Touch了滑动一点距离后，up时触发
             */
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                // 向左滑动
                if ((e1.getX() - e2.getX()) > MIN_FLING_DISTANCE && Math.abs(velocityX) > MIN_VELOCITYX) {
                    // 触发滑动
                    isFling = true;
                }
                return super.onFling(e1, e2, velocityX, velocityY);
            }

            /**
             * Touch down时触发
             */
            @Override
            public boolean onDown(MotionEvent e) {
                isFling = false;
                return true;
            }
        };


        // 启动监听手势
        mGestureDetector = new GestureDetectorCompat(getContext(), mGestureListener);


        // 创建平滑滚动类
        if (mCloseInterpolator != null) {
            // 生成自定义的平滑滚动类
            mCloseScroller = ScrollerCompat.create(getContext(), mCloseInterpolator);
        } else {
            // 生成一个默认的平滑滚动类
            mCloseScroller = ScrollerCompat.create(getContext());
        }


        if (mOpenInterpolator != null) {
            mOpenScroller = ScrollerCompat.create(getContext(), mOpenInterpolator);
        } else {
            mOpenScroller = ScrollerCompat.create(getContext());
        }

        // 设置显示内容的布局参数
        LayoutParams contentParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

        mContentView.setLayoutParams(contentParams);

        mMenuView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

        addView(mContentView);
        addView(mMenuView);

    }


    /**
     * 关闭操作菜单
     */
    public void smoothCloseMenu() {
        state = STATE_CLOSE;
        mBaseX = -mContentView.getLeft();
        mCloseScroller.startScroll(0, 0, mBaseX, 0, 350);
        // 更新视图
        postInvalidate();
    }

    /**
     * 打开操作菜单
     */
    public void smoothOpenMenu() {
        state = STATE_OPEN;
        // getLeft()所获得的距离是在触摸移动抬起来计算所得
        mOpenScroller.startScroll(-mContentView.getLeft(), 0, mMenuView.getWidth(), 0, 350);
        // 更新视图
        postInvalidate();
    }

    /**
     * 关闭菜单时状态变换
     */
    public void closeMenu() {
        if (mCloseScroller.computeScrollOffset()) {
            // 停止动画,当Scroller滚动到最终x与y位置时中止动画
            mCloseScroller.abortAnimation();
        }
        if (state == STATE_OPEN) {
            state = STATE_CLOSE;
            swipe(0);
        }
    }


    /**
     * 打开菜单时状态变换
     */
    public void openMenu() {

        if (state == STATE_CLOSE) {
            state = STATE_OPEN;
            swipe(mMenuView.getWidth());
        }
    }


    /**
     * 菜单布局移动
     *
     * (移动的距离)
     *
     * @param distance
     *
     */
    private void swipe(int distance) {

        if (distance > mMenuView.getWidth()) {
            distance = mMenuView.getWidth();
        }
        // 意外情况过滤
        if (distance < 0) {
            distance = 0;
        }

        // 视图移动
        mContentView.layout(-distance, mContentView.getTop(),
                mContentView.getWidth() - distance, mContentView.getBottom());
        mMenuView.layout(mContentView.getWidth() - distance,
                mContentView.getTop(),
                mContentView.getWidth() + mMenuView.getWidth() - distance,
                mMenuView.getBottom());
    }

    /**
     * 根据手势进行滑动菜单显示操作
     *
     * @param event
     * @return
     */
    public boolean onSwipe(MotionEvent event) {
        // 触摸手势监测
        mGestureDetector.onTouchEvent(event);
        switch (event.getAction()) {
            // 手势触摸点击
            case MotionEvent.ACTION_DOWN:
                // 初始点击屏幕的x坐标值
                mDownX = (int) event.getX();
                isFling = false;
                break;
            case MotionEvent.ACTION_MOVE:
                // 滑动距离
                int dis = (int) (mDownX - event.getX());
                if (state == STATE_OPEN) {
                    dis += mMenuView.getWidth();
                }
                swipe(dis);
                break;
            case MotionEvent.ACTION_UP:
                if (isFling || (mDownX - event.getX()) > (mMenuView.getWidth() / 2)) {
                    smoothOpenMenu();
                } else {
                    smoothCloseMenu();
                }
                break;
        }
        return true;
    }




    /**
     * 平滑滚动操作处理逻辑
     */
    @Override
    public void computeScroll() {

        if (state == STATE_OPEN) {
            // 返回值为boolean，true说明滚动尚未完成，false说明滚动已经完成。这是一个很重要的方法，
            // 通常放在View.computeScroll()中，用来判断是否滚动是否结束。
            if (mOpenScroller.computeScrollOffset()) {
                // 菜单布局移动 getCurrX()获得的x为手机屏幕左侧的x相对父布局的值
                swipe(mOpenScroller.getCurrX());
                // 更新视图
                postInvalidate();
            }
        } else {
            if (mCloseScroller.computeScrollOffset()) {
                swipe(mBaseX - mCloseScroller.getCurrX());
                postInvalidate();
            }
        }

    }

    /**
     * 操作菜单是否打开
     *
     * @return
     */
    public boolean isOpen() {
        return state == STATE_OPEN;
    }

    public void setPosition(int index) {
        this.position = index;
        mMenuView.setPosition(index);
    }

    public int getPosition() {
        return this.position;
    }

    public View getContentView() {
        return mContentView;
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


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 操作菜单的宽可以根据实际情况再添加(因此为unspecified)
        mMenuView.measure(MeasureSpec.makeMeasureSpec(0,
                MeasureSpec.UNSPECIFIED), MeasureSpec.makeMeasureSpec(
                getMeasuredHeight(), MeasureSpec.EXACTLY));

    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        mContentView.layout(0, 0, getMeasuredWidth(),
                mContentView.getMeasuredHeight());
        mMenuView.layout(getMeasuredWidth(), 0,
                getMeasuredWidth() + mMenuView.getMeasuredWidth(),
                mContentView.getMeasuredHeight());
    }

}
