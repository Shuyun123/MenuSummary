package net.anumbrella.menusummary.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import net.anumbrella.menusummary.R;
import net.anumbrella.menusummary.utils.ToastUtils;
import net.anumbrella.swipemenulibrary.SwipeMenu;
import net.anumbrella.swipemenulibrary.SwipeMenuCreator;
import net.anumbrella.swipemenulibrary.SwipeMenuItem;
import net.anumbrella.swipemenulibrary.SwipeMenuListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author：anumbrella
 * Date:17/2/24 下午1:33
 */
public class SwipeMenuActivity extends AppCompatActivity {


    /**
     * App应用信息
     */
    private List<ApplicationInfo> mAppList;

    private AppAdapter mAdapter;

    @BindView(R.id.listView)
    SwipeMenuListView mListView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipemenu_layout);
        ButterKnife.bind(this);

        // 获取已经安装的软件信息
        mAppList = getPackageManager().getInstalledApplications(0);

        mAdapter = new AppAdapter();

        mListView.setAdapter(mAdapter);

        init();


    }

    private void init() {
        // 创建操作菜单
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            // 定义回调方法(接口SwipeMenuCreator)
            @Override
            public void create(SwipeMenu menu) {
                // 添加打开操作选项
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());

                // 设置背景颜色
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                // 设置宽度
                openItem.setWidth(dp2px(90));
                // 设置标题
                openItem.setTitle("open");
                // 设置标题字体大小
                openItem.setTitleSize(18);
                // 设置标题字体颜色
                openItem.setTitleColor(Color.WHITE);
                // 添加到操作类中
                menu.addMenuItem(openItem);

                // 添加删除操作选项
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());

                // 设置背景颜色
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // 设置宽度
                deleteItem.setWidth(dp2px(90));
                // 设置操作图标
                deleteItem.setIcon(R.mipmap.ic_delete);
                // 添加到操作类中
                menu.addMenuItem(deleteItem);

            }
        };

        // 设置创建菜单接口引用
        mListView.setMenuCreator(creator);

        // 设置点击菜单处理逻辑接口引用
        mListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            // 重写接口方法(回调方法)
            @Override
            public void onMenuItemClick(int position, SwipeMenu menu, int index) {
                ApplicationInfo info = mAppList.get(position);

                switch (index) {
                    case 0:
                        // 打开app操作
                        open(info);
                        break;
                    case 1:
                        // 删除app目录操作
                        mAppList.remove(position);
                        mAdapter.notifyDataSetChanged();
                        break;
                }
            }
        });

        mListView.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {

            @Override
            public void onSwipeStart(int position) {
                // 操作菜单滑动前的操作
            }

            @Override
            public void onSwipeEnd(int position) {
                // 操作菜单滑动后的操作
            }
        });
        // 添加长点击事件
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtils.showToast(position + "长点击");
                return false;
            }
        });

    }


    /**
     * 打开app操作
     *
     * @param info
     */
    private void open(ApplicationInfo info) {

        Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
        resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        resolveIntent.setPackage(info.packageName);
        // 配备适合的应用
        List<ResolveInfo> resolveInfoList = getPackageManager()
                .queryIntentActivities(resolveIntent, 0);

        if (resolveInfoList != null && resolveInfoList.size() > 0) {
            // 获得第一应用的启动项
            ResolveInfo resolveInfo = resolveInfoList.get(0);
            String activityPackageName = resolveInfo.activityInfo.packageName;
            String className = resolveInfo.activityInfo.name;
            // 组装启动的intent
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            ComponentName componentName = new ComponentName(activityPackageName, className);
            intent.setComponent(componentName);
            startActivity(intent);
        }

    }


    /**
     * app应用适配器
     */
    private class AppAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mAppList.size();
        }

        @Override
        public ApplicationInfo getItem(int position) {
            return mAppList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = View.inflate(getApplicationContext(),
                        R.layout.item_swipemenu_list, null);
                new ViewHolder(convertView);
            }
            ViewHolder viewHolder = (ViewHolder) convertView.getTag();
            ApplicationInfo item = getItem(position);
            // 获得应用的图标
            viewHolder.iv_icon.setImageDrawable(item
                    .loadIcon(getPackageManager()));
            viewHolder.tv_name.setText(item.loadLabel(getPackageManager()));
            return convertView;
        }
    }

    class ViewHolder {
        private ImageView iv_icon;
        private TextView tv_name;

        public ViewHolder(View convertView) {
            iv_icon = (ImageView) convertView.findViewById(R.id.iv_icon);
            tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(this);
        }
    }

    /**
     * dp转px
     *
     * @param dp
     * @return
     */
    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }


}
