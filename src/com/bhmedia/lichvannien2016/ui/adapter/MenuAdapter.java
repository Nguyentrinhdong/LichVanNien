package com.bhmedia.lichvannien2016.ui.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bhmedia.lichvannien2016.R;
import com.bhmedia.lichvannien2016.ui.model.Menu;

/**
 * Created by Van on 3/10/2015.
 */
public class MenuAdapter extends BaseAdapter {
    Context context;
    List<Menu> list;
    private int SIZE;
    LayoutInflater inflater;

    public MenuAdapter(Context context, ArrayList<Menu> list) {
        this.context = context;
        this.list = list;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        SIZE = list.size();
    }

    @Override
    public int getCount() {
        return SIZE;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.menu, parent, false);
        }
        TextView tv_menu = (TextView) convertView.findViewById(R.id.tv_menu);
        //ImageView iv_menu = (ImageView) convertView.findViewById(R.id.iv_menu);

        tv_menu.setText(list.get(position).getNameMenu());
        tv_menu.setCompoundDrawablesWithIntrinsicBounds(list.get(position).getImgMenu(), 0, 0, 0);
        //iv_menu.setImageResource(list.get(position).getImgMenu());
        return convertView;
    }
}
