package com.bhmedia.lichvannien2016.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.bhmedia.lichvannien2016.R;
import com.bhmedia.lichvannien2016.ui.activity.ChiTietNgayActivity;
import com.bhmedia.lichvannien2016.ui.activity.MainActivity;
import com.bhmedia.lichvannien2016.ui.activity.MyApp;
import com.bhmedia.lichvannien2016.ui.activity.ThemSuKienActivity;
import com.bhmedia.lichvannien2016.ui.adapter.GridCellAdapter;
import com.bhmedia.lichvannien2016.ui.model.MonthObject;
import com.bhmedia.lichvannien2016.ui.utility.CalendarUtil;
import com.google.android.gms.analytics.HitBuilders;


/**
 * Created by Van on 1/14/2015.
 */
public class LichThangFragment extends BaseFragment implements
		View.OnClickListener {
	Context context;
	int day, month, year;
	ImageView prevMonth, nextMonth;
	TextView currentMonth;
	Calendar _calendar;
	TextView tv_chitietNgayThang, tv_themSukien_thang;
	TextView tv_ngayAmLich_thang, tv_ngayAmLichCanChi_thang,
			tv_thangAmLich_thang, tv_thangAmLichCanChi_thang,
			tv_namAmLich_thang, tv_namAmLichCanChi_thang;
	Calendar calendar;
	int dduong, mduong, yduong;
	CalendarUtil calendarUtil;
	Bundle mBundle;
	GridView calendarView;
	GridCellAdapter adapter;

	@Override
	protected int getLayoutId() {
		return R.layout.layout_fragment_lichthang;
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
		MyApp.tracker().setScreenName("Lich Thang Android");
		MyApp.tracker().send(new HitBuilders.AppViewBuilder().build());

		
		tv_ngayAmLich_thang = (TextView) findViewById(R.id.tv_ngayAmLich_thang);
		tv_thangAmLich_thang = (TextView) findViewById(R.id.tv_thangAmLich_thang);
		tv_namAmLich_thang = (TextView) findViewById(R.id.tv_namAmLich_thang);
		tv_ngayAmLichCanChi_thang = (TextView) findViewById(R.id.tv_ngayAmLichCanChi_thang);
		tv_thangAmLichCanChi_thang = (TextView) findViewById(R.id.tv_thangAmLichCanChi_thang);
		tv_namAmLichCanChi_thang = (TextView) findViewById(R.id.tv_namAmLichCanChi_thang);
		calendar = Calendar.getInstance();
		dduong = calendar.get(Calendar.DAY_OF_MONTH);
		mduong = calendar.get(Calendar.MONTH) + 1;
		yduong = calendar.get(Calendar.YEAR);
		calendarUtil = new CalendarUtil();
		int[] convert = calendarUtil.convertSolar2Lunar(dduong, mduong, yduong,
				7);
		calendar.set(Calendar.DAY_OF_MONTH, convert[0]);
		calendar.set(Calendar.MONTH, convert[1] - 1);
		calendar.set(Calendar.YEAR, convert[2]);
		tv_ngayAmLich_thang.setText(convert[0] + "");
		tv_thangAmLich_thang.setText(convert[1] + "");
		tv_namAmLich_thang.setText(convert[2] + "");

		tv_ngayAmLichCanChi_thang.setText(calendarUtil.getCanChiOfDay(dduong,
				mduong, yduong));
		tv_thangAmLichCanChi_thang.setText(calendarUtil.getCanOfMonth(
				calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR))
				+ " "
				+ calendarUtil.getChiOfMonth(calendar.get(Calendar.MONTH) + 1));
		tv_namAmLichCanChi_thang.setText(calendarUtil.getCanOfYear(calendar
				.get(Calendar.YEAR))
				+ " "
				+ calendarUtil.getChiOfYear(calendar.get(Calendar.YEAR)));

		tv_chitietNgayThang = (TextView) findViewById(R.id.tv_chitietNgayThang);
		tv_themSukien_thang = (TextView) findViewById(R.id.tv_themSukien_thang);
		tv_chitietNgayThang.setOnClickListener(this);
		tv_themSukien_thang.setOnClickListener(this);

		_calendar = Calendar.getInstance(Locale.getDefault());
		day = _calendar.get(Calendar.DAY_OF_MONTH);
		month = _calendar.get(Calendar.MONTH) + 1;
		year = _calendar.get(Calendar.YEAR);
		prevMonth = (ImageView) findViewById(R.id.prevMonth);
		currentMonth = (TextView) findViewById(R.id.currentMonth);
		switch (month) {
		case 1:
			currentMonth.setText("January "+ year);
			break;
		case 2:
			currentMonth.setText("February "+ year);
			break;
		case 3:
			currentMonth.setText("March "+ year);
			break;
		case 4:
			currentMonth.setText("April "+ year);
			break;
		case 5:
			currentMonth.setText("May "+ year);
			break;
		case 6:
			currentMonth.setText("June "+ year);
			break;
		case 7:
			currentMonth.setText("July "+ year);
			break;
		case 8:
			currentMonth.setText("August "+ year);
			break;
		case 9:
			currentMonth.setText("September "+ year);
			break;
		case 10:
			currentMonth.setText("October "+ year);
			break;
		case 11:
			currentMonth.setText("November "+ year);
			break;
		case 12:
			currentMonth.setText("December "+ year);
			break;

		default:
			break;
		}
		nextMonth = (ImageView) findViewById(R.id.nextMonth);
		prevMonth.setOnClickListener(this);
		nextMonth.setOnClickListener(this);

		// Initialised
		calendarView = (GridView) findViewById(R.id.gr_calendarGridView);
		adapter = new GridCellAdapter(getActivity(),
				R.id.calendar_day_gridcell, month, year);
		calendarView.setAdapter(adapter);
		adapter.setCurrentDaySelect(-1);
		adapter.notifyDataSetChanged();

		calendarView
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						MonthObject date_month_year = (MonthObject) parent
								.getItemAtPosition(position);
						day = (int) date_month_year.day;
						int month1 = (int) date_month_year.month + 1;
						year = (int) date_month_year.year;
						switch (month1) {
						case 1:
							currentMonth.setText("January "+ year);
							break;
						case 2:
							currentMonth.setText("February "+ year);
							break;
						case 3:
							currentMonth.setText("March "+ year);
							break;
						case 4:
							currentMonth.setText("April "+ year);
							break;
						case 5:
							currentMonth.setText("May "+ year);
							break;
						case 6:
							currentMonth.setText("June "+ year);
							break;
						case 7:
							currentMonth.setText("July "+ year);
							break;
						case 8:
							currentMonth.setText("August "+ year);
							break;
						case 9:
							currentMonth.setText("September "+ year);
							break;
						case 10:
							currentMonth.setText("October "+ year);
							break;
						case 11:
							currentMonth.setText("November "+ year);
							break;
						case 12:
							currentMonth.setText("December "+ year);
							break;

						default:
							break;
						}
						int[] convert = calendarUtil.convertSolar2Lunar(day,
								month1, year, 7);
						calendar.set(Calendar.DAY_OF_MONTH, convert[0]);
						calendar.set(Calendar.MONTH, convert[1] - 1);
						calendar.set(Calendar.YEAR, convert[2]);
						tv_ngayAmLich_thang.setText(calendar
								.get(Calendar.DAY_OF_MONTH) + "");
						tv_thangAmLich_thang.setText((calendar
								.get(Calendar.MONTH) + 1) + "");
						tv_namAmLich_thang.setText(calendar.get(Calendar.YEAR)
								+ "");
						tv_ngayAmLichCanChi_thang.setText(calendarUtil
								.getCanChiOfDay(day, month1, year));
						tv_thangAmLichCanChi_thang.setText(calendarUtil
								.getCanOfMonth(
										calendar.get(Calendar.MONTH) + 1,
										calendar.get(Calendar.YEAR))
								+ " "
								+ calendarUtil.getChiOfMonth(calendar
										.get(Calendar.MONTH) + 1));
						tv_namAmLichCanChi_thang.setText(calendarUtil
								.getCanOfYear(calendar.get(Calendar.YEAR))
								+ " "
								+ calendarUtil.getChiOfYear(calendar
										.get(Calendar.YEAR)));
						if (month1 < month) {
							prevMonth();
						} else if (month1 > month) {
							nextMonth();
						}
						month = month1;
						adapter.setCurrentDaySelect(day);
					}
				});

		((MainActivity) getActivity()).tv_thang_menu
				.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						Date dateCurr = new Date();
						calendar = Calendar.getInstance(Locale.getDefault());
						calendar.setTime(dateCurr);
						day = calendar.get(Calendar.DAY_OF_MONTH);
						month = calendar.get(Calendar.MONTH) + 1;
						year = calendar.get(Calendar.YEAR);
						setGridCellAdapterToDate(month, year);
					}
				});
	}

	@Override
	public void onResume() {
		super.onResume();
		adapter.notifyDataSetChanged();
	}

	private void nextMonth() {
		if (month > 11) {
			month = 1;
			year++;
		} else {
			month++;
		}
		setGridCellAdapterToDate(month, year);
	}

	private void prevMonth() {
		if (month <= 1) {
			month = 12;
			year--;
		} else {
			month--;
		}
		setGridCellAdapterToDate(month, year);
	}

	private int currentDaySelect;

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.prevMonth:
			currentDaySelect = adapter.getCurrentDaySelect();
			prevMonth();
			adapter.setCurrentDaySelect(currentDaySelect);
			break;
		case R.id.nextMonth:
			currentDaySelect = adapter.getCurrentDaySelect();
			nextMonth();
			adapter.setCurrentDaySelect(currentDaySelect);
			break;
		case R.id.tv_chitietNgayThang:
			mBundle = new Bundle();
			mBundle.putInt("NGAY", day);
			mBundle.putInt("THANG", month);
			mBundle.putInt("NAM", year);
			Intent intent = new Intent(getActivity(), ChiTietNgayActivity.class);
			intent.putExtra("Bundle", mBundle);
			startActivity(intent);
			break;
		case R.id.tv_themSukien_thang:
			Intent intent1 = new Intent(getActivity(), ThemSuKienActivity.class);
			Bundle mBundle1 = new Bundle();
			mBundle1.putInt("NGAY_SUKIEN", day);
			mBundle1.putInt("THANG_SUKIEN", month);
			mBundle1.putInt("NAM_SUKIEN", year);
			intent1.putExtra("Bundle", mBundle1);
			startActivity(intent1);
			break;
		}

	}

	private void setGridCellAdapterToDate(int month, int year) {

		adapter = new GridCellAdapter(getActivity(),
				R.id.calendar_day_gridcell, month, year);
		switch (month) {
		case 1:
			currentMonth.setText("January "+ year);
			break;
		case 2:
			currentMonth.setText("February "+ year);
			break;
		case 3:
			currentMonth.setText("March "+ year);
			break;
		case 4:
			currentMonth.setText("April "+ year);
			break;
		case 5:
			currentMonth.setText("May "+ year);
			break;
		case 6:
			currentMonth.setText("June "+ year);
			break;
		case 7:
			currentMonth.setText("July "+ year);
			break;
		case 8:
			currentMonth.setText("August "+ year);
			break;
		case 9:
			currentMonth.setText("September "+ year);
			break;
		case 10:
			currentMonth.setText("October "+ year);
			break;
		case 11:
			currentMonth.setText("November "+ year);
			break;
		case 12:
			currentMonth.setText("December "+ year);
			break;

		default:
			break;
		}
		adapter.notifyDataSetChanged();
		calendarView.setAdapter(adapter);
		/*
		 * Date dateCurrent = new Date(); Calendar calendar =
		 * Calendar.getInstance(); calendar.setTime(dateCurrent);
		 */
		// int day = calendar.get(Calendar.DAY_OF_MONTH);
		int[] convert = calendarUtil.convertSolar2Lunar(day, month, year, 7);
		calendar.set(Calendar.DAY_OF_MONTH, convert[0]);
		calendar.set(Calendar.MONTH, convert[1] - 1);
		calendar.set(Calendar.YEAR, convert[2]);
		tv_ngayAmLich_thang.setText(calendar.get(Calendar.DAY_OF_MONTH) + "");
		tv_thangAmLich_thang.setText((calendar.get(Calendar.MONTH) + 1) + "");
		tv_namAmLich_thang.setText(calendar.get(Calendar.YEAR) + "");
		tv_ngayAmLichCanChi_thang.setText(calendarUtil.getCanChiOfDay(day,
				month, year));
		tv_thangAmLichCanChi_thang
				.setText(calendarUtil.getCanOfMonth(
						(calendar.get(Calendar.MONTH) + 1),
						calendar.get(Calendar.YEAR))
						+ " "
						+ calendarUtil.getChiOfMonth(calendar
								.get(Calendar.MONTH) + 1));
		tv_namAmLichCanChi_thang.setText(calendarUtil.getCanOfYear(calendar
				.get(Calendar.YEAR))
				+ " "
				+ calendarUtil.getChiOfYear(calendar.get(Calendar.YEAR)));
	}

}
