package com.bhmedia.lichvannien2016.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import com.bhmedia.lichvannien2016.R;
import com.bhmedia.lichvannien2016.ui.activity.MainActivity;
import com.bhmedia.lichvannien2016.ui.utility.BroadCastManager;
import com.bhmedia.lichvannien2016.ui.utility.CalendarUtil;


/**
 * Created by Van on 1/20/2015.
 */
public class ChiTietNgayFragment extends BaseFragment {
    TextView tv_tietKhi, tv_tuoiXungKhac, tv_huongXuatHanh, tv_gioHoangDao;
    WebView wv_saoTotXau;
    Calendar calendar;
    Bundle mBundle;
    int dd, mm, yy;

    @Override
    protected int getLayoutId() {
        return R.layout.layout_fragment_chitietngay;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    protected void onInitializeView(Bundle savedInstanceState) {
        super.onInitializeView(savedInstanceState);
        tv_gioHoangDao = (TextView) findViewById(R.id.tv_gioHoangDao);
        tv_tietKhi = (TextView) findViewById(R.id.tv_tietKhi);
        tv_tuoiXungKhac = (TextView) findViewById(R.id.tv_tuoiXungKhac);
        tv_huongXuatHanh = (TextView) findViewById(R.id.tv_huongXuatHanh);
        wv_saoTotXau = (WebView) findViewById(R.id.wv_saoTotXau);

        calendar = Calendar.getInstance();
        dd = calendar.get(Calendar.DAY_OF_MONTH);
        mm = calendar.get(Calendar.MONTH) + 1;
        yy = calendar.get(Calendar.YEAR);
        CalendarUtil calendarUtil = new CalendarUtil();
        tv_gioHoangDao.setText(calendarUtil.getGioHoangDao(dd, mm, yy));
        tv_tietKhi.setText(calendarUtil.getTietKhi(dd, mm, yy));
        tv_tuoiXungKhac.setText(calendarUtil.getTuoiXungKhac(dd, mm, yy));
        tv_huongXuatHanh.setText(calendarUtil.getHuongXuatHanh(dd, mm, yy));
        int[] convert = calendarUtil.convertSolar2Lunar(dd, mm, yy, 7);
        calendar.set(Calendar.DAY_OF_MONTH, convert[0]);
        String str = calendarUtil.getSaoTotXau(dd, mm, yy, convert[0], convert[1]);
        String html = "<html><body>" + str + "</body></html>";
        wv_saoTotXau.getSettings().setJavaScriptEnabled(true);
        wv_saoTotXau.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);

        mBundle = getArguments();
        if (mBundle != null) {
            tv_gioHoangDao.setText(calendarUtil.getGioHoangDao(mBundle.getInt("NGAY"), mBundle.getInt("THANG"), mBundle.getInt("NAM")));
            tv_tietKhi.setText(calendarUtil.getTietKhi(mBundle.getInt("NGAY"), mBundle.getInt("THANG"), mBundle.getInt("NAM")));
            tv_tuoiXungKhac.setText(calendarUtil.getTuoiXungKhac(mBundle.getInt("NGAY"), mBundle.getInt("THANG"), mBundle.getInt("NAM")));
            tv_huongXuatHanh.setText(calendarUtil.getHuongXuatHanh(mBundle.getInt("NGAY"), mBundle.getInt("THANG"), mBundle.getInt("NAM")));

            int[] convert1 = calendarUtil.convertSolar2Lunar(mBundle.getInt("NGAY"), mBundle.getInt("THANG"), mBundle.getInt("NAM"), 7);

            String str1 = calendarUtil.getSaoTotXau(mBundle.getInt("NGAY"), mBundle.getInt("THANG"), mBundle.getInt("NAM"),
                    convert1[0],convert1[1]);
            String html1 = "<html><body>" + str1 + "</body></html>";
            wv_saoTotXau.getSettings().setJavaScriptEnabled(true);
            wv_saoTotXau.loadDataWithBaseURL(null, html1, "text/html", "utf-8", null);

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


}
