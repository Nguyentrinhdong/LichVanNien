package com.bhmedia.lichvannien2016.ui.adapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bhmedia.lichvannien2016.R;
import com.bhmedia.lichvannien2016.ui.database.NewDBSuKien;
import com.bhmedia.lichvannien2016.ui.model.MonthObject;
import com.bhmedia.lichvannien2016.ui.utility.CalendarUtil;

/**
 * Created by Van on 1/22/2015.
 */
public class GridCellAdapter extends BaseAdapter {
	private final Context _context;
	private List<MonthObject> list;
	private static final int DAY_OFFSET = 1;

	private final String[] months = { "1", "2", "3", "4", "5", "6", "7", "8",
			"9", "10", "11", "12" };
	private final int[] daysOfMonth = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31,
			30, 31 };
	private int daysInMonth;
	private int currentDayOfMonth;
	private int currentWeekDay;
	private final int month, year;
	private int SIZE_OF_LIST = 0;
	LayoutInflater inflater;

	int currentDaySelect = -1;

	static class ViewHolder {
		TextView gridcell;
		TextView tv_amLich_grid;
		ImageView iv_hacHoangdDao, iv_sukien;
	}

	private final int COLOR_BLUE = 11;
	private final int COLOR_GREY = 12;
	private final int COLOR_WHITE = 13;

	// Days in Current Month
	public GridCellAdapter(Context context, int textViewResourceId,
			final int month, final int year) {
		super();
		this._context = context;
		this.list = new ArrayList<MonthObject>();
		this.month = month;
		this.year = year;

		inflater = (LayoutInflater) _context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		Calendar calendar = Calendar.getInstance();
		setCurrentDayOfMonth(calendar.get(Calendar.DAY_OF_MONTH));
		setCurrentWeekDay(calendar.get(Calendar.DAY_OF_WEEK));
		// Print Month

		list = printMonth();
		SIZE_OF_LIST = list.size();
		// Find Number of Events

		suKienHelper = new NewDBSuKien(_context);
	}

	private NewDBSuKien suKienHelper;

	private String getMonthAsString(int i) {
		return months[i];
	}

	private int getNumberOfDaysOfMonth(int i) {
		return daysOfMonth[i];
	}

	public MonthObject getItem(int position) {
		return list.get(position);
	}

	@Override
	public int getCount() {
		return SIZE_OF_LIST;
	}

	public void loadData(ArrayList<MonthObject> months) {
		this.list = months;
		this.SIZE_OF_LIST = list.size();
		notifyDataSetChanged();
	}

	public ArrayList<MonthObject> printMonth() {
		// The number of days to leave blank at
		// the start of this month.

		int mm = this.month;
		int yy = this.year;

		ArrayList<MonthObject> temp = new ArrayList<MonthObject>();

		int trailingSpaces = 0;
		int leadSpaces = 0;
		int daysInPrevMonth = 0;
		int prevMonth = 0;
		int prevYear = 0;
		int nextMonth = 0;
		int nextYear = 0;

		int currentMonth = mm - 1;
		String currentMonthName = getMonthAsString(currentMonth);
		daysInMonth = getNumberOfDaysOfMonth(currentMonth);

		CalendarUtil calendarUtil = new CalendarUtil();
		if (calendarUtil.checkLeap(yy)) {
			if (mm == 2) {
				daysInMonth++;
			}
		}
		// Gregorian Calendar : MINUS 1, set to FIRST OF MONTH
		GregorianCalendar cal = new GregorianCalendar(yy, currentMonth, 1);
		if (currentMonth == 11) {
			prevMonth = currentMonth - 1;
			daysInPrevMonth = getNumberOfDaysOfMonth(prevMonth);
			nextMonth = 0;
			prevYear = yy;
			nextYear = yy + 1;
		} else if (currentMonth == 0) {
			prevMonth = 11;
			prevYear = yy - 1;
			nextYear = yy;
			daysInPrevMonth = getNumberOfDaysOfMonth(prevMonth);
			nextMonth = 1;
		} else {
			prevMonth = currentMonth - 1;
			nextMonth = currentMonth + 1;
			nextYear = yy;
			prevYear = yy;
			daysInPrevMonth = getNumberOfDaysOfMonth(prevMonth);
		}

		// Compute how much to leave before before the first day of the
		// month.
		// getDay() returns 0 for Sunday.
		int currentWeekDay = cal.get(Calendar.DAY_OF_WEEK) - 1;
		trailingSpaces = currentWeekDay;

		/*
		 * if (cal.isLeapYear(cal.get(Calendar.YEAR)) && mm == 1) {
		 * ++daysInMonth; }
		 */
		if (trailingSpaces == 0)
			trailingSpaces = 7;
		// Trailing Month days
		MonthObject monthObject;
		for (int i = 0; i < trailingSpaces; i++) {
			monthObject = new MonthObject();
			monthObject.day = (daysInPrevMonth - trailingSpaces + DAY_OFFSET)
					+ i;
			monthObject.month = prevMonth;
			monthObject.year = prevYear;
			monthObject.color = COLOR_GREY;
			temp.add(monthObject);
		}

		// Current Month Days
		for (int i = 1; i <= daysInMonth; i++) {
			monthObject = new MonthObject();
			monthObject.day = i;
			monthObject.month = currentMonth;
			monthObject.year = yy;
			if (i == getCurrentDayOfMonth()) {
				monthObject.color = COLOR_BLUE;
				temp.add(monthObject);
			} else {
				monthObject.color = COLOR_WHITE;
				temp.add(monthObject);
			}
		}
		int nextMonthSpace = 42 - daysInMonth - trailingSpaces;
		// Leading Month days
		for (int i = 0; i < nextMonthSpace; i++) {
			monthObject = new MonthObject();
			monthObject.day = i + 1;
			monthObject.month = nextMonth;
			monthObject.year = nextYear;
			monthObject.color = COLOR_GREY;
			temp.add(monthObject);
		}

		return temp;
	}

	public void setCurrentDaySelect(int currentDay) {
		this.currentDaySelect = currentDay;
		notifyDataSetChanged();
	}

	public int getCurrentDaySelect() {
		return currentDaySelect;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressLint("NewApi")
	@Override
	public View getView(int poss, View convertView, ViewGroup parent) {
		View row = convertView;
		ViewHolder holder = null;
		if (row == null) {
			row = inflater.inflate(R.layout.item_gridview, parent, false);
			holder = new ViewHolder();
			holder.gridcell = (TextView) row
					.findViewById(R.id.calendar_day_gridcell);
			holder.tv_amLich_grid = (TextView) row
					.findViewById(R.id.tv_amLich_grid);
			holder.tv_amLich_grid.setTextColor(Color.GRAY);
			holder.iv_hacHoangdDao = (ImageView) row
					.findViewById(R.id.iv_hacHoangdDao);
			holder.iv_sukien = (ImageView) row.findViewById(R.id.iv_sukien);
			row.setTag(holder);
		} else {
			holder = (ViewHolder) row.getTag();
		}
		// Get a reference to the Day gridcell
		// ACCOUNT FOR SPACING

		MonthObject monthObject = list.get(poss);
		int theday = monthObject.day;
		int themonth = monthObject.month + 1;
		int theyear = monthObject.year;
		CalendarUtil calendarUtil = new CalendarUtil();

		holder.gridcell.setText(theday + "");

		if (monthObject.color == COLOR_GREY) {
			holder.gridcell.setTextColor(Color.DKGRAY);
		} else if (monthObject.color == COLOR_WHITE) {
			holder.gridcell.setTextColor(Color.BLACK);
			holder.gridcell.setTypeface(Typeface.DEFAULT_BOLD);
			row.setBackgroundColor(Color.WHITE);

			if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
				row.setAlpha(0.7f);
			}

			if (currentDaySelect == theday) {
				currentDaySelect = theday;
				row.setBackgroundResource(R.drawable.month_selection2);
			}
		} else if (monthObject.color == COLOR_BLUE) {
			Calendar c = Calendar.getInstance();
			if (themonth == (c.get(Calendar.MONTH) + 1)
					&& theday == c.get(Calendar.DAY_OF_MONTH)
					&& theyear == c.get(Calendar.YEAR)) {
				holder.gridcell.setTextColor(Color.BLACK);
				row.setBackgroundResource(R.drawable.month_today2);
				row.setBackgroundColor(_context.getResources().getColor(
						R.color.gray3));
				holder.gridcell.setTypeface(Typeface.DEFAULT_BOLD);
				// currentDaySelect = theday;
			} else {
				if (currentDaySelect == -1) {
					// row.setBackgroundResource(R.drawable.month_selection);
				} else {
					holder.gridcell.setTextColor(Color.BLACK);
					row.setBackgroundColor(_context.getResources().getColor(
							R.color.gray3));
					if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
						row.setAlpha(0.7f);
					}
				}
			}
		}

		Calendar calendar = Calendar.getInstance();
		int[] convert = calendarUtil.convertSolar2Lunar(theday, themonth,
				theyear, 7);
		calendar.set(Calendar.DAY_OF_MONTH, convert[0]);
		calendar.set(Calendar.MONTH, convert[1] - 1);
		calendar.set(Calendar.YEAR, convert[2]);

		if (calendarUtil.IsBadGoodDay(theday, themonth, theyear, convert[1]) == CalendarUtil.NGAYHACDAO) {
			holder.iv_hacHoangdDao
					.setImageResource(R.drawable.hacdao_icon_lichthang);
		} else if (calendarUtil.IsBadGoodDay(theday, themonth, theyear,
				convert[1]) == CalendarUtil.NGAYHOANGDAO) {
			holder.iv_hacHoangdDao.setImageResource(R.drawable.hoangdao_icon);
		} else {
			holder.iv_hacHoangdDao.setVisibility(View.GONE);
		}

		if (convert[0] == 1) {
			holder.tv_amLich_grid.setText(convert[0] + "/" + convert[1]);
		} else {
			holder.tv_amLich_grid.setText(convert[0] + "");
		}

		boolean result = suKienHelper.checkSuKienByTime(theday, themonth,
				theyear);
		if (result) {
			holder.iv_sukien.setVisibility(View.VISIBLE);
		} else {
			holder.iv_sukien.setVisibility(View.GONE);
		}

		if (ngayDacBietDuong(theday, themonth)) {
			if (monthObject.color == COLOR_GREY) {
				holder.gridcell.setTextColor(Color.DKGRAY);
			} else {
				holder.gridcell.setTextColor(Color.RED);
			}
		} else if (ngayDacBietAm(convert[0], convert[1], convert[2])) {
			holder.tv_amLich_grid.setTextColor(Color.RED);
		}

		return row;
	}

	private boolean ngayDacBietDuong(int ngayDuong, int thangDuong) {
		boolean ret = false;

		if ((ngayDuong == 30 && thangDuong == 4)
				|| (ngayDuong == 1 && thangDuong == 5)
				|| (ngayDuong == 2 && thangDuong == 9)
				|| (ngayDuong == 1 && thangDuong == 1)
				|| (ngayDuong == 14 && thangDuong == 2)
				|| (ngayDuong == 24 && thangDuong == 12)
				|| (ngayDuong == 8 && thangDuong == 3)
				|| (ngayDuong == 20 && thangDuong == 10)
				|| (ngayDuong == 20 && thangDuong == 11))
			ret = true;
		return ret;
	}

	private boolean ngayDacBietAm(int ngayAm, int thangAm, int namAm) {
		boolean ret = false;
		if (namAm == 2015) {
			if ((ngayAm >= 1 && ngayAm <= 3 && thangAm == 1)
					|| (ngayAm == 10 && thangAm == 3)||(ngayAm==29&&thangAm==12))
				ret = true;
		} else {
			if ((ngayAm >= 1 && ngayAm <= 3 && thangAm == 1)
					|| (ngayAm == 10 && thangAm == 3)||(ngayAm==30&&thangAm==12))
				ret = true;
		}

		return ret;
	}

	public void closeDatabaseSuKien() {
		if (suKienHelper != null) {
			suKienHelper.closeDatabase();
			suKienHelper.close();
		}
	}

	public int getCurrentDayOfMonth() {
		return currentDayOfMonth;
	}

	private void setCurrentDayOfMonth(int currentDayOfMonth) {
		this.currentDayOfMonth = currentDayOfMonth;
	}

	public void setCurrentWeekDay(int currentWeekDay) {
		this.currentWeekDay = currentWeekDay;
	}

	public int getCurrentWeekDay() {
		return currentWeekDay;
	}
}
