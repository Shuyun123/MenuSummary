package net.anumbrella.menusummary.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import net.anumbrella.menusummary.fragment.TypeFragment;

/**
 * author：anumbrella
 * Date:17/2/23 下午2:24
 *
 * fragment的适配器(右侧ViewPager加载选项卡)
 *
 * Fragment对象会一直存留在内存中，所以当有大量的显示页时，就不适合用FragmentPagerAdapter
 * 了，FragmentPagerAdapter 适用于只有少数的page情况，像选项卡。。
 * 当比较多fragment时，选择FragmentStatePagerAdapter
 */

public class DetailAdapter extends FragmentPagerAdapter {

    private String[] list;

    public DetailAdapter(FragmentManager fm,String[] array) {
        super(fm);
        this.list = array;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new TypeFragment();
        Bundle bundle = new Bundle();
        // 把选中的index指针传入过去
        bundle.putInt("index", position);
        // 设定在fragment当中去
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return list.length;
    }
}
