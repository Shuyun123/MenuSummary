package net.anumbrella.menusummary.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import net.anumbrella.menusummary.R;
import net.anumbrella.menusummary.adapter.GridViewAdapter;
import net.anumbrella.menusummary.bean.Type;
import net.anumbrella.menusummary.config.Config;
import net.anumbrella.menusummary.utils.ToastUtils;

import java.util.ArrayList;

/**
 * author：anumbrella
 * Date:17/2/23 下午2:27
 */

public class TypeFragment extends Fragment {


    private ArrayList<Type> listType;
    /**
     * widget网格view
     */
    private GridView gridView;
    /**
     * 网格适配器
     */
    private GridViewAdapter adapter;

    /**
     * 右侧栏目中的子选项
     */
    private Type type;

    /**
     * 子选项名称
     */
    private String typeName;

    /**
     * 图片
     */
    private int icon;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detaillist_layout, null);

        gridView = (GridView) view.findViewById(R.id.GridViewList);

        int index = getArguments().getInt("index");

        typeName = Config.list[index];
        icon = Config.iconList[index];

        ((TextView) view.findViewById(R.id.TypeName)).setText(typeName);

        // 为listType装载数据
        setTypeList();

        adapter = new GridViewAdapter(getActivity(), listType);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String typeName = listType.get(position).getTypeName();
                ToastUtils.showToast("你点击的是" + typeName);

            }
        });
        return view;
    }

    /**
     * 给listType设定数据
     */
    private void setTypeList() {
        listType = new ArrayList<Type>();
        // 这里可以根据数据设定要填充的资源
        for (int i = 0; i < 23; i++) {
            type = new Type(i, icon, typeName + i);
            listType.add(type);
        }

    }


}
