package net.anumbrella.menusummary.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * author：anumbrella
 * Date:17/2/23 下午3:40
 * 自定义的视图显示(GridView)
 */

public class MyGridView extends GridView {

    public MyGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    /**
     * 设置视图不滚动
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);

    }
}
