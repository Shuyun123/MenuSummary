package net.anumbrella.menusummary.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import net.anumbrella.menusummary.R;
import net.anumbrella.menusummary.utils.ToastUtils;
import net.anumbrella.menusummary.widget.MyGridView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * author：anumbrella
 * Date:17/2/23 下午3:38
 */
public class ExpandableGridAdapter extends BaseExpandableListAdapter implements AdapterView.OnItemClickListener {


    /**
     * 二级菜单选项的显示内容
     */
    private String[][] text_array;

    /**
     * 一级菜单选项的显示内容
     */
    private List<Map<String, Object>> list;

    /**
     * 自定义的视图
     */
    private MyGridView myGridView;

    private Context context;

    private List<String> child_text;


    public ExpandableGridAdapter(Context context, List<Map<String, Object>> list, String[][] array) {
        this.context = context;
        this.list = list;
        this.text_array = array;
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
        // 这里返回1是为了让ExpandableListView只显示一个ChildView，否则在展开
        // ExpandableListView时会显示和ChildCount数量相同的GridView
        return 1;
    }

    /**
     * 获取一级菜单的具体的选项的内容
     *
     * @param groupPosition
     * @return
     */
    @Override
    public Object getGroup(int groupPosition) {
        return list.get(groupPosition).get("txt");
    }

    /**
     * 获取一级菜单下第二级菜单的具体的选项的内容
     *
     * @return
     */
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return text_array[groupPosition][childPosition];
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
     * 获取二级菜单的选项的id
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
     * 指定位置相应的组视图(指定视图相应的id)
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
        convertView = convertView.inflate(context, R.layout.item_expandablegrid_layout, null);
        TextView textView = (TextView) convertView.findViewById(R.id.item_expandablegrid_text);

        if (isExpanded) {
            textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.group_down, 0);
        } else {
            textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.group_up, 0);
        }

        textView.setText(list.get(groupPosition).get("txt").toString());
        return convertView;
    }

    /**
     * 对一级菜单下面的二级菜单进行设置
     *
     * @param i1
     * @param b
     * @param viewGroup
     * @return
     */
    @Override
    public View getChildView(int groupPosition, int i1, boolean b, View convertView, ViewGroup viewGroup) {
        convertView = convertView.inflate(context,
                R.layout.item_expandablegrid_child_layout, null);
        myGridView = (MyGridView) convertView.findViewById(R.id.MyGridView);

        int size = text_array[groupPosition].length;
        child_text = new ArrayList<String>();
        for (int i = 0; i < size; i++) {
            child_text.add(text_array[groupPosition][i]);
        }

        myGridView.setAdapter(new ExpandableGridTextAdapter(child_text, context));
        myGridView.setOnItemClickListener(this);

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


    /**
     * 二级菜单选项点击事件
     *
     * @param adapterView
     * @param view
     * @param l
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        ToastUtils.showToast("你点击的是" + child_text.get(position));
    }
}
