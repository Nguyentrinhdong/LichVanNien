package com.bhmedia.lichvannien2016.ui.fragment;

import java.util.Calendar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bhmedia.lichvannien2016.R;
import com.bhmedia.lichvannien2016.ui.activity.ChiTietNgayActivity;
import com.bhmedia.lichvannien2016.ui.activity.KetQuaNSHActivity;
import com.bhmedia.lichvannien2016.ui.activity.MainActivity;
import com.bhmedia.lichvannien2016.ui.activity.MyApp;
import com.bhmedia.lichvannien2016.ui.activity.ThemSuKienActivity;
import com.bhmedia.lichvannien2016.ui.utility.CalendarUtil;
import com.bhmedia.lichvannien2016.ui.utility.SmartLogUtility;
import com.bhmedia.lichvannien2016.ui.wheel.ArrayWheelAdapter;
import com.bhmedia.lichvannien2016.ui.wheel.NumericWheelAdapter;
import com.bhmedia.lichvannien2016.ui.wheel.OnWheelChangedListener;
import com.bhmedia.lichvannien2016.ui.wheel.OnWheelScrollListener;
import com.bhmedia.lichvannien2016.ui.wheel.WheelView;
import com.google.android.gms.analytics.HitBuilders;

/**
 * Created by Van on 1/14/2015.
 */
