package net.anumbrella.menusummary.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.anumbrella.menusummary.R;

import java.util.ArrayList;
import java.util.List;

/**
 * author：anumbrella
 * Date:17/2/23 下午3:51
 */

public class ExpandableGridTextAdapter extends BaseAdapter {

    private List<String> child_text_array = new ArrayList<String>();

    private Context context;

    public ExpandableGridTextAdapter(List<String> list, Context context) {
        this.context = context;
        this.child_text_array = list;
    }

    @Override
    public int getCount() {
        return child_text_array.size();
    }

    @Override
    public Object getItem(int position) {
        return child_text_array.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = null;
        if (convertView == null) {
            convertView = convertView.inflate(context, R.layout.item_giridview_text, null);
            textView = (TextView) convertView
                    .findViewById(R.id.item_expandablegridview_text);
            convertView.setTag(textView);
        } else {
            textView = (TextView) convertView.getTag();
        }
        textView.setText(child_text_array.get(position));
        return convertView;
    }
}
