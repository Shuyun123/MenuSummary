package net.anumbrella.menusummary.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * author：anumbrella
 * Date:17/2/16 下午1:55
 */

public class ToastUtils {

    private static Toast mToast;

    private static Context context = AppUtils.getAppContext();


    /**********************
     * 非连续弹出的Toast
     ***********************/


    public static void showSingleToast(int resId) {
        getSingleToast(resId, Toast.LENGTH_SHORT).show();
    }

    public static void showSingleToast(String text) {
        getSingleToast(text, Toast.LENGTH_SHORT).show();
    }

    public static void showSingleLongToast(int resId) {
        getSingleToast(resId, Toast.LENGTH_LONG).show();
    }

    public static void showSingleLongToast(String text) {
        getSingleToast(text, Toast.LENGTH_LONG).show();
    }


    /***********************
     * 连续弹出的Toast
     ************************/

    public static void showToast(int resId) {
        getToast(resId, Toast.LENGTH_SHORT).show();
    }

    public static void showToast(String text) {
        getToast(text, Toast.LENGTH_SHORT).show();
    }

    public static void showLongToast(int resId) {
        getToast(resId, Toast.LENGTH_LONG).show();
    }

    public static void showLongToast(String text) {
        getToast(text, Toast.LENGTH_LONG).show();
    }


    /**
     * 连续调用不会连续弹出，只是替换文本
     *
     * @param resId
     * @param duration
     * @return
     */
    public static Toast getSingleToast(int resId, int duration) {
        return getSingleToast(context.getResources().getText(resId).toString(), duration);
    }


    public static Toast getSingleToast(String text, int duration) {
        if (mToast == null) {
            mToast = Toast.makeText(context, text, duration);
        } else {
            mToast.setText(text);
        }
        return mToast;
    }

    /**
     * 连续调用会连续弹出
     *
     * @param resId
     * @param duration
     * @return
     */
    public static Toast getToast(int resId, int duration) {
        return getToast(context.getResources().getText(resId).toString(), duration);
    }

    public static Toast getToast(String text, int duration) {
        return Toast.makeText(context, text, duration);
    }

}
