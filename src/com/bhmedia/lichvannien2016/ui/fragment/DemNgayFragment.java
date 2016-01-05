package com.bhmedia.lichvannien2016.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.bhmedia.lichvannien2016.R;
import com.bhmedia.lichvannien2016.ui.activity.MainActivity;
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
public class DemNgayFragment extends BaseFragment implements View.OnClickListener {
    Context context;
    private int NoOfYear = 115;
    private int YesOfYear = 35;
    String months[] = new String[]{"1", "2", "3", "4", "5",
            "6", "7", "8", "9", "10", "11", "12"};
    String[] years;
    String[] days;
    // Wheel scrolled flag
    private boolean wheelScrolled = false;
    WheelView day_batdau, month_batdau, year_batdau, day_ketthuc, month_ketthuc, year_kethuc;
    TextView tv_demngay;
    RadioButton rb_duongLich_demNgay, rb_amLich_demNgay;
    Calendar calendar;
    int dduong, mduong, yduong, timeZone;
    int leap = 0, leap1 = 0;
    String valueMonthBegin, valueDayBegin, valueYearBegin, valueMonthEnd, valueDayEnd, valueYearEnd;
    DateArrayAdapter arrayAdapterDayBD, arrayAdapterMonthBD, arrayAdapterYearBD, arrayAdapterDayKT, arrayAdapterMonthKT, arrayAdapterYearKT;
    int DAY = 0, MONTH = 1, YEAR = 2;
    CalendarUtil calendarUtil;

