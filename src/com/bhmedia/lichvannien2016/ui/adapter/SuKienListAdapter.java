package com.bhmedia.lichvannien2016.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import com.bhmedia.lichvannien2016.R;
import com.bhmedia.lichvannien2016.ui.fragment.SuKienFragment;
import com.bhmedia.lichvannien2016.ui.model.Sukien;


/**
 * Created by Van on 1/29/2015.
 */
public class SuKienListAdapter extends BaseAdapter {
    Context context;
    ArrayList<Sukien> list;
    LayoutInflater inflater;
    SuKienFragment.RemoveListener removeListener;

    static class ViewHolder {
        TextView  tv_item_tenSuKien;
        ImageView iv_xoaSuKien;
    }

    public SuKienListAdapter(Context context, ArrayList<Sukien> list, SuKienFragment.RemoveListener removeListener) {
        this.context = context;
        this.list = list;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.removeListener = removeListener;
    }

    public void removeItems(int postion) {
        list.remove(postion);
        notifyDataSetChanged();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        View vi = convertView;             //trying to reuse a recycled view
        ViewHolder holder = null;
        if (convertView == null) {
            vi = inflater.inflate(R.layout.item_listview_sukien, parent, false);
            holder = new ViewHolder();
            holder.tv_item_tenSuKien = (TextView) vi.findViewById(R.id.tv_item_tenSuKien);
            holder.iv_xoaSuKien = (ImageView) vi.findViewById(R.id.iv_xoaSuKien);
            vi.setTag(holder);
        } else {
            holder = (ViewHolder) vi.getTag();

        }
        holder.tv_item_tenSuKien.setText(list.get(position).getSukien());
        holder.iv_xoaSuKien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeListener.showDialog(position, list.get(position).getId());
            }
        });
        return vi;
    }
}
