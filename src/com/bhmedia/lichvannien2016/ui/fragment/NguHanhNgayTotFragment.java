package com.bhmedia.lichvannien2016.ui.fragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.R.string;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bhmedia.lichvannien2016.R;
import com.bhmedia.lichvannien2016.ui.activity.KetQuaNHNTActivity;
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
public class NguHanhNgayTotFragment extends BaseFragment implements View.OnClickListener {
    Context context;
    private int NoOfYear = 115;
    private int YesOfYear = 35;
    String months[] = new String[]{"1", "2", "3", "4", "5",
            "6", "7", "8", "9", "10", "11", "12"};
    String[] years;
    String[] years2;
    String[] days;
    // Wheel scrolled flag
    private boolean wheelScrolled = false;
    WheelView day_batdau, month_batdau, year_batdau, day_ketthuc, month_ketthuc, year_kethuc;
    ImageView ivkqsaogiaihan;
    Calendar calendar,calendar2;
    int dduong, mduong, yduong, timeZone;
    int leap = 0, leap1 = 0;
    String valueMonthBegin, valueDayBegin, valueYearBegin, valueMonthEnd, valueDayEnd, valueYearEnd;
    DateArrayAdapter arrayAdapterDayBD, arrayAdapterMonthBD, arrayAdapterYearBD, arrayAdapterDayKT, arrayAdapterMonthKT, arrayAdapterYearKT;
    int DAY = 0, MONTH = 1, YEAR = 2;
    CalendarUtil calendarUtil;
    public static String thongtin;

