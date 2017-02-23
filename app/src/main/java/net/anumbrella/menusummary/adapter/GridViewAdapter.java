package net.anumbrella.menusummary.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import net.anumbrella.menusummary.R;
import net.anumbrella.menusummary.bean.Type;

import java.util.ArrayList;

/**
 * author：anumbrella
 * Date:17/2/23 下午2:32
 */

public class GridViewAdapter extends BaseAdapter {


    private Type type;

    private ArrayList<Type> listTypes;

    private Context context;

    private Hoder hoder;


    public GridViewAdapter(Context mContext, ArrayList<Type> list) {
        this.context = mContext;
        this.listTypes = list;
    }

    @Override
    public int getCount() {
        return listTypes.size();
    }

    @Override
    public Object getItem(int position) {
        return listTypes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            convertView = convertView.inflate(context, R.layout.item_gridview,
                    null);
            hoder = new Hoder(convertView);
            convertView.setTag(hoder);
        } else {
            hoder = (Hoder) convertView.getTag();
        }

        if (listTypes != null && listTypes.size() > 0) {
            type = listTypes.get(position);
            hoder.imageView.setBackgroundResource(type.getIcon());
            hoder.textView.setText(type.getTypeName());
        }

        return convertView;
    }


    private static class Hoder {
        private TextView textView;
        private ImageView imageView;
        public Hoder(View view) {
            textView = (TextView) view.findViewById(R.id.typeName_gridView);
            imageView = (ImageView) view.findViewById(R.id.icon_gridView);
        }

    }


}