    @Override
    protected int getLayoutId() {
        return R.layout.layout_fragment_demngay;
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
        
        MyApp.tracker().setScreenName("Dem Ngay Android");
		MyApp.tracker().send(new HitBuilders.AppViewBuilder().build());

		
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
        tv_demngay = (TextView) findViewById(R.id.tv_demngay);

        calendar = Calendar.getInstance();
        day_batdau = (WheelView) findViewById(R.id.day_batdau);
        month_batdau = (WheelView) findViewById(R.id.month_batdau);
        year_batdau = (WheelView) findViewById(R.id.year_batdau);

        day_ketthuc = (WheelView) findViewById(R.id.day_ketthuc);
        month_ketthuc = (WheelView) findViewById(R.id.month_ketthuc);
        year_kethuc = (WheelView) findViewById(R.id.year_ketthuc);

        // month
        int curMonth = calendar.get(Calendar.MONTH);
        arrayAdapterMonthBD = new DateArrayAdapter(context, months, curMonth);
        month_batdau.setViewAdapter(arrayAdapterMonthBD);
        month_batdau.setVisibleItems(2);
        month_batdau.setCurrentItem(curMonth);
        month_batdau.addChangingListener(changedListener);
        month_batdau.addScrollingListener(scrolledListener);

        arrayAdapterMonthKT = new DateArrayAdapter(context, months, curMonth);
        month_ketthuc.setViewAdapter(arrayAdapterMonthKT);
        month_ketthuc.setVisibleItems(2);
        month_ketthuc.setCurrentItem(curMonth);
        month_ketthuc.addChangingListener(changedListener);
        month_ketthuc.addScrollingListener(scrolledListener);

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

        arrayAdapterYearKT = new DateArrayAdapter(context, years, NoOfYear);
        year_kethuc.setViewAdapter(arrayAdapterYearKT);
        year_kethuc.setVisibleItems(2);
        year_kethuc.setCurrentItem(curYear - (Year - NoOfYear));
        year_kethuc.addChangingListener(changedListener);
        year_kethuc.addScrollingListener(scrolledListener);

        //day
        updateDaysBD(year_batdau, month_batdau, day_batdau);
        day_batdau.setCurrentItem(calendar.get(Calendar.DAY_OF_MONTH) - 1);
        day_batdau.addChangingListener(changedListener);
        day_batdau.addScrollingListener(scrolledListener);

        updateDaysKT(year_kethuc, month_ketthuc, day_ketthuc);
        day_ketthuc.setCurrentItem(calendar.get(Calendar.DAY_OF_MONTH) - 1);
        day_ketthuc.addChangingListener(changedListener);
        day_ketthuc.addScrollingListener(scrolledListener);

        ((MainActivity) getActivity()).tv_ngay_demngay_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int dayCurr = calendar.get(Calendar.DAY_OF_MONTH);
                int monthCurr = calendar.get(Calendar.MONTH) + 1;
                int yearCurr = calendar.get(Calendar.YEAR);
                if (rb_duongLich_demNgay.isChecked()) {
                    day_batdau.setCurrentItem(dayCurr - 1);
                    month_batdau.setCurrentItem(monthCurr - 1);
                    year_batdau.setCurrentItem(yearCurr - 1900);

                    day_ketthuc.setCurrentItem(dayCurr - 1);
                    month_ketthuc.setCurrentItem(monthCurr - 1);
                    year_kethuc.setCurrentItem(yearCurr - 1900);
                } else if (rb_amLich_demNgay.isChecked()) {
                    CalendarUtil calendarUtil = new CalendarUtil();
                    int[] convert = calendarUtil.convertSolar2Lunar(dayCurr, monthCurr, yearCurr, 7);
                    day_batdau.setCurrentItem(convert[0] - 1);
                    month_batdau.setCurrentItem(convert[1] - 1);
                    year_batdau.setCurrentItem(convert[2] - 1900);

                    day_ketthuc.setCurrentItem(convert[0] - 1);
                    month_ketthuc.setCurrentItem(convert[1] - 1);
                    year_kethuc.setCurrentItem(convert[2] - 1900);
                }
            }
        });

        Calendar c = Calendar.getInstance();
        valueDayBegin = c.get(Calendar.DAY_OF_MONTH) + "";
        valueMonthBegin = (c.get(Calendar.MONTH) + 1) + "";
        valueYearBegin = c.get(Calendar.YEAR) + "";
        valueDayEnd = c.get(Calendar.DAY_OF_MONTH) + "";
        valueMonthEnd = (c.get(Calendar.MONTH) + 1) + "";
        valueYearEnd = c.get(Calendar.YEAR) + "";
    }

    // Wheel scrolled listener
    OnWheelScrollListener scrolledListener = new OnWheelScrollListener() {
        public void onScrollStarts(WheelView wheel) {
            wheelScrolled = true;
        }

        public void onScrollEnds(WheelView wheel) {
            wheelScrolled = false;
            updateStatus();
            /*if (rb_duongLich_demNgay.isChecked()) {
                updateDayOfMonth();
            } else if (rb_amLich_demNgay.isChecked()) {
                int totalDayOfMonthALBD = getDaysMonth(Integer.parseInt(valueMonthBegin), Integer.parseInt(valueYearBegin));
                int current_day_select_bd = day_batdau.getCurrentItem();
                if ((current_day_select_bd + 1) > totalDayOfMonthALBD) {
                    current_day_select_bd = totalDayOfMonthALBD - 1;
                }
                day_batdau.setCurrentItem(current_day_select_bd);
                updateDaysALBD(totalDayOfMonthALBD);

                int totalDayOfMonthALKT = getDaysMonth(Integer.parseInt(valueMonthEnd), Integer.parseInt(valueYearEnd));
                int current_day_select_kt = day_ketthuc.getCurrentItem();
                if ((current_day_select_kt + 1) > totalDayOfMonthALKT) {
                    current_day_select_kt = totalDayOfMonthALKT - 1;
                }
                day_ketthuc.setCurrentItem(current_day_select_kt);
                updateDaysALKT(totalDayOfMonthALKT);
            }*/
            arrayAdapterDayBD.changeColorCurrValue(day_batdau.getCurrentItem());
            arrayAdapterMonthBD.changeColorCurrValue(month_batdau.getCurrentItem());
            arrayAdapterYearBD.changeColorCurrValue(year_batdau.getCurrentItem());

            arrayAdapterDayKT.changeColorCurrValue(day_ketthuc.getCurrentItem());
            arrayAdapterMonthKT.changeColorCurrValue(month_ketthuc.getCurrentItem());
            arrayAdapterYearKT.changeColorCurrValue(year_kethuc.getCurrentItem());
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
                /*if (rb_duongLich_demNgay.isChecked()) {
                    updateDayOfMonth();
                } else if (rb_amLich_demNgay.isChecked()) {
                    int totalDayOfMonthALBD = getDaysMonth(Integer.parseInt(valueMonthBegin), Integer.parseInt(valueYearBegin));
                    int current_day_select_bd = day_batdau.getCurrentItem();
                    if ((current_day_select_bd + 1) > totalDayOfMonthALBD) {
                        current_day_select_bd = totalDayOfMonthALBD - 1;
                    }
                    day_batdau.setCurrentItem(current_day_select_bd);
                    updateDaysALBD(totalDayOfMonthALBD);

                    int totalDayOfMonthALKT = getDaysMonth(Integer.parseInt(valueMonthEnd), Integer.parseInt(valueYearEnd));
                    int current_day_select_kt = day_ketthuc.getCurrentItem();
                    if ((current_day_select_kt + 1) > totalDayOfMonthALKT) {
                        current_day_select_kt = totalDayOfMonthALKT - 1;
                    }
                    day_ketthuc.setCurrentItem(current_day_select_kt);
                    updateDaysALKT(totalDayOfMonthALKT);
                }*/
                arrayAdapterDayBD.changeColorCurrValue(day_batdau.getCurrentItem());
                arrayAdapterMonthBD.changeColorCurrValue(month_batdau.getCurrentItem());
                arrayAdapterYearBD.changeColorCurrValue(year_batdau.getCurrentItem());

                arrayAdapterDayKT.changeColorCurrValue(day_ketthuc.getCurrentItem());
                arrayAdapterMonthKT.changeColorCurrValue(month_ketthuc.getCurrentItem());
                arrayAdapterYearKT.changeColorCurrValue(year_kethuc.getCurrentItem());
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
                calendar.get(Calendar.YEAR) + (year.getCurrentItem() - NoOfYear));
        calendar.set(Calendar.MONTH, month.getCurrentItem());

        int maxDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        days = getListString(1, maxDays + 1);
        arrayAdapterDayBD = new DateArrayAdapter(context, days, calendar
                .get(Calendar.DAY_OF_MONTH) - 1);
        day.setViewAdapter(arrayAdapterDayBD);
        int curDay = Math.min(maxDays, day.getCurrentItem() + 1);
        day.setCurrentItem(curDay - 1, true);
        calendar.set(Calendar.DAY_OF_MONTH, curDay);

        return calendar;
    }

    Calendar updateDaysKT(WheelView year, WheelView month, WheelView day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,
                calendar.get(Calendar.YEAR) + (year.getCurrentItem() - NoOfYear));
        calendar.set(Calendar.MONTH, month.getCurrentItem());

        int maxDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        days = getListString(1, maxDays + 1);
        arrayAdapterDayKT = new DateArrayAdapter(context, days, calendar
                .get(Calendar.DAY_OF_MONTH) - 1);
        day.setViewAdapter(arrayAdapterDayKT);
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

    public void updateDaysALKT(int totalDayKT) {
        days = getListString(1, totalDayKT + 1);
        arrayAdapterDayKT = new DateArrayAdapter(context, days, totalDayKT);
        day_ketthuc.setViewAdapter(arrayAdapterDayKT);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rb_duongLich_demNgay:
                switchAmDuong(true);
                break;
            case R.id.rb_amLich_demNgay:
                switchAmDuong(false);
                break;
        }
    }

    private void switchAmDuong(boolean isDuong) {

        if (isDuong) {
            Calendar calendarAm = Calendar.getInstance();
            calendarAm.set(Calendar.DAY_OF_MONTH, Integer.parseInt(valueDayBegin));
            calendarAm.set(Calendar.MONTH, Integer.parseInt(valueMonthBegin) - 1);
            calendarAm.set(Calendar.YEAR, Integer.parseInt(valueYearBegin));

            Calendar calendarDuong = Calendar.getInstance();
            CalendarUtil calendarUtil = new CalendarUtil();
            int[] convert = calendarUtil.convertLunar2Solar(Integer.parseInt(valueDayBegin),
                    Integer.parseInt(valueMonthBegin), Integer.parseInt(valueYearBegin), leap, 7);
            calendarDuong.set(Calendar.DAY_OF_MONTH, convert[0]);
            calendarDuong.set(Calendar.MONTH, convert[1] - 1);
            calendarDuong.set(Calendar.YEAR, convert[2]);
            day_batdau.setCurrentItem(calendarDuong.get(Calendar.DAY_OF_MONTH) - 1);
            month_batdau.setCurrentItem(calendarDuong.get(Calendar.MONTH));
            year_batdau.setCurrentItem(calendarDuong.get(Calendar.YEAR) - 1900);

            calendarAm.set(Calendar.DAY_OF_MONTH, Integer.parseInt(valueDayEnd));
            calendarAm.set(Calendar.MONTH, Integer.parseInt(valueMonthEnd) - 1);
            calendarAm.set(Calendar.YEAR, Integer.parseInt(valueYearEnd));
            int[] convert1 = calendarUtil.convertLunar2Solar(Integer.parseInt(valueDayEnd),
                    Integer.parseInt(valueMonthEnd), Integer.parseInt(valueYearEnd), leap1, 7);
            calendarDuong.set(Calendar.DAY_OF_MONTH, convert1[0]);
            calendarDuong.set(Calendar.MONTH, convert1[1] - 1);
            calendarDuong.set(Calendar.YEAR, convert1[2]);
            day_ketthuc.setCurrentItem(calendarDuong.get(Calendar.DAY_OF_MONTH) - 1);
            month_ketthuc.setCurrentItem(calendarDuong.get(Calendar.MONTH));
            year_kethuc.setCurrentItem(calendarDuong.get(Calendar.YEAR) - 1900);
            wheelScrolled = false;
        } else {
            Calendar calendarDuong = Calendar.getInstance();
            calendarDuong.set(Calendar.DAY_OF_MONTH, Integer.parseInt(valueDayBegin));
            calendarDuong.set(Calendar.MONTH, Integer.parseInt(valueMonthBegin) - 1);
            calendarDuong.set(Calendar.YEAR, Integer.parseInt(valueYearBegin));
            Calendar calendarAm = Calendar.getInstance();
            CalendarUtil calendarUtil = new CalendarUtil();
            int[] convert = calendarUtil.convertSolar2Lunar(Integer.parseInt(valueDayBegin),
                    Integer.parseInt(valueMonthBegin), Integer.parseInt(valueYearBegin), 7);
            calendarAm.set(Calendar.DAY_OF_MONTH, convert[0]);
            calendarAm.set(Calendar.MONTH, convert[1] - 1);
            calendarAm.set(Calendar.YEAR, convert[2]);
            leap = convert[3];

            day_batdau.setCurrentItem(calendarAm.get(Calendar.DAY_OF_MONTH) - 1);
            month_batdau.setCurrentItem(calendarAm.get(Calendar.MONTH));
            year_batdau.setCurrentItem(calendarAm.get(Calendar.YEAR) - 1900);
            calendarDuong.set(Calendar.DAY_OF_MONTH, Integer.parseInt(valueDayEnd));
            calendarDuong.set(Calendar.MONTH, Integer.parseInt(valueMonthEnd) - 1);
            calendarDuong.set(Calendar.YEAR, Integer.parseInt(valueYearEnd));
            int[] convert1 = calendarUtil.convertSolar2Lunar(Integer.parseInt(valueDayEnd),
                    Integer.parseInt(valueMonthEnd), Integer.parseInt(valueYearEnd), 7);
            calendarAm.set(Calendar.DAY_OF_MONTH, convert1[0]);
            calendarAm.set(Calendar.MONTH, convert1[1] - 1);
            calendarAm.set(Calendar.YEAR, convert1[2]);
            leap1 = convert1[3];
            day_ketthuc.setCurrentItem(calendarAm.get(Calendar.DAY_OF_MONTH) - 1);
            month_ketthuc.setCurrentItem(calendarAm.get(Calendar.MONTH));
            year_kethuc.setCurrentItem(calendarAm.get(Calendar.YEAR) - 1900);
            wheelScrolled = false;
        }
    }

    private class DateNumericAdapter extends NumericWheelAdapter {
        int currentItem;
        int currentValue;

        public DateNumericAdapter(Context context, int minValue, int maxValue, int current) {
            super(context, minValue, maxValue);
            this.currentValue = current;
            setTextSize(14);
        }

        @Override
        protected void configureTextView(TextView view) {
            super.configureTextView(view);
            if (currentItem == currentValue) {
//                view.setTextColor(Color.parseColor("#C4AC5C"));
            	 view.setTextColor(Color.parseColor("#1C9D51"));
            }else {
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
//                view.setTextColor(Color.parseColor("#C4AC5C"));
            	view.setTextColor(Color.parseColor("#1C9D51"));
            }else {
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

    private final int[] daysOfMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    private void updateDayOfMonth() {
        int positionMonthBegin = month_batdau.getCurrentItem();
        int totalDayOfMonthBegin = daysOfMonth[positionMonthBegin];
        CalendarUtil calendarUtil = new CalendarUtil();
        if (calendarUtil.checkLeap(Integer.parseInt(years[year_batdau.getCurrentItem()]))) {
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

        int positionMonthEnd = month_ketthuc.getCurrentItem();
        int totalDayOfMonthEnd = daysOfMonth[positionMonthEnd];
        if (calendarUtil.checkLeap(Integer.parseInt(years[year_kethuc.getCurrentItem()]))) {
            if (totalDayOfMonthEnd == 1) {
                totalDayOfMonthEnd++;
            }
        }
        int current_day_end_select = day_ketthuc.getCurrentItem();
        if ((current_day_end_select + 1) > totalDayOfMonthEnd) {
            current_day_end_select = totalDayOfMonthEnd - 1;
        }
        day_ketthuc.setCurrentItem(current_day_end_select);
        updateDaysKT(year_kethuc, month_ketthuc, day_ketthuc);
    }

    private void updateStatus() {
        int positionMonthBegin = month_batdau.getCurrentItem();
        valueMonthBegin = months[positionMonthBegin];

        int positionMonthEnd = month_ketthuc.getCurrentItem();
        valueMonthEnd = months[positionMonthEnd];

        int positionDayBegin = day_batdau.getCurrentItem();
        valueDayBegin = positionDayBegin + 1 + "";

        int positionDayEnd = day_ketthuc.getCurrentItem();
        valueDayEnd = positionDayEnd + 1 + "";

        int positionYearBatDau = year_batdau.getCurrentItem();
        valueYearBegin = years[positionYearBatDau];

        int positionYearEnd = year_kethuc.getCurrentItem();
        valueYearEnd = years[positionYearEnd];

        if (rb_duongLich_demNgay.isChecked()) {
            updateDayOfMonth();
            String dateStart = valueYearBegin + "/" + valueMonthBegin + "/" + valueDayBegin;
            String dateStop = valueYearEnd + "/" + valueMonthEnd + "/" + valueDayEnd;
            // Custom date format
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
            Date d1 = null;
            Date d2 = null;
            try {
                d1 = format.parse(dateStart);
                d2 = format.parse(dateStop);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            // Get msec from each, and subtract.
            long diff = d2.getTime() - d1.getTime();
            long diffDays = diff / (24 * 60 * 60 * 1000);
            tv_demngay.setText(diffDays + "");
        } else if (rb_amLich_demNgay.isChecked()) {
            int totalDayOfMonthALBD = getDaysMonth(Integer.parseInt(valueMonthBegin), Integer.parseInt(valueYearBegin));
            int current_day_select_bd = day_batdau.getCurrentItem();
            if ((current_day_select_bd + 1) > totalDayOfMonthALBD) {
                current_day_select_bd = totalDayOfMonthALBD - 1;
            }
            day_batdau.setCurrentItem(current_day_select_bd);
            updateDaysALBD(totalDayOfMonthALBD);

            int totalDayOfMonthALKT = getDaysMonth(Integer.parseInt(valueMonthEnd), Integer.parseInt(valueYearEnd));
            int current_day_select_kt = day_ketthuc.getCurrentItem();
            if ((current_day_select_kt + 1) > totalDayOfMonthALKT) {
                current_day_select_kt = totalDayOfMonthALKT - 1;
            }
            day_ketthuc.setCurrentItem(current_day_select_kt);
            updateDaysALKT(totalDayOfMonthALKT);

            int[] convert = calendarUtil.convertLunar2Solar(Integer.parseInt(valueDayBegin),
                    Integer.parseInt(valueMonthBegin), Integer.parseInt(valueYearBegin), leap, 7);
            int[] convert1 = calendarUtil.convertLunar2Solar(Integer.parseInt(valueDayEnd),
                    Integer.parseInt(valueMonthEnd), Integer.parseInt(valueYearEnd), leap, 7);
            String dateStart = convert[2] + "/" + convert[1] + "/" + convert[0];
            String dateStop = convert1[2] + "/" + convert1[1] + "/" + convert1[0];
            // Custom date format
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
            Date d1 = null;
            Date d2 = null;
            try {
                d1 = format.parse(dateStart);
                d2 = format.parse(dateStop);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            // Get msec from each, and subtract.
            long diff = d2.getTime() - d1.getTime();
            long diffDays = diff / (24 * 60 * 60 * 1000);
            tv_demngay.setText(diffDays + "");
        }
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
        int[] solars = calendarUtil.convertLunar2Solar(lunarDay, month, year, leap, 7);

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