    String g_arrMenh[][] = {
    		{
    		"Thạch lựu mộc (Cây thạch lựu)",
    		"Thạch lựu mộc (Cây thạch lựu)",
    		"Thoa xuyến kim (Vàng trang sức)",
    		"Thoa xuyến kim (Vàng trang sức)",
    		"Bích thượng thổ (Đất trên vách)",
    		"Bích thượng thổ (Đất trên vách)",
    		"Tùng bách mộc (Cây tùng bách)",
    		"Tùng bách mộc (Cây tùng bách)",
    		"Bạch lạp kim (Vàng trong nến rắn)",
    		"Bạch lạp kim (Vàng trong nến rắn)",
    		"Lộ bàng thổ (Đất giữa đường)",
    		"Lộ bàng thổ (Đất giữa đường)"
    		},

    		{
    		"Thạch lựu mộc (Cây thạch lựu)",
    		"Thạch lựu mộc (Cây thạch lựu)",
    		"Thoa xuyến kim (Vàng trang sức)",
    		"Thoa xuyến kim (Vàng trang sức)",
    		"Bích thượng thổ (Đất trên vách)",
    		"Bích thượng thổ (Đất trên vách)",
    		"Tùng bách mộc (Cây tùng bách)",
    		"Tùng bách mộc (Cây tùng bách)",
    		"Bạch lạp kim (Vàng trong nến rắn)",
    		"Bạch lạp kim (Vàng trong nến rắn)",
    		"Lộ bàng thổ (Đất giữa đường)",
    		"Lộ bàng thổ (Đất giữa đường)"
    		},

    		{
    		"Kiếm phong kim (Vàng đầu mũi kiếm)",
    		"Kiếm phong kim (Vàng đầu mũi kiếm)",
    		"Đại hải thủy (Nước đại dương)",
    		"Đại hải thủy (Nước đại dương)",
    		"Tang đố mộc (Gỗ cây dâu)",
    		"Tang đố mộc (Gỗ cây dâu)",
    		"Kim bạch kim (Vàng pha bạch kim)",
    		"Kim bạch kim (Vàng pha bạch kim)",
    		"Trường lưu thủy (Dòng nước lớn)",
    		"Trường lưu thủy (Dòng nước lớn)",
    		"Dương liễu mộc (Cây dương liễu)",
    		"Dương liễu mộc (Cây dương liễu)"
    		},

    		{
    		"Kiếm phong kim (Vàng đầu mũi kiếm)",
    		"Kiếm phong kim (Vàng đầu mũi kiếm)",
    		"Đại hải thủy (Nước đại dương)",
    		"Đại hải thủy (Nước đại dương)",
    		"Tang đố mộc (Gỗ cây dâu)",
    		"Tang đố mộc (Gỗ cây dâu)",
    		"Kim bạch kim (Vàng pha bạch kim)",
    		"Kim bạch kim (Vàng pha bạch kim)",
    		"Trường lưu thủy (Dòng nước lớn)",
    		"Trường lưu thủy (Dòng nước lớn)",
    		"Dương liễu mộc (Cây dương liễu)",
    		"Dương liễu mộc (Cây dương liễu)"
    		},

    		{
    		"Tuyền trung thủy (Dưới giữa dòng suối)",
    		"Tuyền trung thủy (Dưới giữa dòng suối)",
    		"Sơn đầu hỏa (Lửa trên núi)",
    		"Sơn đầu hỏa (Lửa trên núi)",
    		"Hải trung kim (Vàng dưới biển)",
    		"Hải trung kim (Vàng dưới biển)",
    		"Đại khê thủy (Nước dưới khe lớn)",
    		"Đại khê thủy (Nước dưới khe lớn)",
    		"Hú đăng hỏa (Lửa ngọn đèn)",
    		"Hú đăng hỏa (Lửa ngọn đèn)",
    		"Sa trung kim (Vàng trong cát)",
    		"Sa trung kim (Vàng trong cát)"
    		},

    		{
    		"Tuyền trung thủy (Dưới giữa dòng suối)",
    		"Tuyền trung thủy (Dưới giữa dòng suối)",
    		"Sơn đầu hỏa (Lửa trên núi)",
    		"Sơn đầu hỏa (Lửa trên núi)",
    		"Hải trung kim (Vàng dưới biển)",
    		"Hải trung kim (Vàng dưới biển)",
    		"Đại khê thủy (Nước dưới khe lớn)",
    		"Đại khê thủy (Nước dưới khe lớn)",
    		"Hú đăng hỏa (Lửa ngọn đèn)",
    		"Hú đăng hỏa (Lửa ngọn đèn)",
    		"Sa trung kim (Vàng trong cát)",
    		"Sa trung kim (Vàng trong cát)"
    		},

    		{
    		"Sơn hạ hỏa (Lửa dưới chân núi)",
    		"Sơn hạ hỏa (Lửa dưới chân núi)",
    		"Ốc thượng thổ (Đất trên nóc nhà)",
    		"Ốc thượng thổ (Đất trên nóc nhà)",
    		"Giản hạ thủy (Nước dưới khe)",
    		"Giản hạ thủy (Nước dưới khe)",
    		"Lộ trung hỏa (Lửa trong lò)",
    		"Lộ trung hỏa (Lửa trong lò)",
    		"Sa trung thổ (Đất lẫn trong cát)",
    		"Sa trung thổ (Đất lẫn trong cát)",
    		"Thiên hà thủy (Nước trên trời)",
    		"Thiên hà thủy (Nước trên trời)"
    		},

    		{
    		"Sơn hạ hỏa (Lửa dưới chân núi)",
    		"Sơn hạ hỏa (Lửa dưới chân núi)",
    		"Ốc thượng thổ (Đất trên nóc nhà)",
    		"Ốc thượng thổ (Đất trên nóc nhà)",
    		"Giản hạ thủy (Nước dưới khe)",
    		"Giản hạ thủy (Nước dưới khe)",
    		"Lộ trung hỏa (Lửa trong lò)",
    		"Lộ trung hỏa (Lửa trong lò)",
    		"Sa trung thổ (Đất lẫn trong cát)",
    		"Sa trung thổ (Đất lẫn trong cát)",
    		"Thiên hà thủy (Nước trên trời)",
    		"Thiên hà thủy (Nước trên trời)"
    		},

    		{
    		"Đại trạch thổ (Đất thuộc khe lớn)",
    		"Đại trạch thổ (Đất thuộc khe lớn)",
    		"Bình địa mộc (Cây ở đồng bằng)",
    		"Bình địa mộc (Cây ở đồng bằng)",
    		"Tích lịch hỏa (Lửa sấm sét)",
    		"Tích lịch hỏa (Lửa sấm sét)",
    		"Thành đầu thổ (Đất trên thành)",
    		"Thành đầu thổ (Đất trên thành)",
    		"Đại lâm mộc (Cây trong rừng lớn)",
    		"Đại lâm mộc (Cây trong rừng lớn)",
    		"Thiên thượng hỏa (Lửa trên trời)",
    		"Thiên thượng hỏa (Lửa trên trời)"},
    		{
    		"Đại trạch thổ (Đất thuộc khe lớn)",
    		"Đại trạch thổ (Đất thuộc khe lớn)",
    		"Bình địa mộc (Cây ở đồng bằng)",
    		"Bình địa mộc (Cây ở đồng bằng)",
    		"Tích lịch hỏa (Lửa sấm sét)",
    		"Tích lịch hỏa (Lửa sấm sét)",
    		"Thành đầu thổ (Đất trên thành)",
    		"Thành đầu thổ (Đất trên thành)",
    		"Đại lâm mộc (Cây trong rừng lớn)",
    		"Đại lâm mộc (Cây trong rừng lớn)",
    		"Thiên thượng hỏa (Lửa trên trời)",
    		"Thiên thượng hỏa (Lửa trên trời)"}
    		};
    //Param Can Chi
    String CANCHI_CAN1 = "Giáp";
    String CANCHI_CAN2 = "Ất";
    String CANCHI_CAN3 = "Bính";
    String CANCHI_CAN4 = "Đinh";
    String CANCHI_CAN5 = "Mậu";
    String CANCHI_CAN6 = "Kỷ";
    String CANCHI_CAN7 = "Canh";
    String CANCHI_CAN8 = "Tân";
    String CANCHI_CAN9 = "Nhâm";
    String CANCHI_CAN10 = "Quý";