public class NhipSinhHocFragment extends BaseFragment implements
		View.OnClickListener {
	Context context;
	private int NoOfYear = 115;
	private int YesOfYear = 85;
	String months[] = new String[] { "1", "2", "3", "4", "5", "6", "7", "8",
			"9", "10", "11", "12" };
	// Wheel scrolled flag
	private boolean wheelScrolled = false;

	WheelView day, month, year;

	String[] years;
	String[] days;
	View ll_chitietngay_doingay;

	Calendar calendar;

	int leap = 0;
	public static String valueMonthBegin, valueDayBegin, valueYearBegin;
	boolean flag = true;
	int curMonth, curYear, Year;
	DateArrayAdapter arrayAdapterDay, arrayAdapterMonth, arrayAdapterYear;
	int DAY = 0, MONTH = 1, YEAR = 2;

	@Override
	protected int getLayoutId() {

		return R.layout.layout_fragment_nsh;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		context = activity.getApplicationContext();
	}

	@Override
	protected void onInitializeView(Bundle savedInstanceState) {
		super.onInitializeView(savedInstanceState);

		MyApp.tracker().setScreenName("Doi Ngay Android");
		MyApp.tracker().send(new HitBuilders.AppViewBuilder().build());
		ll_chitietngay_doingay = (View) findViewById(R.id.ll_chitietngay_doingay);
		ll_chitietngay_doingay.setOnClickListener(this);

		calendar = Calendar.getInstance();
		// calendar.set(Calendar.MONTH,6);
		// calendar.set(Calendar.YEAR,1987);
		// calendar.set(Calendar.DAY_OF_MONTH,24);

		day = (WheelView) findViewById(R.id.day);
		month = (WheelView) findViewById(R.id.month);
		year = (WheelView) findViewById(R.id.year);

		// month
		curMonth = calendar.get(Calendar.MONTH);
		arrayAdapterMonth = new DateArrayAdapter(context, months, curMonth);
		month.setViewAdapter(arrayAdapterMonth);
		month.setVisibleItems(2);
		month.setCurrentItem(curMonth);//
		month.addChangingListener(changedListener);
		month.addScrollingListener(scrolledListener);

		Calendar cal = Calendar.getInstance();
		// year
		curYear = calendar.get(Calendar.YEAR);
		Year = cal.get(Calendar.YEAR);

		years = getListString(Year - NoOfYear, Year + YesOfYear);
		arrayAdapterYear = new DateArrayAdapter(context, years, NoOfYear);
		year.setViewAdapter(arrayAdapterYear);
		year.setVisibleItems(2);
		year.setCurrentItem(curYear - (Year - NoOfYear));
		year.addChangingListener(changedListener);
		year.addScrollingListener(scrolledListener);

		// day
		/*
		 * day.setViewAdapter(new ArrayWheelAdapter(context, wheelMenu1));
		 * day.setVisibleItems(2);
		 */
		updateDays(year, month, day);
		day.setCurrentItem(calendar.get(Calendar.DAY_OF_MONTH) - 1);
		day.addChangingListener(changedListener);
		day.addScrollingListener(scrolledListener);

		Calendar c = Calendar.getInstance();
		valueDayBegin = c.get(Calendar.DAY_OF_MONTH) + "";
		valueMonthBegin = (c.get(Calendar.MONTH) + 1) + "";
		valueYearBegin = c.get(Calendar.YEAR) + "";
		CalendarUtil calendarUtil = new CalendarUtil();
		int[] convert = calendarUtil.convertSolar2Lunar(
				Integer.parseInt(valueDayBegin),
				Integer.parseInt(valueMonthBegin),
				Integer.parseInt(valueYearBegin), 7);
		calendar.set(Calendar.DAY_OF_MONTH, convert[0]);
		calendar.set(Calendar.MONTH, convert[1] - 1);
		calendar.set(Calendar.YEAR, convert[2]);

	}

	// Wheel scrolled listener
	OnWheelScrollListener scrolledListener = new OnWheelScrollListener() {
		public void onScrollStarts(WheelView wheel) {
			wheelScrolled = true;
		}

		public void onScrollEnds(WheelView wheel) {
			wheelScrolled = false;
			updateStatus();
			updateDayOfMonth();
			flagAmDuong = true;
			arrayAdapterDay.changeColorCurrValue(day.getCurrentItem());
			arrayAdapterMonth.changeColorCurrValue(month.getCurrentItem());
			arrayAdapterYear.changeColorCurrValue(year.getCurrentItem());
		}

		@Override
		public void onScrollingStarted(WheelView wheel) {

		}

		@Override
		public void onScrollingFinished(WheelView wheel) {

		}
	};
	// Wheel changed listener
	private final OnWheelChangedListener changedListener = new OnWheelChangedListener() {
		public void onChanged(WheelView wheel, int oldValue, int newValue) {
			if (!wheelScrolled) {
				updateStatus();
				updateDayOfMonth();
				flagAmDuong = true;
				arrayAdapterDay.changeColorCurrValue(day.getCurrentItem());
				arrayAdapterMonth.changeColorCurrValue(month.getCurrentItem());
				arrayAdapterYear.changeColorCurrValue(year.getCurrentItem());
			}
		}
	};

	private WheelView getWheel(int id) {
		return (WheelView) findViewById(id);
	}

	private int getWheelValue(int id) {
		return getWheel(id).getCurrentItem();
	}

	Calendar updateDays(WheelView year, WheelView month, WheelView day) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR,
				calendar.get(Calendar.YEAR)
						+ (year.getCurrentItem() - NoOfYear));
		calendar.set(Calendar.MONTH, month.getCurrentItem());

		int maxDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		days = getListString(1, maxDays + 1);
		arrayAdapterDay = new DateArrayAdapter(context, days,
				calendar.get(Calendar.DAY_OF_MONTH) - 1);
		day.setViewAdapter(arrayAdapterDay);

		int curDay = Math.min(maxDays, day.getCurrentItem() + 1);
		day.setCurrentItem(curDay - 1, true);
		calendar.set(Calendar.DAY_OF_MONTH, curDay);

		return calendar;
	}

	public void updateDaysAL(int totalDay) {
		days = getListString(1, totalDay + 1);
		arrayAdapterDay = new DateArrayAdapter(context, days, totalDay);
		day.setViewAdapter(arrayAdapterDay);
	}

	boolean flagAmDuong = true;

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ll_chitietngay_doingay:
			// SmartLogUtility.logD("gt can biet la:"+getHtml());
			Intent it = new Intent(getActivity(), KetQuaNSHActivity.class);
			startActivity(it);
			break;
		}
	}

	private class DateNumericAdapter extends NumericWheelAdapter {
		int currentItem;
		int currentValue;

		public DateNumericAdapter(Context context, int minValue, int maxValue,
				int current) {
			super(context, minValue, maxValue);
			this.currentValue = current;
			setTextSize(15);
		}

		@Override
		protected void configureTextView(TextView view) {
			super.configureTextView(view);
			if (currentItem == currentValue) {
				// view.setTextColor(Color.parseColor("#C4AC5C"));
				view.setTextColor(Color.parseColor("#1C9D51"));
			}
			view.setTypeface(null, Typeface.NORMAL);
		}

		@Override
		public View getItem(int index, View cachedView, ViewGroup parent) {
			currentItem = index;
			return super.getItem(index, cachedView, parent);
		}
	}

	private class DateArrayAdapter extends ArrayWheelAdapter<String> {
		int currentItem;
		int currentValue;

		public DateArrayAdapter(Context context, String[] items, int current) {
			super(context, items);
			this.currentValue = current;
			setTextSize(15);
		}

		public void changeColorCurrValue(int value) {
			currentValue = value;
			notifyDataChangedEvent();
		}

		@Override
		protected void configureTextView(TextView view) {
			super.configureTextView(view);
			if (currentItem == currentValue) {
				view.setTextColor(Color.parseColor("#1C9D51"));
			} else {
				view.setTextColor(Color.BLACK);
			}
			view.setTypeface(null, Typeface.NORMAL);
			view.setBackgroundColor(Color.WHITE);
		}

		@Override
		public View getItem(int index, View cachedView, ViewGroup parent) {
			currentItem = index;
			return super.getItem(index, cachedView, parent);
		}
	}

	private final int[] daysOfMonth = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31,
			30, 31 };

	private void updateDayOfMonth() {
		int positionMonth = month.getCurrentItem();
		int totalDayOfMonth = daysOfMonth[positionMonth];
		CalendarUtil calendarUtil = new CalendarUtil();
		if (calendarUtil
				.checkLeap(Integer.parseInt(years[year.getCurrentItem()]))) {
			if (positionMonth == 1) {
				totalDayOfMonth++;
			}
		}
		int current_day_select = day.getCurrentItem();
		if ((current_day_select + 1) > totalDayOfMonth) {
			current_day_select = totalDayOfMonth - 1;
		}
		day.setCurrentItem(current_day_select);
		updateDays(year, month, day);
	}

	private void updateStatus() {
		int positionMonthBegin = month.getCurrentItem();
		valueMonthBegin = months[positionMonthBegin];

		int positionDayBegin = day.getCurrentItem();
		valueDayBegin = days[positionDayBegin];

		int positionYearBatDau = year.getCurrentItem();
		valueYearBegin = years[positionYearBatDau];
		flagAmDuong = true;
	}

	private String[] getListString(int minValue, int maxValue) {
		int sizeDate = maxValue - minValue;
		String[] lists = new String[sizeDate];
		for (int i = 0; i < sizeDate; i++) {
			lists[i] = String.valueOf(minValue + i);
		}
		return lists;
	}

	// So ngay am lich trong thang
	public int getDaysMonth(int month, int year) {
		int lunarDay = 30;
		CalendarUtil calendarUtil = new CalendarUtil();
		int[] solars = calendarUtil.convertLunar2Solar(lunarDay, month, year,
				leap, 7);

		if (solars[DAY] <= 0)
			return 29;

		int[] lunars = calendarUtil.convertSolar2Lunar(solars[DAY],
				solars[MONTH], solars[YEAR], 7);

		if (lunars[DAY] == lunarDay && lunars[MONTH] == month
				&& lunars[YEAR] == year) {
			return lunarDay;
		} else
			return 29;
	}

}
