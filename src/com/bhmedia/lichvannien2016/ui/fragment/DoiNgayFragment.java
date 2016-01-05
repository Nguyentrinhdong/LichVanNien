package com.bhmedia.lichvannien2016.ui.fragment;

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

import java.util.Calendar;
import java.util.Date;

import com.bhmedia.lichvannien2016.R;
import com.bhmedia.lichvannien2016.ui.activity.ChiTietNgayActivity;
import com.bhmedia.lichvannien2016.ui.activity.MainActivity;
import com.bhmedia.lichvannien2016.ui.activity.MyApp;
import com.bhmedia.lichvannien2016.ui.activity.ThemSuKienActivity;
import com.bhmedia.lichvannien2016.ui.model.CheckNumOfDayAL;
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
public class DoiNgayFragment extends BaseFragment implements
		View.OnClickListener {
	Context context;
	private int NoOfYear = 115;
	private int YesOfYear = 85;
	String months[] = new String[] { "1", "2", "3", "4", "5", "6", "7", "8",
			"9", "10", "11", "12" };
	// Wheel scrolled flag
	private boolean wheelScrolled = false;

	TextView tv_ngayduong, tv_thangduong, tv_namduong;
	WheelView day, month, year;

	TextView tv_duongLich, tv_amLich;
	ImageView iv_doiNgay;

	String[] years;
	String[] days;
	View ll_chitietngay_doingay, ll_themsukien_doingay;

	TextView tv_ngayam_doingay, tv_thangam_doingay, tv_namam_doingay;
	Calendar calendar;

	int leap = 0;
	String valueMonthBegin, valueDayBegin, valueYearBegin;
	boolean flag = true;
	int curMonth, curYear, Year;
	DateArrayAdapter arrayAdapterDay, arrayAdapterMonth, arrayAdapterYear;
	int DAY = 0, MONTH = 1, YEAR = 2;
	// Set can chi
	CalendarUtil calendarUtil = new CalendarUtil();
	TextView tv_namAmLichCanChi, tv_namAmLich, tv_thangAmLich,
			tv_thangAmLichCanChi, tv_ngayAmLichCanChi, tv_ngayAmLich,
			tv_gioAmLichCanChi;

	// Close set can chi

	@Override
	protected int getLayoutId() {

		return R.layout.layout_fragment_doingay;
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

		// Set can chi
		tv_namAmLichCanChi = (TextView) findViewById(R.id.tv_namAmLichCanChi);
		tv_namAmLich = (TextView) findViewById(R.id.tv_namAmLich);
		tv_thangAmLich = (TextView) findViewById(R.id.tv_thangAmLich);
		tv_thangAmLichCanChi = (TextView) findViewById(R.id.tv_thangAmLichCanChi);
		tv_ngayAmLich = (TextView) findViewById(R.id.tv_ngayAmLich);
		tv_ngayAmLichCanChi = (TextView) findViewById(R.id.tv_ngayAmLichCanChi);
		// Close set can chi
		ll_chitietngay_doingay = (View) findViewById(R.id.ll_chitietngay_doingay);
		ll_themsukien_doingay = (View) findViewById(R.id.ll_themsukien_doingay);
		ll_themsukien_doingay.setOnClickListener(this);
		ll_chitietngay_doingay.setOnClickListener(this);
		tv_ngayduong = (TextView) findViewById(R.id.tv_ngayduong_doingay);
		tv_thangduong = (TextView) findViewById(R.id.tv_thangduong_doingay);
		tv_namduong = (TextView) findViewById(R.id.tv_namduong_doingay);

		tv_ngayam_doingay = (TextView) findViewById(R.id.tv_ngayam_doingay);
		tv_thangam_doingay = (TextView) findViewById(R.id.tv_thangam_doingay);
		tv_namam_doingay = (TextView) findViewById(R.id.tv_namam_doingay);

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

		tv_duongLich = (TextView) findViewById(R.id.tv_duongLich);
		tv_amLich = (TextView) findViewById(R.id.tv_amLich);
		iv_doiNgay = (ImageView) findViewById(R.id.iv_doiNgay);
		iv_doiNgay.setOnClickListener(this);

		Calendar c = Calendar.getInstance();
		valueDayBegin = c.get(Calendar.DAY_OF_MONTH) + "";
		valueMonthBegin = (c.get(Calendar.MONTH) + 1) + "";
		valueYearBegin = c.get(Calendar.YEAR) + "";
		tv_ngayduong.setText("Ngày " + Integer.parseInt(valueDayBegin));
		tv_thangduong.setText("Tháng " + Integer.parseInt(valueMonthBegin));
		tv_namduong.setText("Năm " + Integer.parseInt(valueYearBegin));
		CalendarUtil calendarUtil = new CalendarUtil();
		int[] convert = calendarUtil.convertSolar2Lunar(
				Integer.parseInt(valueDayBegin),
				Integer.parseInt(valueMonthBegin),
				Integer.parseInt(valueYearBegin), 7);
		calendar.set(Calendar.DAY_OF_MONTH, convert[0]);
		calendar.set(Calendar.MONTH, convert[1] - 1);
		calendar.set(Calendar.YEAR, convert[2]);
		tv_ngayam_doingay.setText("Ngày " + convert[0]);
		tv_thangam_doingay.setText("Tháng " + convert[1]);
		tv_namam_doingay.setText("Năm " + convert[2]);
		// Set can chi
		String[] mangtam1=tv_ngayam_doingay.getText().toString().split(" ");
		int ngay=Integer.parseInt(mangtam1[1]);
		String[] mangtam2=tv_thangam_doingay.getText().toString().split(" ");
		int thang=Integer.parseInt(mangtam2[1]);
		String[] mangtam3=tv_namam_doingay.getText().toString().split(" ");
		int nam=Integer.parseInt(mangtam3[1]);
		tv_ngayAmLich.setText(String.valueOf(ngay));
		tv_thangAmLich.setText(String.valueOf(thang));
		tv_namAmLich.setText(String.valueOf(nam));
		tv_ngayAmLichCanChi.setText(calendarUtil.getCanChiOfDay(ngay,
				thang, nam));
		tv_thangAmLichCanChi.setText(calendarUtil.getCanOfMonth(
				calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR))
				+ " "
				+ calendarUtil.getChiOfMonth(calendar.get(Calendar.MONTH) + 1));
		tv_namAmLichCanChi.setText(calendarUtil.getCanOfYear(calendar
				.get(Calendar.YEAR))
				+ " "
				+ calendarUtil.getChiOfYear(calendar.get(Calendar.YEAR)));
		((MainActivity) getActivity()).cancelAsyncTask();
		// Close set can chi

		((MainActivity) getActivity()).tv_ngay_doingay_menu
				.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						calendar = Calendar.getInstance();
						int dayCurr = calendar.get(Calendar.DAY_OF_MONTH);
						int monthCurr = calendar.get(Calendar.MONTH) + 1;
						int yearCurr = calendar.get(Calendar.YEAR);
						if (tv_duongLich.getText().toString().trim()
								.equalsIgnoreCase("Dương lịch")
								&& tv_amLich.getText().toString().trim()
										.equalsIgnoreCase("Âm lịch")) {
							day.setCurrentItem(dayCurr - 1);
							month.setCurrentItem(monthCurr - 1);
							year.setCurrentItem(yearCurr - 1900);
							flagAmDuong = false;
						} else if (tv_duongLich.getText().toString().trim()
								.equalsIgnoreCase("Âm Lịch")
								&& tv_amLich.getText().toString().trim()
										.equalsIgnoreCase("Dương lịch")) {
							flagAmDuong = false;
							CalendarUtil calendarUtil = new CalendarUtil();
							int[] convert = calendarUtil.convertSolar2Lunar(
									dayCurr, monthCurr, yearCurr, 7);
							calendar.set(Calendar.DAY_OF_MONTH, convert[0]);
							calendar.set(Calendar.MONTH, convert[1] - 1);
							calendar.set(Calendar.YEAR, convert[2]);
							day.setCurrentItem(convert[0] - 1);
							month.setCurrentItem(convert[1] - 1);
							year.setCurrentItem(convert[2] - 1900);
						}
						flagAmDuong = true;
					}
				});
	}

	// Wheel scrolled listener
	OnWheelScrollListener scrolledListener = new OnWheelScrollListener() {
		public void onScrollStarts(WheelView wheel) {
			wheelScrolled = true;
		}

		public void onScrollEnds(WheelView wheel) {
			wheelScrolled = false;
			updateStatus();
			if (tv_duongLich.getText().toString().trim()
					.equalsIgnoreCase("Dương lịch")
					&& tv_amLich.getText().toString().trim()
							.equalsIgnoreCase("Âm lịch")) {
				updateDayOfMonth();
				flagAmDuong = false;
			} else if (tv_duongLich.getText().toString().trim()
					.equalsIgnoreCase("Âm Lịch")
					&& tv_amLich.getText().toString().trim()
							.equalsIgnoreCase("Dương lịch")) {
				CalendarUtil calendarUtil = new CalendarUtil();
				/*
				 * int []convert =
				 * calendarUtil.convertSolar2Lunar(Integer.parseInt
				 * (valueDayBegin),Integer.parseInt(valueMonthBegin),
				 * Integer.parseInt(valueYearBegin),7); int totalDayOfMonthAL =
				 * GetNumDayMonth(30, convert[1], convert[2]); int
				 * current_day_select = day.getCurrentItem(); if
				 * ((current_day_select + 1) > totalDayOfMonthAL) {
				 * current_day_select = totalDayOfMonthAL - 1; }
				 * day.setCurrentItem(current_day_select);
				 * updateDaysAL(totalDayOfMonthAL);
				 */
				// int[] convertSolar2LunarAL =
				// calendarUtil.convertSolar2Lunar(Integer.parseInt(valueDayBegin),
				// Integer.parseInt(valueMonthBegin),
				// Integer.parseInt(valueYearBegin), 7);
				int totalDayOfMonthAL = getDaysMonth(
						Integer.parseInt(valueMonthBegin),
						Integer.parseInt(valueYearBegin));
				// int totalDayOfMonthALBD =
				// lunarMonthDayCount(Integer.parseInt(valueYearBegin),
				// Integer.parseInt(valueMonthBegin));
				int current_day_select_bd = day.getCurrentItem();
				if ((current_day_select_bd + 1) > totalDayOfMonthAL) {
					current_day_select_bd = totalDayOfMonthAL - 1;
				}
				day.setCurrentItem(current_day_select_bd);
				updateDaysAL(totalDayOfMonthAL);
				// updateDaysAL(lunarMonthDayCount(Integer.parseInt(valueYearBegin),
				// Integer.parseInt(valueMonthBegin)));
				flagAmDuong = false;
			}
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
				if (tv_duongLich.getText().toString().trim()
						.equalsIgnoreCase("Dương lịch")
						&& tv_amLich.getText().toString().trim()
								.equalsIgnoreCase("Âm lịch")) {
					updateDayOfMonth();
					flagAmDuong = false;
				} else if (tv_duongLich.getText().toString().trim()
						.equalsIgnoreCase("Âm Lịch")
						&& tv_amLich.getText().toString().trim()
								.equalsIgnoreCase("Dương lịch")) {
					int totalDayOfMonthAL = getDaysMonth(
							Integer.parseInt(valueMonthBegin),
							Integer.parseInt(valueYearBegin));
					int current_day_select_bd = day.getCurrentItem();
					if ((current_day_select_bd + 1) > totalDayOfMonthAL) {
						current_day_select_bd = totalDayOfMonthAL - 1;
					}
					day.setCurrentItem(current_day_select_bd);
					updateDaysAL(totalDayOfMonthAL);
					flagAmDuong = false;
				}
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
		case R.id.iv_doiNgay:
			if (tv_duongLich.getText().toString().trim()
					.equalsIgnoreCase("Dương lịch")
					&& tv_amLich.getText().toString().trim()
							.equalsIgnoreCase("Âm lịch")) {
				switchAmDuong(false);
				flagAmDuong = false;
			} else if (tv_duongLich.getText().toString().trim()
					.equalsIgnoreCase("Âm Lịch")
					&& tv_amLich.getText().toString().trim()
							.equalsIgnoreCase("Dương lịch")) {
				switchAmDuong(true);
				flagAmDuong = false;
			}
			flagAmDuong = true;
			break;
		case R.id.ll_themsukien_doingay:
			if (flag) {
				Bundle mBundle = new Bundle();
				mBundle.putInt("NGAY_SUKIEN",
						calendar.get(Calendar.DAY_OF_MONTH));
				mBundle.putInt("THANG_SUKIEN",
						(calendar.get(Calendar.MONTH) + 1));
				mBundle.putInt("NAM_SUKIEN", calendar.get(Calendar.YEAR));
				Intent intent = new Intent(getActivity(),
						ThemSuKienActivity.class);
				intent.putExtra("Bundle", mBundle);
				startActivity(intent);
				flag = false;
			} else if (flag == false && wheelScrolled == false) {
				Bundle mBundle1 = new Bundle();
				mBundle1.putInt("NGAY_SUKIEN", Integer.parseInt(valueDayBegin));
				mBundle1.putInt("THANG_SUKIEN",
						Integer.parseInt(valueMonthBegin));
				mBundle1.putInt("NAM_SUKIEN", Integer.parseInt(valueYearBegin));
				Intent intent1 = new Intent(getActivity(),
						ThemSuKienActivity.class);
				intent1.putExtra("Bundle", mBundle1);
				startActivity(intent1);
			}
			break;
		case R.id.ll_chitietngay_doingay:
			if (flag) {
				Bundle mBundle = new Bundle();
				mBundle.putInt("NGAY", calendar.get(Calendar.DAY_OF_MONTH));
				mBundle.putInt("THANG", (calendar.get(Calendar.MONTH) + 1));
				mBundle.putInt("NAM", calendar.get(Calendar.YEAR));
				Intent intent = new Intent(getActivity(),
						ChiTietNgayActivity.class);
				intent.putExtra("Bundle", mBundle);
				startActivity(intent);
				flag = false;
			} else if (flag == false && wheelScrolled == false) {
				Bundle mBundle1 = new Bundle();
				mBundle1.putInt("NGAY", Integer.parseInt(valueDayBegin));
				mBundle1.putInt("THANG", Integer.parseInt(valueMonthBegin));
				mBundle1.putInt("NAM", Integer.parseInt(valueYearBegin));
				Intent intent = new Intent(getActivity(),
						ChiTietNgayActivity.class);
				intent.putExtra("Bundle", mBundle1);
				startActivity(intent);
			}

			break;
		}
	}

	private void switchAmDuong(boolean isDuong) {
		if (isDuong) {
			CalendarUtil calendarUtil = new CalendarUtil();
			int[] convert = calendarUtil.convertLunar2Solar(
					Integer.parseInt(valueDayBegin),
					Integer.parseInt(valueMonthBegin),
					Integer.parseInt(valueYearBegin), leap, 7);
			calendar.set(Calendar.DAY_OF_MONTH, convert[0]);
			calendar.set(Calendar.MONTH, convert[1] - 1);
			calendar.set(Calendar.YEAR, convert[2]);
			changeTextDuong(Integer.parseInt(valueDayBegin),
					Integer.parseInt(valueMonthBegin),
					Integer.parseInt(valueYearBegin));

			changeTextAm(convert[0], convert[1], convert[2]);
			wheelScrolled = false;
			tv_duongLich.setText("Dương Lịch");
			tv_amLich.setText("Âm lịch");
			day.setCurrentItem(convert[0] - 1);
			month.setCurrentItem(convert[1] - 1);
			year.setCurrentItem(convert[2] - 1900);
		} else {
			CalendarUtil calendarUtil = new CalendarUtil();
			int[] convert = calendarUtil.convertSolar2Lunar(
					Integer.parseInt(valueDayBegin),
					Integer.parseInt(valueMonthBegin),
					Integer.parseInt(valueYearBegin), 7);
			calendar.set(Calendar.DAY_OF_MONTH, convert[0]);
			calendar.set(Calendar.MONTH, convert[1] - 1);
			calendar.set(Calendar.YEAR, convert[2]);
			changeTextAm(Integer.parseInt(valueDayBegin),
					Integer.parseInt(valueMonthBegin),
					Integer.parseInt(valueYearBegin));
			changeTextDuong(convert[0], convert[1], convert[2]);
			leap = convert[3];
			wheelScrolled = false;
			tv_duongLich.setText("Âm Lịch");
			tv_amLich.setText("Dương Lịch");

			day.setCurrentItem(convert[0] - 1);
			month.setCurrentItem(convert[1] - 1);
			year.setCurrentItem(convert[2] - 1900);
		}
	}

	private void changeTextDuong(int day, int month, int year) {
		tv_ngayduong.setText("Ngày " + day);
		tv_thangduong.setText("Tháng " + month);
		tv_namduong.setText("Năm " + year);
	}

	private void changeTextAm(int day, int month, int year) {
		tv_ngayam_doingay.setText("Ngày " + day);
		tv_thangam_doingay.setText("Tháng " + month);
		tv_namam_doingay.setText("Năm " + year);
	}

	// Set can chi
	private void changeTextDuong2(int day, int month, int year) {
	}

	private void changeTextAm2(int day, int month, int year) {
		tv_ngayAmLich.setText(String.valueOf(day));
		tv_thangAmLich.setText(String.valueOf(month));
		tv_namAmLich.setText(String.valueOf(year));
		tv_ngayAmLichCanChi.setText(calendarUtil.getCanChiOfDay(day, month,
				year));
		tv_thangAmLichCanChi.setText(calendarUtil.getCanOfMonth(
				month, year)
				+ " "
				+ calendarUtil.getChiOfMonth(month));
		tv_namAmLichCanChi.setText(calendarUtil.getCanOfYear(year)
				+ " "
				+ calendarUtil.getChiOfYear(year));
		((MainActivity) getActivity()).cancelAsyncTask();
	}

	// Close set can chi

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
		if (tv_duongLich.getText().toString().trim()
				.equalsIgnoreCase("Dương lịch")
				&& tv_amLich.getText().toString().trim()
						.equalsIgnoreCase("Âm lịch")) {
			CalendarUtil calendarUtil = new CalendarUtil();
			int[] convert = calendarUtil.convertSolar2Lunar(
					Integer.parseInt(valueDayBegin),
					Integer.parseInt(valueMonthBegin),
					Integer.parseInt(valueYearBegin), 7);
			calendar.set(Calendar.DAY_OF_MONTH, convert[0]);
			calendar.set(Calendar.MONTH, convert[1] - 1);
			calendar.set(Calendar.YEAR, convert[2]);
			changeTextDuong(Integer.parseInt(valueDayBegin),
					Integer.parseInt(valueMonthBegin),
					Integer.parseInt(valueYearBegin));
			changeTextAm2(calendar.get(Calendar.DAY_OF_MONTH),
					calendar.get(Calendar.MONTH) + 1,
					calendar.get(Calendar.YEAR));
			changeTextAm(calendar.get(Calendar.DAY_OF_MONTH),
					calendar.get(Calendar.MONTH) + 1,
					calendar.get(Calendar.YEAR));
			flagAmDuong = false;
		} else if (tv_duongLich.getText().toString().trim()
				.equalsIgnoreCase("Âm Lịch")
				&& tv_amLich.getText().toString().trim()
						.equalsIgnoreCase("Dương lịch")) {
			CalendarUtil calendarUtil = new CalendarUtil();
			int[] convert = calendarUtil.convertLunar2Solar(
					Integer.parseInt(valueDayBegin),
					Integer.parseInt(valueMonthBegin),
					Integer.parseInt(valueYearBegin), leap, 7);
			calendar.set(Calendar.DAY_OF_MONTH, convert[0]);
			calendar.set(Calendar.MONTH, convert[1] - 1);
			calendar.set(Calendar.YEAR, convert[2]);
			changeTextDuong(Integer.parseInt(valueDayBegin),
					Integer.parseInt(valueMonthBegin),
					Integer.parseInt(valueYearBegin));
			changeTextAm(calendar.get(Calendar.DAY_OF_MONTH),
					calendar.get(Calendar.MONTH) + 1,
					calendar.get(Calendar.YEAR));
			changeTextAm2(Integer.parseInt(valueDayBegin),
					Integer.parseInt(valueMonthBegin),
					Integer.parseInt(valueYearBegin));
			int totalDayOfMonthAL = getDaysMonth(
					Integer.parseInt(valueMonthBegin),
					Integer.parseInt(valueYearBegin));
			int current_day_select = day.getCurrentItem();
			if ((current_day_select + 1) > totalDayOfMonthAL) {
				current_day_select = totalDayOfMonthAL - 1;
			}
			day.setCurrentItem(current_day_select);
			updateDaysAL(totalDayOfMonthAL);
			flagAmDuong = false;
		}
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
