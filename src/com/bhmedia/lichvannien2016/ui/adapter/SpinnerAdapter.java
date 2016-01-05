package com.bhmedia.lichvannien2016.ui.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bhmedia.lichvannien2016.R;
import com.bhmedia.lichvannien2016.ui.model.SpinnerObject;

/**
 * Created by Van on 3/11/2015.
 */
public class SpinnerAdapter extends BaseAdapter {
    List<SpinnerObject> list;
    LayoutInflater inflater;
    Context context;

    public SpinnerAdapter(Context context, ArrayList<SpinnerObject> list) {
        this.context = context;
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
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.row_item_spinner, parent, false);
        }
        TextView tv_time_nhac_nho = (TextView) convertView.findViewById(R.id.tv_time_nhac_nho);
        tv_time_nhac_nho.setText(list.get(position).getName());
        return convertView;
    }
}
