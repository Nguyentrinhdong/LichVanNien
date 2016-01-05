package com.bhmedia.lichvannien2016.ui.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bhmedia.lichvannien2016.R;
import com.bhmedia.lichvannien2016.ui.model.GiaiMong;

public class GiaiMongAdapter extends ArrayAdapter<GiaiMong> {

	Context mContext;
	ArrayList<GiaiMong> mLstGiaiMong = new ArrayList<GiaiMong>();

	public GiaiMongAdapter(Context context, int resource, List<GiaiMong> objects) {
		super(context, resource, objects);
		this.mContext = context;
		this.mLstGiaiMong = new ArrayList<GiaiMong>(objects);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = convertView;
		ViewHolder viewHolder;

		if (rowView == null) {
			LayoutInflater inflate = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			rowView = inflate.inflate(R.layout.item_giaimong, parent, false);
			// rowView = inflate.inflate(R.layout.item_theloaithu, null);
			viewHolder = new ViewHolder();
			viewHolder.tvgiaimong = (TextView) rowView.findViewById(R.id.tvgiaimong);
			rowView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		GiaiMong gm = mLstGiaiMong.get(position);
		viewHolder.tvgiaimong.setText(gm.getTitle());
		return rowView;
	}

	static class ViewHolder {
		TextView tvgiaimong;
	}
}
