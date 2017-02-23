package net.anumbrella.menusummary.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import net.anumbrella.menusummary.R;

import java.util.List;
import java.util.Map;

/**
 * author：anumbrella
 * Date:17/2/23 下午3:09
 * 扩展视图的适配器
 */

public class ExpandableListAdapter extends BaseExpandableListAdapter {


    private String[][] text_array;

    private Context context;

    private List<Map<String, Object>> list;

    public ExpandableListAdapter(Context context, List<Map<String, Object>> list, String[][] array) {
        this.context = context;
        // 子菜单的选项的数据
        this.text_array = array;
        // 父菜单的选项的数据
        this.list = list;
    }

    /**
     * 获取第一级菜单的选的总数目
     *
     * @return
     */
    @Override
    public int getGroupCount() {
        return list.size();
    }

    /**
     * 获取一级菜单下面二级菜单的选项的总数目
     *
     * @param i
     * @return
     */
    @Override
    public int getChildrenCount(int i) {
        return text_array[i].length;

    }

    /**
     * 获取一级菜单的具体的选项的内容
     *
     * @param i
     * @return
     */
    @Override
    public Object getGroup(int i) {
        return list.get(i).get("txt");
    }

    /**
     * 获取一级菜单下第二级菜单的具体的选项的内容
     *
     * @param i
     * @param i1
     * @return
     */
    @Override
    public Object getChild(int i, int i1) {
        return text_array[i][i1];

    }

    /**
     * 获取第一级菜单的选项的id
     *
     * @param i
     * @return
     */
    @Override
    public long getGroupId(int i) {
        return i;
    }

    /**
     * 获取一级菜单下二级菜单选项的id
     *
     * @param i
     * @param i1
     * @return
     */
    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    /**
     * 指定位置是否有相应的组视图(指定视图相应的id)
     *
     * @return
     */
    @Override
    public boolean hasStableIds() {
        return false;
    }

    /**
     * 对一级菜单的标签的内容进行设置
     *
     * @param viewGroup
     * @return
     */
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup viewGroup) {
        convertView = convertView.inflate(context, R.layout.item_expandablelist_layout, null);
        ImageView imageView = (ImageView) convertView
                .findViewById(R.id.img_icon);
        TextView textView = (TextView) convertView
                .findViewById(R.id.expandablelist_item_txt);
        // 是否可以点击扩展开来,设置字体显示的位置
        if (isExpanded) {
            textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.group_down, 0);
        } else {
            textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.group_up, 0);
        }

        // 设置图片和字体的内容
        imageView.setImageResource(Integer.parseInt(list.get(groupPosition).get("img").toString()));
        textView.setText(list.get(groupPosition).get("txt").toString());

        return convertView;
    }

    /**
     * 设置一级菜单下二级菜单的内容
     *
     * @param b
     * @param viewGroup
     * @return
     */
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean b, View convertView, ViewGroup viewGroup) {
        convertView = convertView.inflate(context,
                R.layout.item_expandablelist_child_layout, null);
        TextView textView = (TextView) convertView.findViewById(R.id.expandablelist_child_item);
        textView.setText(text_array[groupPosition][childPosition]);
        return convertView;
    }

    /**
     * 当选择子节点的时候，调用该方法
     *
     * @param i
     * @param i1
     * @return
     */
    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
