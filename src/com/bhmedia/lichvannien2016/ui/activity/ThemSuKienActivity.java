package com.bhmedia.lichvannien2016.ui.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.bhmedia.frame.utils.RootSupport;
import com.bhmedia.lichvannien2016.R;
import com.bhmedia.lichvannien2016.ui.adapter.SpinnerAdapter;
import com.bhmedia.lichvannien2016.ui.database.DBSuKienHelper;
import com.bhmedia.lichvannien2016.ui.fragment.LichThangFragment;
import com.bhmedia.lichvannien2016.ui.fragment.SuKienFragment;
import com.bhmedia.lichvannien2016.ui.model.SpinnerObject;
import com.bhmedia.lichvannien2016.ui.model.Sukien;
import com.bhmedia.lichvannien2016.ui.utility.CalendarUtil;
import com.bhmedia.lichvannien2016.ui.wheel.ArrayWheelAdapter;
import com.bhmedia.lichvannien2016.ui.wheel.NumericWheelAdapter;
import com.bhmedia.lichvannien2016.ui.wheel.OnWheelChangedListener;
import com.bhmedia.lichvannien2016.ui.wheel.OnWheelScrollListener;
import com.bhmedia.lichvannien2016.ui.wheel.WheelView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.mediation.admob.AdMobExtras;


/**
 * Created by Van on 4/21/2015.
 */
public class ThemSuKienActivity extends FragmentActivity implements View.OnClickListener, View.OnFocusChangeListener {
    EditText et_tensukien, et_diadiem, et_chitiet;
    TextView tv_batdau, tv_ketthuc, tv_time_batdau, tv_time_ketthuc, tv_changeYesNo;
    View ll_kethuc, ll_batdau;
    WheelView day_bd, month_bd, year_bd, hour_bd, minute_bd, day_kt, month_kt, year_kt, hour_kt, minute_kt;
    private int NoOfYear = 115;
    private int YesOfYear = 45;
    String months[] = new String[]{"1", "2", "3", "4", "5",
            "6", "7", "8", "9", "10", "11", "12"};
    String[] years;
    String[] days;
    String[] hours;
    String[] minutes;
    Bundle mBundle;

    private boolean wheelScrolled = false;

