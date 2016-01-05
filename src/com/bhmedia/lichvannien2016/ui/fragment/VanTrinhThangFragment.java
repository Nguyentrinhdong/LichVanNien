package com.bhmedia.lichvannien2016.ui.fragment;

import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bhmedia.lichvannien2016.R;
import com.bhmedia.lichvannien2016.ui.activity.KetQuaVanTrinhThangActivity;
import com.bhmedia.lichvannien2016.ui.activity.MyApp;
import com.bhmedia.lichvannien2016.ui.utility.CalendarUtil;
import com.bhmedia.lichvannien2016.ui.wheel.ArrayWheelAdapter;
import com.bhmedia.lichvannien2016.ui.wheel.NumericWheelAdapter;
import com.bhmedia.lichvannien2016.ui.wheel.OnWheelChangedListener;
import com.bhmedia.lichvannien2016.ui.wheel.OnWheelScrollListener;
import com.bhmedia.lichvannien2016.ui.wheel.WheelView;
import com.google.android.gms.analytics.HitBuilders;


/**
 * Created by Van on 1/14/2015.
 */
public class VanTrinhThangFragment extends BaseFragment {
	Context context;
	WheelView day, month, year, thang_amlich;
	private int NoOfYear = 115;
	private int YesOfYear = 35;
	String months[] = new String[] { "1", "2", "3", "4", "5", "6", "7", "8",
			"9", "10", "11", "12" };
	String amlich[] = new String[] { "Tháng Giêng", "Tháng Hai", "Tháng Ba",
			"Tháng Tư", "Tháng Năm", "Tháng Sáu", "Tháng Bảy", "Tháng Tám",
			"Tháng Chín", "Tháng Mười", "Tháng Mười Một", "Tháng Chạp" };
	// Wheel scrolled flag
	private boolean wheelScrolled = false;
	int posThangAmLich;
	ImageView iv_ketqua_vantrinhthang;
	String years[], days[];
	DateArrayAdapter arrayAdapterDay, arrayAdapterMonth, arrayAdapterYear,
			arrayAdapterMonthAL;
	String valueYear, valueMonth, valueDay;
	TextView tv_current_year_vtt;

	// private ImageView btnPreYear, btnNextYear;

