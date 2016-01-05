package com.bhmedia.lichvannien2016.ui.fragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.bhmedia.lichvannien2016.R;
import com.bhmedia.lichvannien2016.ui.utility.CalendarUtil;
import com.bhmedia.lichvannien2016.ui.utility.SmartLogUtility;
import com.bhmedia.lichvannien2016.ui.wheel.ArrayWheelAdapter;
import com.bhmedia.lichvannien2016.ui.wheel.OnWheelChangedListener;
import com.bhmedia.lichvannien2016.ui.wheel.OnWheelScrollListener;
import com.bhmedia.lichvannien2016.ui.wheel.WheelView;
import com.google.android.gms.internal.ed;

public class CTNFragment extends BaseFragment implements View.OnClickListener {
	Context context;
	LinearLayout parentAds;
	Calendar calendar;
	int DAY = 0, MONTH = 1, YEAR = 2;
	RadioButton rb_duongLich_demNgay, rb_amLich_demNgay;
	WheelView day_batdau, month_batdau, year_batdau, hour_bd, minute_bd;
	int dduong, mduong, yduong, timeZone;
	int leap = 0;
	private int NoOfYear = 115;
	private int YesOfYear = 35;
	String months[] = new String[] { "1", "2", "3", "4", "5", "6", "7", "8",
			"9", "10", "11", "12" };
	String[] years;
	String[] days;
	String[] hours;
	String[] minutes;
	private boolean wheelScrolled = false;
	String valueMonthBegin, valueDayBegin, valueYearBegin, valueHourBegin,
			valueMinuteBegin;
	String dateStart, dateStop, timeStart, timeStop;
	int timeShow;
	DateArrayAdapter arrayAdapterDayBD, arrayAdapterMonthBD,
			arrayAdapterYearBD, arrayAdapterHourBD, arrayAdapterMinuteBD;
	CalendarUtil calendarUtil;
	EditText ednam, edthang, edtuan, edngay, edgio, edphut;
	// Set kết quả
	TextView tvketqua;
	private int bientam;

	// Close set kết quả