    String valueMonthBegin, valueMonthEnd, valueDayBegin, valueDayEnd, valueYearBegin, valueYearEnd,
            valueHourBegin, valueHourEnd, valueMinuteBegin, valueMinuteEnd;
    String dateStart, dateStop, timeStart, timeStop;
    Spinner sp_nhacnho;
    int timeShow;
    DBSuKienHelper suKienHelper;
    TextView tv_huy, tv_xong;
    DateArrayAdapter arrayAdapterDayBD, arrayAdapterMonthBD, arrayAdapterYearBD,
            arrayAdapterDayKT, arrayAdapterMonthKT, arrayAdapterYearKT, arrayAdapterHourBD, arrayAdapterMinuteBD,
            arrayAdapterHourKT, arrayAdapterMinuteKT;
	private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_fragment_themsukien);
        Bundle extras = new Bundle();
		extras.putString("appname", "lich_van_lien_2016");
		extras.putString("appcat", "productivity");
		extras.putString("jb", "" + RootSupport.isDeviceRooted());

		AdMobExtras adExtra = new AdMobExtras(extras);

		mAdView = (AdView) findViewById(R.id.adView);
		AdRequest adRequest = new AdRequest.Builder().addNetworkExtras(adExtra)
				.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
				.addTestDevice("EDA8BB96AFAC4D787093E74267389265")
				.addTestDevice("3D7268CC8E6A7666958991A316D08A88")
				.addTestDevice("7AFC6206AD04A3073968C664E902FF5D")
				.addTestDevice("780C9B0B7E4E49713CFE54EEF2C2F0E7")
				.addTestDevice("48A720DA7ABCF53ECC3A00DEB6983B97").build();
		mAdView.loadAd(adRequest);
        tv_huy = (TextView) findViewById(R.id.tv_huy);
        tv_xong = (TextView) findViewById(R.id.tv_xong);
        tv_huy.setOnClickListener(this);
        tv_xong.setOnClickListener(this);
        sp_nhacnho = (Spinner) findViewById(R.id.sp_nhacnho);
        day_bd = (WheelView) findViewById(R.id.day_bd);
        month_bd = (WheelView) findViewById(R.id.month_bd);
        year_bd = (WheelView) findViewById(R.id.year_bd);
        hour_bd = (WheelView) findViewById(R.id.hour_bd);
        minute_bd = (WheelView) findViewById(R.id.minute_bd);

        day_kt = (WheelView) findViewById(R.id.day_kt);
        month_kt = (WheelView) findViewById(R.id.month_kt);
        year_kt = (WheelView) findViewById(R.id.year_kt);
        hour_kt = (WheelView) findViewById(R.id.hour_kt);
        minute_kt = (WheelView) findViewById(R.id.minute_kt);

        et_tensukien = (EditText) findViewById(R.id.et_tensukien);
        et_diadiem = (EditText) findViewById(R.id.et_diadiem);
        et_chitiet = (EditText) findViewById(R.id.et_chitiet);

        tv_changeYesNo = (TextView) findViewById(R.id.tv_changeYesNo);
        tv_changeYesNo.setOnClickListener(new View.OnClickListener() {
            boolean flagYesNo = true;

            @Override
            public void onClick(View v) {
                if (tv_changeYesNo.getText().toString().trim().equalsIgnoreCase("YES")) {
                    tv_changeYesNo.setText("NO");
                    flagYesNo = false;
                } else if (tv_changeYesNo.getText().toString().trim().equalsIgnoreCase("NO")) {
                    tv_changeYesNo.setText("YES");
                    flagYesNo = false;
                }
                flagYesNo = true;
            }
        });
        tv_time_batdau = (TextView) findViewById(R.id.tv_time_batdau);
        tv_time_ketthuc = (TextView) findViewById(R.id.tv_time_ketthuc);

        tv_batdau = (TextView) findViewById(R.id.tv_date_batdau);
        tv_ketthuc = (TextView) findViewById(R.id.tv_date_ketthuc);

        et_tensukien.setOnFocusChangeListener(this);
        et_diadiem.setOnFocusChangeListener(this);
        et_chitiet.setOnFocusChangeListener(this);


        ArrayList<SpinnerObject> list = new ArrayList<SpinnerObject>();
        list.add(new SpinnerObject("15 minutes before", 15));
        list.add(new SpinnerObject("30 minutes before", 30));
        list.add(new SpinnerObject("1 hour before", 60));
        list.add(new SpinnerObject("1 day before", 24 * 60));
        SpinnerAdapter dataAdapter = new SpinnerAdapter(this, list);
        sp_nhacnho.setAdapter(dataAdapter);


        Calendar calendar = Calendar.getInstance();

        hours = getListString(00, 24);
        int curHour = calendar.get(Calendar.HOUR_OF_DAY);

        minutes = getListString(00, 60);
        int curMinute = calendar.get(Calendar.MINUTE);
        arrayAdapterHourBD = new DateArrayAdapter(this, hours, curHour);
        hour_bd.setViewAdapter(arrayAdapterHourBD);
        hour_bd.setVisibleItems(2);
        hour_bd.setCurrentItem(curHour);
        hour_bd.addChangingListener(changedListener);
        hour_bd.addScrollingListener(scrolledListener);

        arrayAdapterMinuteBD = new DateArrayAdapter(this, minutes, curMinute);
        minute_bd.setViewAdapter(arrayAdapterMinuteBD);
        minute_bd.setVisibleItems(2);
        minute_bd.setCurrentItem(curMinute);
        minute_bd.addChangingListener(changedListener);
        minute_bd.addScrollingListener(scrolledListener);

        arrayAdapterHourKT = new DateArrayAdapter(this, hours, curHour + 2);
        hour_kt.setViewAdapter(arrayAdapterHourKT);
        hour_kt.setVisibleItems(2);
        hour_kt.setCurrentItem(curHour + 2);
        hour_kt.addChangingListener(changedListener);
        hour_kt.addScrollingListener(scrolledListener);

        arrayAdapterMinuteKT = new DateArrayAdapter(this, minutes, curMinute);
        minute_kt.setViewAdapter(arrayAdapterMinuteKT);
        minute_kt.setVisibleItems(2);
        minute_kt.setCurrentItem(curMinute);
        minute_kt.addChangingListener(changedListener);
        minute_kt.addScrollingListener(scrolledListener);

        // month
        int curMonth = calendar.get(Calendar.MONTH);
        arrayAdapterMonthBD = new DateArrayAdapter(this, months, curMonth);
        month_bd.setViewAdapter(arrayAdapterMonthBD);
        month_bd.setVisibleItems(2);
        month_bd.setCurrentItem(curMonth);
        month_bd.addChangingListener(changedListener);
        month_bd.addScrollingListener(scrolledListener);

        arrayAdapterMonthKT = new DateArrayAdapter(this, months, curMonth);
        month_kt.setViewAdapter(arrayAdapterMonthKT);
        month_kt.setVisibleItems(2);
        month_kt.setCurrentItem(curMonth);
        month_kt.addChangingListener(changedListener);
        month_kt.addScrollingListener(scrolledListener);


        Calendar cal = Calendar.getInstance();
        // year
        int curYear = calendar.get(Calendar.YEAR);
        int Year = cal.get(Calendar.YEAR);

        years = getListString(Year - NoOfYear, Year + YesOfYear);
        arrayAdapterYearBD = new DateArrayAdapter(this, years, NoOfYear);
        year_bd.setViewAdapter(arrayAdapterYearBD);
        year_bd.setVisibleItems(2);
        year_bd.setCurrentItem(curYear - (Year - NoOfYear));
        year_bd.addChangingListener(changedListener);
        year_bd.addScrollingListener(scrolledListener);

        arrayAdapterYearKT = new DateArrayAdapter(this, years, NoOfYear);
        year_kt.setViewAdapter(arrayAdapterYearKT);
        year_kt.setVisibleItems(2);
        year_kt.setCurrentItem(curYear - (Year - NoOfYear));
        year_kt.addChangingListener(changedListener);
        year_kt.addScrollingListener(scrolledListener);

        //day
        updateDaysBD(year_bd, month_bd, day_bd);
        day_bd.setCurrentItem(calendar.get(Calendar.DAY_OF_MONTH) - 1);
        day_bd.addChangingListener(changedListener);
        day_bd.addScrollingListener(scrolledListener);

        updateDaysKT(year_kt, month_kt, day_kt);
        day_kt.setCurrentItem(calendar.get(Calendar.DAY_OF_MONTH) - 1);
        day_kt.addChangingListener(changedListener);
        day_kt.addScrollingListener(scrolledListener);


        View rl_batdau = findViewById(R.id.rl_batdau);
        View rl_kethuc = findViewById(R.id.rl_ketthuc);

        ll_batdau = findViewById(R.id.ll_batdau);
        ll_kethuc = findViewById(R.id.ll_ketthuc);

        rl_batdau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_batdau.setVisibility(ll_batdau.isShown() ? View.GONE : View.VISIBLE);
            }
        });

        rl_kethuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_kethuc.setVisibility(ll_kethuc.isShown() ? View.GONE : View.VISIBLE);
            }
        });

        // Date dateCurr = new Date();
        Calendar c = Calendar.getInstance();
        //calendar1.setTime(dateCurr);
        valueDayBegin = c.get(Calendar.DAY_OF_MONTH) + "";
        valueMonthBegin = (c.get(Calendar.MONTH) + 1) + "";
        valueYearBegin = c.get(Calendar.YEAR) + "";
        valueDayEnd = c.get(Calendar.DAY_OF_MONTH) + "";
        valueMonthEnd = (c.get(Calendar.MONTH) + 1) + "";
        valueYearEnd = c.get(Calendar.YEAR) + "";
        valueHourBegin = c.get(Calendar.HOUR_OF_DAY) + "";
        valueMinuteBegin = (c.get(Calendar.MINUTE)) + "";
        valueHourEnd = (c.get(Calendar.HOUR_OF_DAY) + 2) + "";
        valueMinuteEnd = (c.get(Calendar.MINUTE)) + "";
        tv_batdau.setText(valueDayBegin + "/" + valueMonthBegin + "/" + valueYearBegin);
        tv_ketthuc.setText(valueDayEnd + "/" + valueMonthEnd + "/" + valueYearEnd);
        tv_time_batdau.setText(valueHourBegin + ":" + valueMinuteBegin);
        tv_time_ketthuc.setText(valueHourEnd + ":" + valueMinuteEnd);
        mBundle = getIntent().getExtras().getBundle("Bundle");
        if (mBundle != null) {
            tv_batdau.setText(mBundle.getInt("NGAY_SUKIEN") + "/" + mBundle.getInt("THANG_SUKIEN") + "/" + mBundle.getInt("NAM_SUKIEN"));
            tv_ketthuc.setText(mBundle.getInt("NGAY_SUKIEN") + "/" + mBundle.getInt("THANG_SUKIEN") + "/" + mBundle.getInt("NAM_SUKIEN"));

            updateDaysBD(year_bd, month_bd, day_bd);
            day_bd.setCurrentItem(mBundle.getInt("NGAY_SUKIEN") - 1);
            month_bd.setCurrentItem(mBundle.getInt("THANG_SUKIEN") - 1);
            year_bd.setCurrentItem(mBundle.getInt("NAM_SUKIEN") - 1900);

            updateDaysKT(year_kt, month_kt, day_kt);
            day_kt.setCurrentItem(mBundle.getInt("NGAY_SUKIEN") - 1);
            month_kt.setCurrentItem(mBundle.getInt("THANG_SUKIEN") - 1);
            year_kt.setCurrentItem(mBundle.getInt("NAM_SUKIEN") - 1900);
        }
    }


    private Calendar getDayShowDialog(int day, int month, int year, int hour, int minute, int preTime) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(Calendar.DAY_OF_MONTH, day);
        calendar1.set(Calendar.MONTH, month);
        calendar1.set(Calendar.YEAR, year);
        calendar1.set(Calendar.HOUR_OF_DAY, hour);
        calendar1.set(Calendar.MINUTE, minute);
        calendar1.add(Calendar.MINUTE, 0 - preTime);
        return calendar1;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.et_tensukien:
                if (!hasFocus) {
                    et_tensukien.setHint("");
                } else if (et_tensukien.getText().toString().trim().equals("")) {
                    et_tensukien.setHint("Title ...");
                }
                break;
            case R.id.et_diadiem:
                if (hasFocus) {
                    et_diadiem.setHint("");
                } else if (et_diadiem.getText().toString().trim().equals("")) {
                    et_diadiem.setHint("Location ...");
                }
                break;
            case R.id.et_chitiet:
                if (hasFocus) {
                    et_chitiet.setHint("");
                } else if (et_chitiet.getText().toString().trim().equals("")) {
                    et_chitiet.setHint("Notes ...");
                }
                break;
        }
    }

    private boolean validateInput() {
        boolean flag = true;
        //check blank fields
        if (et_tensukien.getText().toString().trim().equals("")) {
            et_tensukien.setError("Event title is required");
            flag = false;
        }
        if (et_diadiem.getText().toString().trim().equals("")) {
            et_diadiem.setError("Event location is required");
            flag = false;
        }
        if (et_chitiet.getText().toString().trim().equals("")) {
            et_chitiet.setError("Event notes is required");
            flag = false;
        }
        return flag;
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
            arrayAdapterDayBD.changeColorCurrValue(day_bd.getCurrentItem());
            arrayAdapterMonthBD.changeColorCurrValue(month_bd.getCurrentItem());
            arrayAdapterYearBD.changeColorCurrValue(year_bd.getCurrentItem());

            arrayAdapterDayKT.changeColorCurrValue(day_kt.getCurrentItem());
            arrayAdapterMonthKT.changeColorCurrValue(month_kt.getCurrentItem());
            arrayAdapterYearKT.changeColorCurrValue(year_kt.getCurrentItem());

            arrayAdapterHourBD.changeColorCurrValue(hour_bd.getCurrentItem());
            arrayAdapterMinuteBD.changeColorCurrValue(minute_bd.getCurrentItem());
            arrayAdapterHourKT.changeColorCurrValue(hour_kt.getCurrentItem());
            arrayAdapterMinuteKT.changeColorCurrValue(minute_kt.getCurrentItem());
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
                arrayAdapterDayBD.changeColorCurrValue(day_bd.getCurrentItem());
                arrayAdapterMonthBD.changeColorCurrValue(month_bd.getCurrentItem());
                arrayAdapterYearBD.changeColorCurrValue(year_bd.getCurrentItem());

                arrayAdapterDayKT.changeColorCurrValue(day_kt.getCurrentItem());
                arrayAdapterMonthKT.changeColorCurrValue(month_kt.getCurrentItem());
                arrayAdapterYearKT.changeColorCurrValue(year_kt.getCurrentItem());

                arrayAdapterHourBD.changeColorCurrValue(hour_bd.getCurrentItem());
                arrayAdapterMinuteBD.changeColorCurrValue(minute_bd.getCurrentItem());
                arrayAdapterHourKT.changeColorCurrValue(hour_kt.getCurrentItem());
                arrayAdapterMinuteKT.changeColorCurrValue(minute_kt.getCurrentItem());
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
        arrayAdapterDayBD = new DateArrayAdapter(this, days, calendar
                .get(Calendar.DAY_OF_MONTH) - 1);
        day_bd.setViewAdapter(arrayAdapterDayBD);

        int curDay = Math.min(maxDays, day.getCurrentItem() + 1);
        day_bd.setCurrentItem(curDay - 1, true);
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
        arrayAdapterDayKT = new DateArrayAdapter(this, days, calendar
                .get(Calendar.DAY_OF_MONTH) - 1);
        day_kt.setViewAdapter(arrayAdapterDayKT);

        int curDay = Math.min(maxDays, day.getCurrentItem() + 1);
        day_kt.setCurrentItem(curDay - 1, true);
        calendar.set(Calendar.DAY_OF_MONTH, curDay);

        return calendar;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_huy:
                onBackPressed();
                break;
            case R.id.tv_xong:
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                dateStart = tv_batdau.getText().toString().trim();
                dateStop = tv_ketthuc.getText().toString().trim();
                timeStart = tv_time_batdau.getText().toString().trim();
                timeStop = tv_time_ketthuc.getText().toString().trim();

                String date1 = dateStart + " " + timeStart;
                String date2 = dateStop + " " + timeStop;

                Date d1 = null;
                Date d2 = null;
                try {
                    d1 = format.parse(date1);
                    d2 = format.parse(date2);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                long diff = d2.getTime() - d1.getTime();
                /*long diffMinutes = diff / (60 * 1000);
                //long diffHours = diff / (60 * 60 * 1000);
                //long diffDays = diff / (24 * 60 * 60 * 1000);*/
                if (diff <= 0) {
                    AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                    alertDialog.setMessage("Start Time must occur earlier than End Time");
                    alertDialog.setButton("Close", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    alertDialog.show();
                }
                if (validateInput() && diff > 0) {
                    suKienHelper = new DBSuKienHelper(getApplicationContext());
                    timeShow = Integer.parseInt(valueHourBegin) * 60 + Integer.parseInt(valueMinuteBegin)-((SpinnerObject) sp_nhacnho.getSelectedItem()).getTime() ;
                    Calendar calendar1 = getDayShowDialog(Integer.parseInt(valueDayBegin), Integer.parseInt(valueMonthBegin),
                            Integer.parseInt(valueYearBegin), Integer.parseInt(valueHourBegin), Integer.parseInt(valueMinuteBegin),
                            ((SpinnerObject) sp_nhacnho.getSelectedItem()).getTime());

                    suKienHelper.insertSuKien(et_tensukien.getText().toString().trim(), et_diadiem.getText().toString().trim(), et_chitiet.getText().toString().trim(),
                            Integer.parseInt(valueDayBegin), Integer.parseInt(valueMonthBegin), Integer.parseInt(valueYearBegin), Integer.parseInt(valueHourBegin), Integer.parseInt(valueMinuteBegin),
                            Integer.parseInt(valueDayEnd), Integer.parseInt(valueMonthEnd), Integer.parseInt(valueYearEnd), Integer.parseInt(valueHourEnd), Integer.parseInt(valueMinuteEnd), calendar1.get(Calendar.DAY_OF_MONTH),
                            calendar1.get(Calendar.MONTH), calendar1.get(Calendar.YEAR),((SpinnerObject) sp_nhacnho.getSelectedItem()).getTime(), timeShow);

                    Toast.makeText(getApplicationContext(), "Created event successfully", Toast.LENGTH_LONG).show();
                    onBackPressed();
                } else {
                    Toast.makeText(getApplicationContext(), "Created not event successfully", Toast.LENGTH_SHORT).show();
                }
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
        int positionMonthBegin = month_bd.getCurrentItem();
        int totalDayOfMonthBegin = daysOfMonth[positionMonthBegin];
        CalendarUtil calendarUtil = new CalendarUtil();
        if (calendarUtil.checkLeap(Integer.parseInt(years[year_bd.getCurrentItem()]))) {
            if (positionMonthBegin == 1) {
                totalDayOfMonthBegin++;
            }
        }
        int current_day_begin_select = day_bd.getCurrentItem();
        if ((current_day_begin_select + 1) > totalDayOfMonthBegin) {
            current_day_begin_select = totalDayOfMonthBegin - 1;
        }
        day_bd.setCurrentItem(current_day_begin_select);
        updateDaysBD(year_bd, month_bd, day_bd);

        int positionMonthEnd = month_kt.getCurrentItem();
        int totalDayOfMonthEnd = daysOfMonth[positionMonthEnd];
        if (calendarUtil.checkLeap(Integer.parseInt(years[year_kt.getCurrentItem()]))) {
            if (totalDayOfMonthEnd == 1) {
                totalDayOfMonthEnd++;
            }
        }
        int current_day_end_select = day_kt.getCurrentItem();
        if ((current_day_end_select + 1) > totalDayOfMonthEnd) {
            current_day_end_select = totalDayOfMonthEnd - 1;
        }
        day_kt.setCurrentItem(current_day_end_select);
        updateDaysKT(year_kt, month_kt, day_kt);
    }

    private void updateStatus() {
        int positionMonthBegin = month_bd.getCurrentItem();
        valueMonthBegin = months[positionMonthBegin];

        int positionMonthEnd = month_kt.getCurrentItem();
        valueMonthEnd = months[positionMonthEnd];

        int positionDayBegin = day_bd.getCurrentItem();
        valueDayBegin = days[positionDayBegin];

        int positionDayEnd = day_kt.getCurrentItem();
        valueDayEnd = days[positionDayEnd];

        int positionYearBatDau = year_bd.getCurrentItem();
        valueYearBegin = years[positionYearBatDau];

        int positionYearEnd = year_kt.getCurrentItem();
        valueYearEnd = years[positionYearEnd];

        int positionHourBegin = hour_bd.getCurrentItem();
        valueHourBegin = hours[positionHourBegin];

        int positionHourEnd = hour_kt.getCurrentItem();
        valueHourEnd = hours[positionHourEnd];

        int positionMinuteBegin = minute_bd.getCurrentItem();
        valueMinuteBegin = minutes[positionMinuteBegin];

        int positionMinuteEnd = minute_kt.getCurrentItem();
        valueMinuteEnd = minutes[positionMinuteEnd];

        dateStart = valueDayBegin + "/" + valueMonthBegin + "/" + valueYearBegin;
        dateStop = valueDayEnd + "/" + valueMonthEnd + "/" + valueYearEnd;

        timeStart = valueHourBegin + ":" + valueMinuteBegin;
        timeStop = valueHourEnd + ":" + valueMinuteEnd;

        tv_batdau.setText(dateStart);
        tv_ketthuc.setText(dateStop);

        tv_time_batdau.setText(timeStart);
        tv_time_ketthuc.setText(timeStop);

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