	@Override
	protected int getLayoutId() {

		return R.layout.layout_fragment_vantrinhthang;
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

		MyApp.tracker().setScreenName("Van Trinh Thang Android");
		MyApp.tracker().send(new HitBuilders.AppViewBuilder().build());

		iv_ketqua_vantrinhthang = (ImageView) findViewById(R.id.iv_ketqua_vantrinhthang);
		iv_ketqua_vantrinhthang.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
//				if (tv_current_year_vtt.getText().equals("2015")) {
//					tv_current_year_vtt.setText("2014");
//				}
				Bundle mBundle = new Bundle();
				if (valueDay != null && valueMonth != null && valueYear != null) {
					wheelScrolled = false;
					mBundle.putString("NAM", valueYear);
					mBundle.putString("THANG", valueMonth);
					mBundle.putString("NGAY", valueDay);

				} else {
					Date dateCurr = new Date();
					Calendar calendar1 = Calendar.getInstance();
					calendar1.setTime(dateCurr);
					mBundle.putString("NAM", calendar1.get(Calendar.YEAR) + "");
					mBundle.putString("THANG",
							(calendar1.get(Calendar.MONTH) + 1) + "");
					mBundle.putString("NGAY",
							calendar1.get(Calendar.DAY_OF_MONTH) + "");
				}

				mBundle.putInt("THANG_AM", posThangAmLich);
				mBundle.putString("NAM_XEM", tv_current_year_vtt.getText()
						.toString());

				Intent intent = new Intent(getActivity(),
						KetQuaVanTrinhThangActivity.class);
				intent.putExtra("Bundle", mBundle);
				startActivity(intent);
			}
		});
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, 6);
		calendar.set(Calendar.YEAR, 1987);
		calendar.set(Calendar.DAY_OF_MONTH, 24);
		day = (WheelView) findViewById(R.id.day_vtt);
		month = (WheelView) findViewById(R.id.month_vtt);
		year = (WheelView) findViewById(R.id.year_vtt);
		thang_amlich = (WheelView) findViewById(R.id.thangamlich_vtt);

		// month
		int curMonth = calendar.get(Calendar.MONTH);
		arrayAdapterMonth = new DateArrayAdapter(context, months, curMonth);
		month.setViewAdapter(arrayAdapterMonth);
		month.setVisibleItems(2);
		month.setCurrentItem(curMonth);
		month.addChangingListener(changedListener);
		month.addScrollingListener(scrolledListener);

		Calendar cal = Calendar.getInstance();
		int currDay = calendar.get(Calendar.DAY_OF_MONTH);
		// year
		int curYear = calendar.get(Calendar.YEAR);
		int Year = calendar.get(Calendar.YEAR);
		years = getListString(Year - NoOfYear, Year + YesOfYear);
		arrayAdapterYear = new DateArrayAdapter(context, years, NoOfYear);
		year.setViewAdapter(arrayAdapterYear);
		year.setVisibleItems(2);
		year.setCurrentItem(curYear - (Year - NoOfYear));
		year.addChangingListener(changedListener);
		year.addScrollingListener(scrolledListener);

		// thang am lich
		CalendarUtil calendarUtil = new CalendarUtil();
		int[] convert = calendarUtil.convertSolar2Lunar(currDay, curMonth,
				curYear, 7);
		// int currMonthAL
		arrayAdapterMonthAL = new DateArrayAdapter(context, amlich, convert[1]);
		thang_amlich.setViewAdapter(arrayAdapterMonthAL);
		thang_amlich.setVisibleItems(2);
		thang_amlich.setCurrentItem(convert[1]);
		thang_amlich.addChangingListener(changedListener);
		thang_amlich.addScrollingListener(scrolledListener);

		// day
		/*
		 * day.setViewAdapter(new ArrayWheelAdapter(context, wheelMenu1));
		 * day.setVisibleItems(2);
		 */
		updateDays(year, month, day);
		day.setCurrentItem(calendar.get(Calendar.DAY_OF_MONTH) - 1);
		day.addChangingListener(changedListener);
		day.addScrollingListener(scrolledListener);

		tv_current_year_vtt = (TextView) findViewById(R.id.tv_current_year_vtt);

		// btnPreYear = (ImageView) findViewById(R.id.btn_pre_year);
		// btnNextYear = (ImageView) findViewById(R.id.btn_next_year);

		updateStatus();

		// btnPreYear.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View arg0) {
		// // TODO Auto-generated method stub
		// if(tv_current_year_vtt.getText().equals("2015")){
		// tv_current_year_vtt.setText("2014");
		// btnPreYear.setVisibility(View.INVISIBLE);
		// btnNextYear.setVisibility(View.VISIBLE);
		// }
		// }
		// });

		// btnNextYear.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View arg0) {
		// // TODO Auto-generated method stub
		// if(tv_current_year_vtt.getText().equals("2014")){
		// tv_current_year_vtt.setText("2015");
		// btnNextYear.setVisibility(View.INVISIBLE);
		// btnPreYear.setVisibility(View.VISIBLE);
		// }
		// }
		// });
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
			arrayAdapterDay.changeColorCurrValue(day.getCurrentItem());
			arrayAdapterMonth.changeColorCurrValue(month.getCurrentItem());
			arrayAdapterYear.changeColorCurrValue(year.getCurrentItem());
			arrayAdapterMonthAL.changeColorCurrValue(thang_amlich
					.getCurrentItem());
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
				arrayAdapterDay.changeColorCurrValue(day.getCurrentItem());
				arrayAdapterMonth.changeColorCurrValue(month.getCurrentItem());
				arrayAdapterYear.changeColorCurrValue(year.getCurrentItem());
				arrayAdapterMonthAL.changeColorCurrValue(thang_amlich
						.getCurrentItem());
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
		calendar.set(Calendar.MONTH, 6);
		calendar.set(Calendar.YEAR, 1987);
		calendar.set(Calendar.DAY_OF_MONTH, 24);
		calendar.set(Calendar.YEAR,
				calendar.get(Calendar.YEAR)
						+ (year.getCurrentItem() - NoOfYear));
		calendar.set(Calendar.MONTH, month.getCurrentItem());

		int maxDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		days = getListString(1, maxDays);
		arrayAdapterDay = new DateArrayAdapter(context, days,
				calendar.get(Calendar.DAY_OF_MONTH) - 1);
		day.setViewAdapter(arrayAdapterDay);
		int curDay = Math.min(maxDays, day.getCurrentItem() + 1);
		day.setCurrentItem(curDay - 1, true);
		calendar.set(Calendar.DAY_OF_MONTH, curDay);

		return calendar;

	}

	private class DateNumericAdapter extends NumericWheelAdapter {
		int currentItem;
		int currentValue;

		public DateNumericAdapter(Context context, int minValue, int maxValue,
				int current) {
			super(context, minValue, maxValue);
			this.currentValue = current;
			setTextSize(14);
		}

		@Override
		protected void configureTextView(TextView view) {
			super.configureTextView(view);
			if (currentItem == currentValue) {
				// view.setTextColor(Color.parseColor("#C4AC5C"));
				view.setTextColor(Color.parseColor("#1C9D51"));
			} else {
				view.setTextColor(Color.BLACK);
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
			setTextSize(14);
		}

		public void changeColorCurrValue(int value) {
			currentValue = value;
			notifyDataChangedEvent();
		}

		@Override
		protected void configureTextView(TextView view) {
			super.configureTextView(view);
			if (currentItem == currentValue) {
				// view.setTextColor(Color.parseColor("#C4AC5C"));
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
		int positionYear = year.getCurrentItem();
		valueYear = years[positionYear];

		int positionMonth = month.getCurrentItem();
		valueMonth = months[positionMonth];

		int positionDay = day.getCurrentItem();
		valueDay = days[positionDay];

		int positionThangAmLich = thang_amlich.getCurrentItem();
		posThangAmLich = positionThangAmLich;
	}

	private String[] getListString(int minValue, int maxValue) {
		int sizeDate = maxValue - minValue;
		String[] lists = new String[sizeDate];
		for (int i = 0; i < sizeDate; i++) {
			lists[i] = String.valueOf(minValue + i);
		}
		return lists;
	}
}