	@Override
	protected int getLayoutId() {
		return R.layout.congtrungay;
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
		loadTabs();
		// Set kết quả
		bientam = 0;
		tvketqua = (TextView) findViewById(R.id.tvketqua);
		// Close set kết quả
		// Set edittext
		ednam = (EditText) findViewById(R.id.ednam);
		edthang = (EditText) findViewById(R.id.edthang);
		edngay = (EditText) findViewById(R.id.edngay);
		edtuan = (EditText) findViewById(R.id.edtuan);
		edgio = (EditText) findViewById(R.id.edgio);
		edphut = (EditText) findViewById(R.id.edphut);
		ednam.setOnClickListener(this);
		edthang.setOnClickListener(this);
		edtuan.setOnClickListener(this);
		edngay.setOnClickListener(this);
		edgio.setOnClickListener(this);
		edphut.setOnClickListener(this);
		ednam.setFocusable(false);
		// edthang.setFocusable(false);
		// edtuan.setFocusable(false);
		// edngay.setFocusable(false);
		// edgio.setFocusable(false);
		// edphut.setFocusable(false);
		// Close edit text
		parentAds = (LinearLayout) findViewById(R.id.parentAds);
		setupUI(parentAds);
		// Weelview
		// Day-Month-Year
		calendar = Calendar.getInstance();
		dduong = calendar.get(Calendar.DAY_OF_MONTH);
		mduong = calendar.get(Calendar.MONTH) + 1;
		yduong = calendar.get(Calendar.YEAR);
		timeZone = 7;
		calendarUtil = new CalendarUtil();

		rb_amLich_demNgay = (RadioButton) findViewById(R.id.rb_amLich_demNgay);
		rb_duongLich_demNgay = (RadioButton) findViewById(R.id.rb_duongLich_demNgay);
		rb_amLich_demNgay.setOnClickListener(this);
		rb_duongLich_demNgay.setOnClickListener(this);
		calendar = Calendar.getInstance();
		day_batdau = (WheelView) findViewById(R.id.day_batdau);
		month_batdau = (WheelView) findViewById(R.id.month_batdau);
		year_batdau = (WheelView) findViewById(R.id.year_batdau);
		// month
		int curMonth = calendar.get(Calendar.MONTH);
		arrayAdapterMonthBD = new DateArrayAdapter(context, months, curMonth);
		month_batdau.setViewAdapter(arrayAdapterMonthBD);
		month_batdau.setVisibleItems(2);
		month_batdau.setCurrentItem(curMonth);
		month_batdau.addChangingListener(changedListener);
		month_batdau.addScrollingListener(scrolledListener);

		Calendar cal = Calendar.getInstance();
		// year
		int curYear = calendar.get(Calendar.YEAR);
		int Year = cal.get(Calendar.YEAR);

		years = getListString(Year - NoOfYear, Year + YesOfYear);

		arrayAdapterYearBD = new DateArrayAdapter(context, years, NoOfYear);
		year_batdau.setViewAdapter(arrayAdapterYearBD);
		year_batdau.setVisibleItems(2);
		year_batdau.setCurrentItem(curYear - (Year - NoOfYear));
		year_batdau.addChangingListener(changedListener);
		year_batdau.addScrollingListener(scrolledListener);

		// day
		updateDaysBD(year_batdau, month_batdau, day_batdau);
		day_batdau.setCurrentItem(calendar.get(Calendar.DAY_OF_MONTH) - 1);
		day_batdau.addChangingListener(changedListener);
		day_batdau.addScrollingListener(scrolledListener);

		hour_bd = (WheelView) findViewById(R.id.hour_bd);
		minute_bd = (WheelView) findViewById(R.id.minute_bd);

		Calendar calendar = Calendar.getInstance();

		hours = getListString(00, 24);
		int curHour = calendar.get(Calendar.HOUR_OF_DAY);

		minutes = getListString(00, 60);
		int curMinute = calendar.get(Calendar.MINUTE);
		arrayAdapterHourBD = new DateArrayAdapter(getActivity(), hours, curHour);
		hour_bd.setViewAdapter(arrayAdapterHourBD);
		hour_bd.setVisibleItems(2);
		hour_bd.setCurrentItem(curHour);
		hour_bd.addChangingListener(changedListener);
		hour_bd.addScrollingListener(scrolledListener);

		arrayAdapterMinuteBD = new DateArrayAdapter(getActivity(), minutes,
				curMinute);
		minute_bd.setViewAdapter(arrayAdapterMinuteBD);
		minute_bd.setVisibleItems(2);
		minute_bd.setCurrentItem(curMinute);
		minute_bd.addChangingListener(changedListener);
		minute_bd.addScrollingListener(scrolledListener);

		Calendar c = Calendar.getInstance();
		valueDayBegin = c.get(Calendar.DAY_OF_MONTH) + "";
		valueMonthBegin = (c.get(Calendar.MONTH) + 1) + "";
		valueYearBegin = c.get(Calendar.YEAR) + "";
		valueHourBegin = c.get(Calendar.HOUR_OF_DAY) + "";
		valueMinuteBegin = (c.get(Calendar.MINUTE)) + "";
	}

	private Calendar getDayShowDialog(int day, int month, int year, int hour,
			int minute, int preTime) {
		Calendar calendar1 = Calendar.getInstance();
		calendar1.set(Calendar.DAY_OF_MONTH, day);
		calendar1.set(Calendar.MONTH, month);
		calendar1.set(Calendar.YEAR, year);
		calendar1.set(Calendar.HOUR_OF_DAY, hour);
		calendar1.set(Calendar.MINUTE, minute);
		calendar1.add(Calendar.MINUTE, 0 - preTime);
		return calendar1;
	}

