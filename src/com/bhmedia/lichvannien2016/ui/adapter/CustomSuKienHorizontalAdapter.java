package com.bhmedia.lichvannien2016.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;
import com.bhmedia.lichvannien2016.R;
import com.bhmedia.lichvannien2016.ui.database.NewDBSuKien;
import com.bhmedia.lichvannien2016.ui.fragment.SuKienFragment;
import com.bhmedia.lichvannien2016.ui.utility.CalendarUtil;


/**
 * Created by Van on 1/31/2015.
 */
@SuppressLint("NewApi")
public class CustomSuKienHorizontalAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    Context context;
    int size;
    private final int[] daysOfMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    int month, year;
    Calendar calendar;
    int currentSelect;
    private NewDBSuKien suKienHelper;

    static class ViewHolder {
        TextView tv_ngay, tv_thu;
        ImageView iv_sukien_SuKien;
    }

    public CustomSuKienHorizontalAdapter(Context context, int month, int year) {
        this.context = context;
        this.month = month;
        this.year = year;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.YEAR, year);
        size = daysOfMonth[month - 1];
    }

    @Override
    public int getCount() {
        return size;
    }

    @Override
    public Object getItem(int position) {
        return position + 1;
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
            vi = inflater.inflate(R.layout.item_list_horizontal_sukien, parent, false);
            holder = new ViewHolder();
            holder.tv_ngay = (TextView) vi.findViewById(R.id.tv_ngay);
            holder.tv_thu = (TextView) vi.findViewById(R.id.tv_thu);
            holder.iv_sukien_SuKien = (ImageView) vi.findViewById(R.id.iv_sukien_SuKien);
            vi.setTag(holder);
        } else {
            holder = (ViewHolder) vi.getTag();
        }
        suKienHelper = new NewDBSuKien(context);
        boolean result = suKienHelper.checkSuKienByTime(position + 1, month, year);
        suKienHelper.close();

        if (result) {
            holder.iv_sukien_SuKien.setVisibility(View.VISIBLE);
        } else {
            holder.iv_sukien_SuKien.setVisibility(View.GONE);
        }

        String weekDay = "";
        calendar.set(Calendar.DAY_OF_MONTH, position + 1);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        if (Calendar.MONDAY == dayOfWeek) {
            weekDay = "Mo";
        } else if (Calendar.TUESDAY == dayOfWeek) {
            weekDay = "Tu";
        } else if (Calendar.WEDNESDAY == dayOfWeek) {
            weekDay = "We";
        } else if (Calendar.THURSDAY == dayOfWeek) {
            weekDay = "Th";
        } else if (Calendar.FRIDAY == dayOfWeek) {
            weekDay = "Fr";
        } else if (Calendar.SATURDAY == dayOfWeek) {
            weekDay = "Sa";
        } else if (Calendar.SUNDAY == dayOfWeek) {
            weekDay = "Su";
        }
        holder.tv_thu.setText(weekDay);
        holder.tv_ngay.setText(position + 1 + "");
        CalendarUtil calendarUtil = new CalendarUtil();
        if (calendarUtil.checkLeap(year)) {
            if (month == 2) {
                holder.tv_ngay.setText((position + 1 + 1) + "");
            }
        }
        if (position == currentSelect) {
            holder.tv_ngay.setTextColor(Color.BLACK);
            vi.setBackgroundResource(R.drawable.month_selection2);
        } else {
            holder.tv_ngay.setTextColor(Color.BLACK);
            vi.setBackgroundColor(Color.WHITE);
            
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
				vi.setAlpha(0.7f);
			}
        }

        return vi;
    }

    public void changeBackground(int position) {
        currentSelect = position;
        notifyDataSetChanged();
    }

}
