package net.anumbrella.swipemenulibrary;

import android.content.Context;
import android.graphics.drawable.Drawable;

/**
 * author：anumbrella
 * Date:17/2/23 下午8:12
 */

public class SwipeMenuItem {

    private int id;

    private Context mContext;

    private String title;

    private Drawable icon;

    private Drawable background;

    private int titleColor;

    private int titleSize;

    private int width;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public Drawable getBackground() {
        return background;
    }

    public void setBackground(Drawable background) {
        this.background = background;
    }

    public int getTitleSize() {
        return titleSize;
    }

    public void setTitleSize(int titleSize) {
        this.titleSize = titleSize;
    }

    public int getTitleColor() {
        return titleColor;
    }

    public void setTitleColor(int titleColor) {
        this.titleColor = titleColor;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