	// Wheel scrolled listener
	OnWheelScrollListener scrolledListener = new OnWheelScrollListener() {
		public void onScrollStarts(WheelView wheel) {
			wheelScrolled = true;
		}

		public void onScrollEnds(WheelView wheel) {
			wheelScrolled = false;
			updateStatus();
			// updateDayOfMonth();
			arrayAdapterDayBD.changeColorCurrValue(day_batdau.getCurrentItem());
			arrayAdapterMonthBD.changeColorCurrValue(month_batdau
					.getCurrentItem());
			arrayAdapterYearBD.changeColorCurrValue(year_batdau
					.getCurrentItem());

			arrayAdapterHourBD.changeColorCurrValue(hour_bd.getCurrentItem());
			arrayAdapterMinuteBD.changeColorCurrValue(minute_bd
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
				// updateDayOfMonth();
				arrayAdapterDayBD.changeColorCurrValue(day_batdau
						.getCurrentItem());
				arrayAdapterMonthBD.changeColorCurrValue(month_batdau
						.getCurrentItem());
				arrayAdapterYearBD.changeColorCurrValue(year_batdau
						.getCurrentItem());

				arrayAdapterHourBD.changeColorCurrValue(hour_bd
						.getCurrentItem());
				arrayAdapterMinuteBD.changeColorCurrValue(minute_bd
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

	Calendar updateDaysBD(WheelView year, WheelView month, WheelView day) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR,
				calendar.get(Calendar.YEAR)
						+ (year.getCurrentItem() - NoOfYear));
		calendar.set(Calendar.MONTH, month.getCurrentItem());

		int maxDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		days = getListString(1, maxDays + 1);
		arrayAdapterDayBD = new DateArrayAdapter(context, days,
				calendar.get(Calendar.DAY_OF_MONTH) - 1);
		day.setViewAdapter(arrayAdapterDayBD);
		int curDay = Math.min(maxDays, day.getCurrentItem() + 1);
		day.setCurrentItem(curDay - 1, true);
		calendar.set(Calendar.DAY_OF_MONTH, curDay);

		return calendar;
	}

	public void updateDaysALBD(int totalDayBD) {
		days = getListString(1, totalDayBD + 1);
		arrayAdapterDayBD = new DateArrayAdapter(context, days, totalDayBD);
		day_batdau.setViewAdapter(arrayAdapterDayBD);
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
		int positionMonthBegin = month_batdau.getCurrentItem();
		int totalDayOfMonthBegin = daysOfMonth[positionMonthBegin];
		CalendarUtil calendarUtil = new CalendarUtil();
		if (calendarUtil.checkLeap(Integer.parseInt(years[year_batdau
				.getCurrentItem()]))) {
			if (positionMonthBegin == 1) {
				totalDayOfMonthBegin++;
			}
		}
		int current_day_begin_select = day_batdau.getCurrentItem();
		if ((current_day_begin_select + 1) > totalDayOfMonthBegin) {
			current_day_begin_select = totalDayOfMonthBegin - 1;
		}
		day_batdau.setCurrentItem(current_day_begin_select);
		updateDaysBD(year_batdau, month_batdau, day_batdau);
	}

	private void updateStatus() {
		int positionMonthBegin = month_batdau.getCurrentItem();
		valueMonthBegin = months[positionMonthBegin];

		int positionDayBegin = day_batdau.getCurrentItem();
		valueDayBegin = positionDayBegin + 1 + "";

		int positionYearBatDau = year_batdau.getCurrentItem();
		valueYearBegin = years[positionYearBatDau];

		int positionHourBegin = hour_bd.getCurrentItem();
		valueHourBegin = hours[positionHourBegin];

		int positionMinuteBegin = minute_bd.getCurrentItem();
		valueMinuteBegin = minutes[positionMinuteBegin];

		dateStart = valueDayBegin + "/" + valueMonthBegin + "/"
				+ valueYearBegin;

		timeStart = valueHourBegin + ":" + valueMinuteBegin;

		if (rb_duongLich_demNgay.isChecked()) {
			updateDayOfMonth();
			String dateStart = valueYearBegin + "/" + valueMonthBegin + "/"
					+ valueDayBegin;
			// Custom date format
			SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
			Date d1 = null;
			try {
				d1 = format.parse(dateStart);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			// Get msec from each, and subtract.
			// tv_demngay.setText(diffDays + "");
		} else if (rb_amLich_demNgay.isChecked()) {
			int totalDayOfMonthALBD = getDaysMonth(
					Integer.parseInt(valueMonthBegin),
					Integer.parseInt(valueYearBegin));
			int current_day_select_bd = day_batdau.getCurrentItem();
			if ((current_day_select_bd + 1) > totalDayOfMonthALBD) {
				current_day_select_bd = totalDayOfMonthALBD - 1;
			}
			day_batdau.setCurrentItem(current_day_select_bd);
			updateDaysALBD(totalDayOfMonthALBD);

			int[] convert = calendarUtil.convertLunar2Solar(
					Integer.parseInt(valueDayBegin),
					Integer.parseInt(valueMonthBegin),
					Integer.parseInt(valueYearBegin), leap, 7);
			String dateStart = convert[2] + "/" + convert[1] + "/" + convert[0];
			// Custom date format
			SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
			Date d1 = null;
			try {
				d1 = format.parse(dateStart);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			// Get msec from each, and subtract.
			// tv_demngay.setText(diffDays + "");
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rb_duongLich_demNgay:
			switchAmDuong(true);
			SmartLogUtility.logD(valueDayBegin + "/" + valueMonthBegin + "/"
					+ valueYearBegin + " " + valueHourBegin + ":"
					+ valueMinuteBegin);
			break;
		case R.id.rb_amLich_demNgay:
			switchAmDuong(false);
			SmartLogUtility.logD(valueDayBegin + "/" + valueMonthBegin + "/"
					+ valueYearBegin + " " + valueHourBegin + ":"
					+ valueMinuteBegin);
			break;
		case R.id.ednam:
			bientam = 1;
			// ednam.setFocusable(true);
			break;
		case R.id.edthang:
			bientam = 2;
			// edthang.setFocusable(true);
			break;
		case R.id.edtuan:
			bientam = 3;
			// edngay.setFocusable(true);
			break;
		case R.id.edngay:
			bientam = 4;
			// edngay.setFocusable(true);
			break;
		case R.id.edgio:
			bientam = 5;
			// edgio.setFocusable(true);
			break;
		case R.id.edphut:
			bientam = 6;
			// edphut.setFocusable(true);
			break;
		}
	}

	private void switchAmDuong(boolean isDuong) {

		if (isDuong) {
			Calendar calendarAm = Calendar.getInstance();
			calendarAm.set(Calendar.DAY_OF_MONTH,
					Integer.parseInt(valueDayBegin));
			calendarAm.set(Calendar.MONTH,
					Integer.parseInt(valueMonthBegin) - 1);
			calendarAm.set(Calendar.YEAR, Integer.parseInt(valueYearBegin));

			Calendar calendarDuong = Calendar.getInstance();
			CalendarUtil calendarUtil = new CalendarUtil();
			int[] convert = calendarUtil.convertLunar2Solar(
					Integer.parseInt(valueDayBegin),
					Integer.parseInt(valueMonthBegin),
					Integer.parseInt(valueYearBegin), leap, 7);
			calendarDuong.set(Calendar.DAY_OF_MONTH, convert[0]);
			calendarDuong.set(Calendar.MONTH, convert[1] - 1);
			calendarDuong.set(Calendar.YEAR, convert[2]);
			day_batdau
					.setCurrentItem(calendarDuong.get(Calendar.DAY_OF_MONTH) - 1);
			month_batdau.setCurrentItem(calendarDuong.get(Calendar.MONTH));
			year_batdau.setCurrentItem(calendarDuong.get(Calendar.YEAR) - 1900);

			wheelScrolled = false;
		} else {
			Calendar calendarDuong = Calendar.getInstance();
			calendarDuong.set(Calendar.DAY_OF_MONTH,
					Integer.parseInt(valueDayBegin));
			calendarDuong.set(Calendar.MONTH,
					Integer.parseInt(valueMonthBegin) - 1);
			calendarDuong.set(Calendar.YEAR, Integer.parseInt(valueYearBegin));
			Calendar calendarAm = Calendar.getInstance();
			CalendarUtil calendarUtil = new CalendarUtil();
			int[] convert = calendarUtil.convertSolar2Lunar(
					Integer.parseInt(valueDayBegin),
					Integer.parseInt(valueMonthBegin),
					Integer.parseInt(valueYearBegin), 7);
			calendarAm.set(Calendar.DAY_OF_MONTH, convert[0]);
			calendarAm.set(Calendar.MONTH, convert[1] - 1);
			calendarAm.set(Calendar.YEAR, convert[2]);
			leap = convert[3];

			day_batdau
					.setCurrentItem(calendarAm.get(Calendar.DAY_OF_MONTH) - 1);
			month_batdau.setCurrentItem(calendarAm.get(Calendar.MONTH));
			year_batdau.setCurrentItem(calendarAm.get(Calendar.YEAR) - 1900);
			wheelScrolled = false;
		}
	}

	public void loadTabs() {
		// L?y Tabhost id ra tru?c (cái này c?a built - in android
		final TabHost tab = (TabHost) findViewById(android.R.id.tabhost);
		// g?i l?nh setup
		tab.setup();
		TabHost.TabSpec spec;
		// T?o tab1
		spec = tab.newTabSpec("t1");
		spec.setContent(R.id.tab1ctn);
		spec.setIndicator("Add");
		tab.addTab(spec);
		spec = tab.newTabSpec("t2");
		spec.setContent(R.id.tab2ctn);
		spec.setIndicator("Subtract");
		tab.addTab(spec);
		tab.setCurrentTab(0);
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
		int leap = 0;
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

	public static void hideSoftKeyboard(Activity activity) {
		InputMethodManager inputMethodManager = (InputMethodManager) activity
				.getSystemService(Activity.INPUT_METHOD_SERVICE);
		inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus()
				.getWindowToken(), 0);
	}

	public void setupUI(View view) {

		// Set up touch listener for non-text box views to hide keyboard.
		if (!(view instanceof EditText)) {

			view.setOnTouchListener(new OnTouchListener() {

				public boolean onTouch(View v, MotionEvent event) {
					hideSoftKeyboard(getActivity());
					SimpleDateFormat format = new SimpleDateFormat(
							"dd/MM/yyyy HH:mm");
					Calendar ca = Calendar.getInstance();
					ca.set(Calendar.DAY_OF_MONTH,
							Integer.parseInt(valueDayBegin));
					ca.set(Calendar.MONTH,
							Integer.parseInt(valueMonthBegin) - 1);
					ca.set(Calendar.YEAR, Integer.parseInt(valueYearBegin));
					ca.set(Calendar.HOUR_OF_DAY,
							Integer.parseInt(valueHourBegin));
					ca.set(Calendar.MINUTE, Integer.parseInt(valueMinuteBegin));
					if (bientam == 1) {
						ca.add(Calendar.YEAR,
								Integer.parseInt(ednam.getText().toString()));
					}
					if (bientam == 2) {
						ca.add(Calendar.MONTH,
								Integer.parseInt(edthang.getText().toString()));
					}
					if (bientam == 3) {
						ca.add(Calendar.WEEK_OF_MONTH,
								Integer.parseInt(edtuan.getText().toString()));
					}
					if (bientam == 4) {
						ca.add(Calendar.DAY_OF_MONTH,
								Integer.parseInt(edngay.getText().toString()));
					}
					if (bientam == 5) {
						ca.add(Calendar.HOUR_OF_DAY,
								Integer.parseInt(edgio.getText().toString()));
					}
					if (bientam == 6) {
						ca.add(Calendar.MINUTE,
								Integer.parseInt(edphut.getText().toString()));
					}
					tvketqua.setText(format.format(ca.getTime()));
					return false;
				}

			});
		}

		// If a layout container, iterate over children and seed recursion.
		if (view instanceof ViewGroup) {

			for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {

				View innerView = ((ViewGroup) view).getChildAt(i);

				setupUI(innerView);
			}
		}
	}
}
