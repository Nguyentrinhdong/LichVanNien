package com.bhmedia.lichvannien2016.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import com.bhmedia.lichvannien2016.R;

/**
 * Created by Van on 1/23/2015.
 */
public class VanKhanAdapter1 extends BaseAdapter {
    Context context;
    ArrayList<String> list;
    LayoutInflater inflater;
    static class ViewHolder{
        TextView tv_row_list_vanKhan;
    }
    public VanKhanAdapter1(Context mcontext,ArrayList<String> list){
        context = mcontext;
        this.list = list;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;             //trying to reuse a recycled view
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_listview_vankhan, parent,false);
            holder = new ViewHolder();
            holder.tv_row_list_vanKhan = (TextView) convertView.findViewById(R.id.tv_row_item_van_khan);
            vi.setTag(holder);
        }else {
            holder = (ViewHolder) vi.getTag();
        }
        holder.tv_row_list_vanKhan.setText(list.get(position));
        return vi;
    }
}
