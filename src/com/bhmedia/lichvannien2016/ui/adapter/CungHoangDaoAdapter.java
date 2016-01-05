package com.bhmedia.lichvannien2016.ui.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bhmedia.lichvannien2016.R;
import com.bhmedia.lichvannien2016.ui.activity.Itemlist;

public class CungHoangDaoAdapter extends BaseAdapter {
	
private static ArrayList<Itemlist> itemlsrrayList;
	
	private Integer[] imgid = {
			R.drawable.chuot,
			R.drawable.trau,
			R.drawable.meo,
			R.drawable.tho,
			R.drawable.rong,
			R.drawable.ran,
			R.drawable.ngua,
			R.drawable.de,
			R.drawable.khi,
			R.drawable.ga,
			R.drawable.cho,
			R.drawable.lon
			};
	
	private LayoutInflater l_Inflater;

	public CungHoangDaoAdapter(Context context, ArrayList<Itemlist> results) {
		itemlsrrayList = results;
		l_Inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return itemlsrrayList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return itemlsrrayList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if (convertView == null) {
			convertView = l_Inflater.inflate(R.layout.item_cunghoangdao, null);
			holder = new ViewHolder();
			holder.txt_itemName = (TextView) convertView.findViewById(R.id.tvten);
			holder.txt_itemDate = (TextView) convertView.findViewById(R.id.tvngay);
			holder.itemImage = (ImageView) convertView.findViewById(R.id.imagechd);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.txt_itemName.setText(itemlsrrayList.get(position).getName());
		holder.txt_itemDate.setText(itemlsrrayList.get(position).getItemDescription());
		holder.itemImage.setImageResource(imgid[itemlsrrayList.get(position).getImageNumber() - 1]);

		return convertView;

	}
	static class ViewHolder {
		TextView txt_itemName;
		TextView txt_itemDate;
		ImageView itemImage;
	}


}