    String CANCHI_CHI1 = "Tý";
    String CANCHI_CHI2 = "Sửu";
    String CANCHI_CHI3 = "Dần";
    String CANCHI_CHI4 = "Mão";
    String CANCHI_CHI5 = "Thìn";
    String CANCHI_CHI6 = "Tỵ";
    String CANCHI_CHI7 = "Ngọ";
    String CANCHI_CHI8 = "Mùi";
    String CANCHI_CHI9 = "Thân";
    String CANCHI_CHI10 = "Dậu";
    String CANCHI_CHI11 = "Tuất";
    String CANCHI_CHI12 = "Hợi";
    
    @Override
    protected int getLayoutId() {
        return R.layout.layout_fragment_nhnt;
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

        ivkqsaogiaihan = (ImageView) findViewById(R.id.iv_ketqua_saogiaihan);
        ivkqsaogiaihan.setOnClickListener(this);

        calendar = Calendar.getInstance();
        calendar2=Calendar.getInstance();
        calendar2.set(Calendar.MONTH,6);
        calendar2.set(Calendar.YEAR,1987);
        calendar2.set(Calendar.DAY_OF_MONTH,24);
        day_batdau = (WheelView) findViewById(R.id.day_batdau);
        month_batdau = (WheelView) findViewById(R.id.month_batdau);
        year_batdau = (WheelView) findViewById(R.id.year_batdau);

        day_ketthuc = (WheelView) findViewById(R.id.day_ketthuc);
        month_ketthuc = (WheelView) findViewById(R.id.month_ketthuc);
        year_kethuc = (WheelView) findViewById(R.id.year_ketthuc);

        // month
        int curMonth2 = calendar2.get(Calendar.MONTH);
        arrayAdapterMonthBD = new DateArrayAdapter(context, months, curMonth2);
        month_batdau.setViewAdapter(arrayAdapterMonthBD);
        month_batdau.setVisibleItems(2);
        month_batdau.setCurrentItem(curMonth2);
        month_batdau.addChangingListener(changedListener);
        month_batdau.addScrollingListener(scrolledListener);

        int curMonth = calendar.get(Calendar.MONTH);
        arrayAdapterMonthKT = new DateArrayAdapter(context, months, curMonth);
        month_ketthuc.setViewAdapter(arrayAdapterMonthKT);
        month_ketthuc.setVisibleItems(2);
        month_ketthuc.setCurrentItem(curMonth);
        month_ketthuc.addChangingListener(changedListener);
        month_ketthuc.addScrollingListener(scrolledListener);

        Calendar cal = Calendar.getInstance();
        // year
        int curYear = calendar.get(Calendar.YEAR);
        int curYear2 = calendar2.get(Calendar.YEAR);
        int Year = cal.get(Calendar.YEAR);
        int Year2 = calendar2.get(Calendar.YEAR);

        years = getListString(Year - NoOfYear, Year + YesOfYear);
        years2 = getListString(Year2 - NoOfYear, Year2 + YesOfYear);

        arrayAdapterYearBD = new DateArrayAdapter(context, years2, NoOfYear);
        year_batdau.setViewAdapter(arrayAdapterYearBD);
        year_batdau.setVisibleItems(2);
        year_batdau.setCurrentItem(curYear2 - (Year2 - NoOfYear));
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
        day_batdau.setCurrentItem(calendar2.get(Calendar.DAY_OF_MONTH) - 1);
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
                    day_batdau.setCurrentItem(dayCurr - 1);
                    month_batdau.setCurrentItem(monthCurr - 1);
                    year_batdau.setCurrentItem(yearCurr - 1900);

                    day_ketthuc.setCurrentItem(dayCurr - 1);
                    month_ketthuc.setCurrentItem(monthCurr - 1);
                    year_kethuc.setCurrentItem(yearCurr - 1900);
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
        arrayAdapterDayBD = new DateArrayAdapter(context, days, calendar2
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

    public String formatStringDate(int numbers) {
    	String str = String.valueOf(numbers);
    	if (numbers < 10) {
    		str = "0" + String.valueOf(numbers);
		}
    	return str;
    }
    
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.iv_ketqua_saogiaihan: 
//        	 String dateStart = valueYearBegin + "/" + valueMonthBegin + "/" + valueDayBegin;
//             String dateStop = valueYearEnd + "/" + valueMonthEnd + "/" + valueDayEnd;
        	//int ngay thang nam duong lich
        	int ngayBegin = Integer.parseInt(valueDayBegin);
        	int thangBegin = Integer.parseInt(valueMonthBegin);
        	int namBegin = Integer.parseInt(valueYearBegin);

        	int ngayEnd = Integer.parseInt(valueDayEnd);
        	int thangEnd = Integer.parseInt(valueMonthEnd);
        	int namEnd = Integer.parseInt(valueYearEnd);

        	//tính ngày tháng năm âm lịch
			Calendar calendarBegin = Calendar.getInstance();
			int[] convertBegin = calendarUtil.convertSolar2Lunar(ngayBegin, thangBegin,
					namBegin, 7);
			calendarBegin.set(Calendar.DAY_OF_MONTH, convertBegin[0]);
			calendarBegin.set(Calendar.MONTH, convertBegin[1] - 1);
			calendarBegin.set(Calendar.YEAR, convertBegin[2]);
        	
			Calendar calendarEnd = Calendar.getInstance();
			int[] convertEnd = calendarUtil.convertSolar2Lunar(ngayEnd, thangEnd,
					namEnd, 7);
			calendarEnd.set(Calendar.DAY_OF_MONTH, convertEnd[0]);
			calendarEnd.set(Calendar.MONTH, convertEnd[1] - 1);
			calendarEnd.set(Calendar.YEAR, convertEnd[2]);
			
			//tính mệnh begin
		    int namSinh1 = calendarBegin.get(Calendar.YEAR);
		    int canSinh = namSinh1%10;
		    int chiSinh = namSinh1%12;
		    String cungMenhSinh = g_arrMenh[canSinh][chiSinh];

			//tính mệnh end
		    int namXem1 = 0;
		    int[] canchiNgayXem = calendarUtil.intGetCanChiOfDay(ngayEnd, thangEnd, namEnd);
		    int canngayxem = canchiNgayXem[0];
		    int chingayxem = canchiNgayXem[1];
		    int icount = 1;
		    while (namXem1 == 0) {
		        int intNum1 = canngayxem + icount*10 - 6;
		        
		        int intNum2Mod = (intNum1+8)%12;
		        if (intNum2Mod == chingayxem) {
		            namXem1 = intNum1;
		        }
		        icount++;
		    }
		    int canXem = namXem1%10;
		    int chiXem = namXem1%12;
		    String cungMenhXem = g_arrMenh[canXem][chiXem];

		    //tính xung khắc mệnh
		    String arrXungKhac[] = {"xung khắc", "tương hòa", "tương hợp"};
		    String xungKhacMenh = "";
		    String tenMenhNgay = cungMenhXem.split(" ")[2];
		    String tenMenhTuoi = cungMenhSinh.split(" ")[2];
		    int typesMenh = nguHanhMenhNgay(tenMenhNgay, tenMenhTuoi);
		    xungKhacMenh = arrXungKhac[typesMenh];

		    //tính xung khắc chi
		    String xungKhacChi = "";
		    String ngayXemTenNgay = calendarUtil.getCanChiOfDay(ngayEnd, thangEnd, namEnd);
		    String tenChiNgay = ngayXemTenNgay.split(" ")[1];
		    String tenChiTuoi = calendarUtil.getChiOfYear(calendarBegin.get(Calendar.YEAR));
		    int typesChi = chiMenhNgayMenhTuoi(tenChiNgay, tenChiTuoi);
		    xungKhacChi = arrXungKhac[typesChi];

		    //tính xung khắc can
		    String xungKhacCan = "";
		    String tenCanNgay = ngayXemTenNgay.split(" ")[0];
		    String tenCanTuoi = calendarUtil.getCanOfYear(calendarBegin
					.get(Calendar.YEAR));
		    int typesCan = canMenhNgayMenhTuoi(tenCanNgay, tenCanTuoi);
		    xungKhacCan = arrXungKhac[typesCan];

		    //ket luan
		    int tongdiem = typesMenh + typesChi + typesCan;
		    String ngayTotXau = "";
		    if (tongdiem >= 5) {
		        ngayTotXau = "rất tốt";
		    }
		    else if (tongdiem >= 4){
		        ngayTotXau = "tốt";
		    }
		    else if (tongdiem >= 2){
		        ngayTotXau = "bình thường";
		    }
		    else{
		        ngayTotXau = "xấu";
		    }


             thongtin = "- Bạn sinh ngày: " + formatStringDate(ngayBegin) + "-" 
             + formatStringDate(thangBegin) + "-" + String.valueOf(namBegin) + " dương lịch<br />Tức: "
             + formatStringDate(calendarBegin.get(Calendar.DAY_OF_MONTH)) + "-" + formatStringDate((calendarBegin.get(Calendar.MONTH) + 1))
             +  "-" + String.valueOf(calendarBegin.get(Calendar.YEAR)) + " âm lịch, năm " + calendarUtil.getCanOfYear(calendarBegin
						.get(Calendar.YEAR)) + " " + calendarUtil.getChiOfYear(calendarBegin.get(Calendar.YEAR)) + ", Mệnh tuổi "
						+ cungMenhSinh + "<br /><br />- Ngày cần xem: " + formatStringDate(ngayEnd) + "-" 
					             + formatStringDate(thangEnd) + "-" + String.valueOf(namEnd) + " dương lịch\nTức: " 
						+ formatStringDate(calendarEnd.get(Calendar.DAY_OF_MONTH)) + "-" + formatStringDate((calendarEnd.get(Calendar.MONTH) + 1)) 
						+  "-" + String.valueOf(calendarEnd.get(Calendar.YEAR)) + " âm lịch, ngày " 
						+ calendarUtil.getCanChiOfDay(ngayEnd, thangEnd, namEnd) + ", Mệnh ngày " + cungMenhXem + "<br /><br /><b>Thông tin ngày theo tuổi:</b><br />Mệnh ngày " 
								+ cungMenhXem + " và mệnh tuổi " + cungMenhSinh + " " + xungKhacMenh + ". Hàng chi (" + tenChiNgay + "-" + tenChiTuoi 
								+ ") " + xungKhacChi + ".Hàng can (" + tenCanNgay + "-" + tenCanTuoi + ") " + xungKhacCan + ".<br /><br /><b>Kết luận: Ngày này " + ngayTotXau 
								+ " đối với bạn.</b>";
             
             Intent it=new Intent(getActivity(),KetQuaNHNTActivity.class);
             startActivity(it);
             
//             Toast.makeText(getActivity(), thongtin, Toast.LENGTH_LONG).show();

          break;
//            case R.id.rb_duongLich_demNgay:
//                switchAmDuong(true);
//                break;
//            case R.id.rb_amLich_demNgay:
//                switchAmDuong(false);
//                break;
        }
    }
    //tính tương hợp của 2 mệnh (ngày xem và ngày sinh)
    private int nguHanhMenhNgay(String tenMenhNgay, String tenMenhTuoi) {
        int types = 1; //0 la xung, 1 la tuong hoa, 2 la hop
        String kimNH = "kim";
        String mocNH = "mộc";
        String thuyNH = "thủy";
        String hoaNH = "hỏa";
        String thoNH = "thổ";

        //xung
        if ((tenMenhNgay.equals(kimNH) && tenMenhTuoi.equals(mocNH)) || (tenMenhTuoi.equals(kimNH) && tenMenhNgay.equals(mocNH))) {
            types = 0;
        }
        else if ((tenMenhNgay.equals(thuyNH) && tenMenhTuoi.equals(hoaNH)) || (tenMenhTuoi.equals(thuyNH) && tenMenhNgay.equals(hoaNH))) {
            types = 0;
        }
        else if ((tenMenhNgay.equals(hoaNH) && tenMenhTuoi.equals(kimNH)) || (tenMenhTuoi.equals(hoaNH) && tenMenhNgay.equals(kimNH))) {
            types = 0;
        }
        else if ((tenMenhNgay.equals(thoNH) && tenMenhTuoi.equals(thuyNH)) || (tenMenhTuoi.equals(thoNH) && tenMenhNgay.equals(thuyNH))) {
            types = 0;
        }

        //hop
        if ((tenMenhNgay.equals(kimNH) && tenMenhTuoi.equals(thuyNH)) || (tenMenhTuoi.equals(kimNH) && tenMenhNgay.equals(thuyNH))) {
            types = 2;
        }
        else if ((tenMenhNgay.equals(thuyNH) && tenMenhTuoi.equals(mocNH)) || (tenMenhTuoi.equals(thuyNH) && tenMenhNgay.equals(mocNH))) {
            types = 2;
        }
        else if ((tenMenhNgay.equals(mocNH) && tenMenhTuoi.equals(hoaNH)) || (tenMenhTuoi.equals(mocNH) && tenMenhNgay.equals(hoaNH))) {
            types = 2;
        }
        else if ((tenMenhNgay.equals(hoaNH) && tenMenhTuoi.equals(thoNH)) || (tenMenhTuoi.equals(hoaNH) && tenMenhNgay.equals(thoNH))) {
            types = 2;
        }
        else if ((tenMenhNgay.equals(thoNH) && tenMenhTuoi.equals(kimNH)) || (tenMenhTuoi.equals(thoNH) && tenMenhNgay.equals(kimNH))) {
            types = 2;
        }

        return types;
    }
    //tính tương hợp của 2 chi (ngày xem và ngày sinh)
    private int chiMenhNgayMenhTuoi(String tenChiNgay, String tenChiTuoi) {
    	int types = 1; //0 la xung, 1 la tuong hoa, 2 la hop
    	
    	//xung
        if ((tenChiNgay.equals(CANCHI_CHI1) && tenChiTuoi.equals(CANCHI_CHI7)) || (tenChiTuoi.equals(CANCHI_CHI1) && tenChiNgay.equals(CANCHI_CHI7))) {
            types = 0;
        }
        else if ((tenChiNgay.equals(CANCHI_CHI2) && tenChiTuoi.equals(CANCHI_CHI8)) || (tenChiTuoi.equals(CANCHI_CHI2) && tenChiNgay.equals(CANCHI_CHI8))) {
            types = 0;
        }
        else if ((tenChiNgay.equals(CANCHI_CHI3) && tenChiTuoi.equals(CANCHI_CHI9)) || (tenChiTuoi.equals(CANCHI_CHI3) && tenChiNgay.equals(CANCHI_CHI9))) {
            types = 0;
        }
        else if ((tenChiNgay.equals(CANCHI_CHI4) && tenChiTuoi.equals(CANCHI_CHI10)) || (tenChiTuoi.equals(CANCHI_CHI4) && tenChiNgay.equals(CANCHI_CHI10))) {
            types = 0;
        }
        else if ((tenChiNgay.equals(CANCHI_CHI6) && tenChiTuoi.equals(CANCHI_CHI12)) || (tenChiTuoi.equals(CANCHI_CHI6) && tenChiNgay.equals(CANCHI_CHI12))) {
            types = 0;
        }

        //hop
        if ((tenChiNgay.equals(CANCHI_CHI1) && tenChiTuoi.equals(CANCHI_CHI2)) || (tenChiTuoi.equals(CANCHI_CHI1) && tenChiNgay.equals(CANCHI_CHI2))) {
            types = 2;
        }
        else if ((tenChiNgay.equals(CANCHI_CHI4) && tenChiTuoi.equals(CANCHI_CHI11)) || (tenChiTuoi.equals(CANCHI_CHI4) && tenChiNgay.equals(CANCHI_CHI11))) {
            types = 2;
        }
        else if ((tenChiNgay.equals(CANCHI_CHI6) && tenChiTuoi.equals(CANCHI_CHI9)) || (tenChiTuoi.equals(CANCHI_CHI6) && tenChiNgay.equals(CANCHI_CHI9))) {
            types = 2;
        }
        else if ((tenChiNgay.equals(CANCHI_CHI3) && tenChiTuoi.equals(CANCHI_CHI12)) || (tenChiTuoi.equals(CANCHI_CHI3) && tenChiNgay.equals(CANCHI_CHI12))) {
            types = 2;
        }
        else if ((tenChiNgay.equals(CANCHI_CHI5) && tenChiTuoi.equals(CANCHI_CHI10)) || (tenChiTuoi.equals(CANCHI_CHI5) && tenChiNgay.equals(CANCHI_CHI10))) {
            types = 2;
        }
        else if ((tenChiNgay.equals(CANCHI_CHI7) && tenChiTuoi.equals(CANCHI_CHI8)) || (tenChiTuoi.equals(CANCHI_CHI7) && tenChiNgay.equals(CANCHI_CHI8))) {
            types = 2;
        }
        
        return types;
    }
    //tính tương hợp của 2 can (ngày xem và ngày sinh)
    private int canMenhNgayMenhTuoi(String tenCanNgay, String tenCanTuoi) {
        int types = 1; //0 la xung, 1 la tuong hoa, 2 la hop

      //xung
        if ((tenCanNgay.equals(CANCHI_CAN1) && tenCanTuoi.equals(CANCHI_CAN7)) || (tenCanTuoi.equals(CANCHI_CAN1) && tenCanNgay.equals(CANCHI_CAN7))) {
            types = 0;
        }
        else if ((tenCanNgay.equals(CANCHI_CAN2) && tenCanTuoi.equals(CANCHI_CAN8)) || (tenCanTuoi.equals(CANCHI_CAN2) && tenCanNgay.equals(CANCHI_CAN8))) {
            types = 0;
        }
        else if ((tenCanNgay.equals(CANCHI_CAN3) && tenCanTuoi.equals(CANCHI_CAN9)) || (tenCanTuoi.equals(CANCHI_CAN3) && tenCanNgay.equals(CANCHI_CAN9))) {
            types = 0;
        }
        else if ((tenCanNgay.equals(CANCHI_CAN4) && tenCanTuoi.equals(CANCHI_CAN10)) || (tenCanTuoi.equals(CANCHI_CAN4) && tenCanNgay.equals(CANCHI_CAN10))) {
            types = 0;
        }

        //hop
        if ((tenCanNgay.equals(CANCHI_CAN2) && tenCanTuoi.equals(CANCHI_CAN7)) || (tenCanTuoi.equals(CANCHI_CAN2) && tenCanNgay.equals(CANCHI_CAN7))) {
            types = 2;
        }
        else if ((tenCanNgay.equals(CANCHI_CAN3) && tenCanTuoi.equals(CANCHI_CAN8)) || (tenCanTuoi.equals(CANCHI_CAN3) && tenCanNgay.equals(CANCHI_CAN8))) {
            types = 2;
        }
        else if ((tenCanNgay.equals(CANCHI_CAN4) && tenCanTuoi.equals(CANCHI_CAN9)) || (tenCanTuoi.equals(CANCHI_CAN4) && tenCanNgay.equals(CANCHI_CAN9))) {
            types = 2;
        }
        else if ((tenCanNgay.equals(CANCHI_CAN5) && tenCanTuoi.equals(CANCHI_CAN10)) || (tenCanTuoi.equals(CANCHI_CAN5) && tenCanNgay.equals(CANCHI_CAN10))) {
            types = 2;
        }
        else if ((tenCanNgay.equals(CANCHI_CAN6) && tenCanTuoi.equals(CANCHI_CAN1)) || (tenCanTuoi.equals(CANCHI_CAN6) && tenCanNgay.equals(CANCHI_CAN1))) {
            types = 2;
        }
        
        return types;
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

//        if (rb_duongLich_demNgay.isChecked()) {
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
//            tv_demngay.setText(diffDays + "");
//        } else if (rb_amLich_demNgay.isChecked()) {
//            int totalDayOfMonthALBD = getDaysMonth(Integer.parseInt(valueMonthBegin), Integer.parseInt(valueYearBegin));
//            int current_day_select_bd = day_batdau.getCurrentItem();
//            if ((current_day_select_bd + 1) > totalDayOfMonthALBD) {
//                current_day_select_bd = totalDayOfMonthALBD - 1;
//            }
//            day_batdau.setCurrentItem(current_day_select_bd);
//            updateDaysALBD(totalDayOfMonthALBD);
//
//            int totalDayOfMonthALKT = getDaysMonth(Integer.parseInt(valueMonthEnd), Integer.parseInt(valueYearEnd));
//            int current_day_select_kt = day_ketthuc.getCurrentItem();
//            if ((current_day_select_kt + 1) > totalDayOfMonthALKT) {
//                current_day_select_kt = totalDayOfMonthALKT - 1;
//            }
//            day_ketthuc.setCurrentItem(current_day_select_kt);
//            updateDaysALKT(totalDayOfMonthALKT);
//
//            int[] convert = calendarUtil.convertLunar2Solar(Integer.parseInt(valueDayBegin),
//                    Integer.parseInt(valueMonthBegin), Integer.parseInt(valueYearBegin), leap, 7);
//            int[] convert1 = calendarUtil.convertLunar2Solar(Integer.parseInt(valueDayEnd),
//                    Integer.parseInt(valueMonthEnd), Integer.parseInt(valueYearEnd), leap, 7);
//            String dateStart = convert[2] + "/" + convert[1] + "/" + convert[0];
//            String dateStop = convert1[2] + "/" + convert1[1] + "/" + convert1[0];
//            // Custom date format
//            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
//            Date d1 = null;
//            Date d2 = null;
//            try {
//                d1 = format.parse(dateStart);
//                d2 = format.parse(dateStop);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//            // Get msec from each, and subtract.
//            long diff = d2.getTime() - d1.getTime();
//            long diffDays = diff / (24 * 60 * 60 * 1000);
//            tv_demngay.setText(diffDays + "");
//        }
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

