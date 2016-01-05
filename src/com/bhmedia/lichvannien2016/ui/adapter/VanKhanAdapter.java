package com.bhmedia.lichvannien2016.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import com.bhmedia.lichvannien2016.R;
import com.bhmedia.lichvannien2016.ui.model.VanKhan;

/**
 * Created by Van on 1/15/2015.
 */
public class VanKhanAdapter extends BaseAdapter {
    Context context;
    ArrayList<VanKhan> list;
    LayoutInflater inflater;

    static class ViewHolder {
        TextView tv_row_list_vanKhan;
    }

    public VanKhanAdapter(Context mcontext, ArrayList<VanKhan> list) {
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
            vi = inflater.inflate(R.layout.item_listview_vankhan, parent, false);
            holder = new ViewHolder();
            holder.tv_row_list_vanKhan = (TextView) vi.findViewById(R.id.tv_row_item_van_khan);
            vi.setTag(holder);
        } else {
            holder = (ViewHolder) vi.getTag();
        }
        holder.tv_row_list_vanKhan.setText(list.get(position).getTitle());
        return vi;
    }
}
