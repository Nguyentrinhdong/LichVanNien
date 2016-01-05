package com.bhmedia.lichvannien2016.ui.activity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bhmedia.frame.utils.RootSupport;
import com.bhmedia.lichvannien2016.R;
import com.bhmedia.lichvannien2016.ui.fragment.LichNgayFragment;
import com.bhmedia.lichvannien2016.ui.http.request.RequestApi;
import com.bhmedia.lichvannien2016.ui.http.response.GetWeatherResponse;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.mediation.admob.AdMobExtras;


/**
 * Created by Van on 4/21/2015.
 */
public class DuBaoThoiTietActivity extends FragmentActivity implements
		View.OnClickListener {
	ImageView iv_CurrWeather, iv_weatherSang1, iv_weatherSang2,
			iv_weatherSang3, iv_weatherSang4, iv_weatherSang5, iv_weatherSang6,
			iv_weatherSang7, iv_weatherTrua1, iv_weatherTrua2, iv_weatherTrua3,
			iv_weatherTrua4, iv_weatherTrua5, iv_weatherTrua6, iv_weatherTrua7,
			iv_weatherChieu1, iv_weatherChieu2, iv_weatherChieu3,
			iv_weatherChieu4, iv_weatherChieu5, iv_weatherChieu6,
			iv_weatherChieu7, iv_weatherDem1, iv_weatherDem2, iv_weatherDem3,
			iv_weatherDem4, iv_weatherDem5, iv_weatherDem6, iv_weatherDem7;
	TextView tv_location_wearther, tv_timeCurrent_wearther, tv_dateCurrent,
			tv_CurrdoC, tv_locationCurr_doAm, tv_locationCurr_tamNhin,
			tv_locationCurr_Apsuat, tv_locationCurr_tocDoGio;
	TextView tv_date1, tv_timeBinhMinh1, tv_timeHoangHon1, tv_nhietdoThapNhat1,
			tv_nhietdoCaoNhat1, tv_date2, tv_timeBinhMinh2, tv_timeHoangHon2,
			tv_nhietdoThapNhat2, tv_nhietdoCaoNhat2, tv_date3,
			tv_timeBinhMinh3, tv_timeHoangHon3, tv_nhietdoThapNhat3,
			tv_nhietdoCaoNhat3, tv_date4, tv_timeBinhMinh4, tv_timeHoangHon4,
			tv_nhietdoThapNhat4, tv_nhietdoCaoNhat4, tv_date5,
			tv_timeBinhMinh5, tv_timeHoangHon5, tv_nhietdoThapNhat5,
			tv_nhietdoCaoNhat5, tv_date6, tv_timeBinhMinh6, tv_timeHoangHon6,
			tv_nhietdoThapNhat6, tv_nhietdoCaoNhat6, tv_date7,
			tv_timeBinhMinh7, tv_timeHoangHon7, tv_nhietdoThapNhat7,
			tv_nhietdoCaoNhat7;
	TextView tv_doC_weatherSang1, tv_doC_weatherSang2, tv_doC_weatherSang3,
			tv_doC_weatherSang4, tv_doC_weatherSang5, tv_doC_weatherSang6,
			tv_doC_weatherSang7, tv_doC_weatherChieu1, tv_doC_weatherChieu2,
			tv_doC_weatherChieu3, tv_doC_weatherChieu4, tv_doC_weatherChieu5,
			tv_doC_weatherChieu6, tv_doC_weatherChieu7, tv_doC_weatherTrua1,
			tv_doC_weatherTrua2, tv_doC_weatherTrua3, tv_doC_weatherTrua4,
			tv_doC_weatherTrua5, tv_doC_weatherTrua6, tv_doC_weatherTrua7,
			tv_doC_weatherDem1, tv_doC_weatherDem2, tv_doC_weatherDem3,
			tv_doC_weatherDem4, tv_doC_weatherDem5, tv_doC_weatherDem6,
			tv_doC_weatherDem7;
	TextView tv_doAm_weatherSang1, tv_doAm_weatherSang2, tv_doAm_weatherSang3,
			tv_doAm_weatherSang4, tv_doAm_weatherSang5, tv_doAm_weatherSang6,
			tv_doAm_weatherSang7, tv_doAm_weatherTrua1, tv_doAm_weatherTrua2,
			tv_doAm_weatherTrua3, tv_doAm_weatherTrua4, tv_doAm_weatherTrua5,
			tv_doAm_weatherTrua6, tv_doAm_weatherTrua7, tv_doAm_weatherChieu1,
			tv_doAm_weatherChieu2, tv_doAm_weatherChieu3,
			tv_doAm_weatherChieu4, tv_doAm_weatherChieu5,
			tv_doAm_weatherChieu6, tv_doAm_weatherChieu7, tv_doAm_weatherDem1,
			tv_doAm_weatherDem2, tv_doAm_weatherDem3, tv_doAm_weatherDem4,
			tv_doAm_weatherDem5, tv_doAm_weatherDem6, tv_doAm_weatherDem7;
	TextView tv_tamNhin_weatherSang1, tv_tamNhin_weatherSang2,
			tv_tamNhin_weatherSang3, tv_tamNhin_weatherSang4,
			tv_tamNhin_weatherSang5, tv_tamNhin_weatherSang6,
			tv_tamNhin_weatherSang7, tv_tamNhin_weatherTrua1,
			tv_tamNhin_weatherTrua2, tv_tamNhin_weatherTrua3,
			tv_tamNhin_weatherTrua4, tv_tamNhin_weatherTrua5,
			tv_tamNhin_weatherTrua6, tv_tamNhin_weatherTrua7,
			tv_tamNhin_weatherChieu1, tv_tamNhin_weatherChieu2,
			tv_tamNhin_weatherChieu3, tv_tamNhin_weatherChieu4,
			tv_tamNhin_weatherChieu5, tv_tamNhin_weatherChieu6,
			tv_tamNhin_weatherChieu7, tv_tamNhin_weatherDem1,
			tv_tamNhin_weatherDem2, tv_tamNhin_weatherDem3,
			tv_tamNhin_weatherDem4, tv_tamNhin_weatherDem5,
			tv_tamNhin_weatherDem6, tv_tamNhin_weatherDem7;
	TextView tv_apSuat_weatherSang1, tv_apSuat_weatherSang2,
			tv_apSuat_weatherSang3, tv_apSuat_weatherSang4,
			tv_apSuat_weatherSang5, tv_apSuat_weatherSang6,
			tv_apSuat_weatherSang7, tv_apSuat_weatherTrua1,
			tv_apSuat_weatherTrua2, tv_apSuat_weatherTrua3,
			tv_apSuat_weatherTrua4, tv_apSuat_weatherTrua5,
			tv_apSuat_weatherTrua6, tv_apSuat_weatherTrua7,
			tv_apSuat_weatherChieu1, tv_apSuat_weatherChieu2,
			tv_apSuat_weatherChieu3, tv_apSuat_weatherChieu4,
			tv_apSuat_weatherChieu5, tv_apSuat_weatherChieu6,
			tv_apSuat_weatherChieu7, tv_apSuat_weatherDem1,
			tv_apSuat_weatherDem2, tv_apSuat_weatherDem3,
			tv_apSuat_weatherDem4, tv_apSuat_weatherDem5,
			tv_apSuat_weatherDem6, tv_apSuat_weatherDem7;
	TextView tv_tocDoGio_weatherSang1, tv_tocDoGio_weatherSang2,
			tv_tocDoGio_weatherSang3, tv_tocDoGio_weatherSang4,
			tv_tocDoGio_weatherSang5, tv_tocDoGio_weatherSang6,
			tv_tocDoGio_weatherSang7, tv_tocDoGio_weatherTrua1,
			tv_tocDoGio_weatherTrua2, tv_tocDoGio_weatherTrua3,
			tv_tocDoGio_weatherTrua4, tv_tocDoGio_weatherTrua5,
			tv_tocDoGio_weatherTrua6, tv_tocDoGio_weatherTrua7,
			tv_tocDoGio_weatherChieu1, tv_tocDoGio_weatherChieu2,
			tv_tocDoGio_weatherChieu3, tv_tocDoGio_weatherChieu4,
			tv_tocDoGio_weatherChieu5, tv_tocDoGio_weatherChieu6,
			tv_tocDoGio_weatherChieu7, tv_tocDoGio_weatherDem1,
			tv_tocDoGio_weatherDem2, tv_tocDoGio_weatherDem3,
			tv_tocDoGio_weatherDem4, tv_tocDoGio_weatherDem5,
			tv_tocDoGio_weatherDem6, tv_tocDoGio_weatherDem7;

	ImageView iv_tinhthanh, iv_back;
	private AdView mAdView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_fragment_weather);
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
		iv_back = (ImageView) findViewById(R.id.iv_back);
		iv_tinhthanh = (ImageView) findViewById(R.id.iv_tinhthanh);
		iv_back.setOnClickListener(this);
		iv_tinhthanh.setOnClickListener(this);
		iv_CurrWeather = (ImageView) findViewById(R.id.iv_CurrWeather);
		iv_weatherSang1 = (ImageView) findViewById(R.id.iv_weatherSang1);
		iv_weatherSang2 = (ImageView) findViewById(R.id.iv_weatherSang2);
		iv_weatherSang3 = (ImageView) findViewById(R.id.iv_weatherSang3);
		iv_weatherSang4 = (ImageView) findViewById(R.id.iv_weatherSang4);
		iv_weatherSang5 = (ImageView) findViewById(R.id.iv_weatherSang5);
		iv_weatherSang6 = (ImageView) findViewById(R.id.iv_weatherSang6);
		iv_weatherSang7 = (ImageView) findViewById(R.id.iv_weatherSang7);
		iv_weatherTrua1 = (ImageView) findViewById(R.id.iv_weatherTrua1);
		iv_weatherTrua2 = (ImageView) findViewById(R.id.iv_weatherTrua2);
		iv_weatherTrua3 = (ImageView) findViewById(R.id.iv_weatherTrua3);
		iv_weatherTrua4 = (ImageView) findViewById(R.id.iv_weatherTrua4);
		iv_weatherTrua5 = (ImageView) findViewById(R.id.iv_weatherTrua5);
		iv_weatherTrua6 = (ImageView) findViewById(R.id.iv_weatherTrua6);
		iv_weatherTrua7 = (ImageView) findViewById(R.id.iv_weatherTrua7);
		iv_weatherChieu1 = (ImageView) findViewById(R.id.iv_weatherChieu1);
		iv_weatherChieu2 = (ImageView) findViewById(R.id.iv_weatherChieu2);
		iv_weatherChieu3 = (ImageView) findViewById(R.id.iv_weatherChieu3);
		iv_weatherChieu4 = (ImageView) findViewById(R.id.iv_weatherChieu4);
		iv_weatherChieu5 = (ImageView) findViewById(R.id.iv_weatherChieu5);
		iv_weatherChieu6 = (ImageView) findViewById(R.id.iv_weatherChieu6);
		iv_weatherChieu7 = (ImageView) findViewById(R.id.iv_weatherChieu7);
		iv_weatherDem1 = (ImageView) findViewById(R.id.iv_weatherDem1);
		iv_weatherDem2 = (ImageView) findViewById(R.id.iv_weatherDem2);
		iv_weatherDem3 = (ImageView) findViewById(R.id.iv_weatherDem3);
		iv_weatherDem4 = (ImageView) findViewById(R.id.iv_weatherDem4);
		iv_weatherDem5 = (ImageView) findViewById(R.id.iv_weatherDem5);
		iv_weatherDem6 = (ImageView) findViewById(R.id.iv_weatherDem6);
		iv_weatherDem7 = (ImageView) findViewById(R.id.iv_weatherDem7);

		tv_location_wearther = (TextView) findViewById(R.id.tv_location_wearther);
		Bundle mBundle = getIntent().getExtras().getBundle("Bundle");
		tv_location_wearther.setText(mBundle.getString("DIACHI"));
		tv_timeCurrent_wearther = (TextView) findViewById(R.id.tv_timeCurrent_wearther);
		tv_dateCurrent = (TextView) findViewById(R.id.tv_dateCurrent);
		tv_CurrdoC = (TextView) findViewById(R.id.tv_CurrdoC);
		tv_locationCurr_doAm = (TextView) findViewById(R.id.tv_locationCurr_doAm);
		tv_locationCurr_tamNhin = (TextView) findViewById(R.id.tv_locationCurr_tamNhin);
		tv_locationCurr_Apsuat = (TextView) findViewById(R.id.tv_locationCurr_Apsuat);
		tv_locationCurr_tocDoGio = (TextView) findViewById(R.id.tv_locationCurr_tocDoGio);

		tv_date1 = (TextView) findViewById(R.id.tv_date1);
		tv_timeBinhMinh1 = (TextView) findViewById(R.id.tv_timeBinhMinh1);
		tv_timeHoangHon1 = (TextView) findViewById(R.id.tv_timeHoangHon1);
		tv_nhietdoThapNhat1 = (TextView) findViewById(R.id.tv_nhietdoThapNhat1);
		tv_nhietdoCaoNhat1 = (TextView) findViewById(R.id.tv_nhietdoCaoNhat1);
		tv_date2 = (TextView) findViewById(R.id.tv_date2);
		tv_timeBinhMinh2 = (TextView) findViewById(R.id.tv_timeBinhMinh2);
		tv_timeHoangHon2 = (TextView) findViewById(R.id.tv_timeHoangHon2);
		tv_nhietdoThapNhat2 = (TextView) findViewById(R.id.tv_nhietdoThapNhat2);
		tv_nhietdoCaoNhat2 = (TextView) findViewById(R.id.tv_nhietdoCaoNhat2);
		tv_date3 = (TextView) findViewById(R.id.tv_date3);
		tv_timeBinhMinh3 = (TextView) findViewById(R.id.tv_timeBinhMinh3);
		tv_timeHoangHon3 = (TextView) findViewById(R.id.tv_timeHoangHon3);
		tv_nhietdoThapNhat3 = (TextView) findViewById(R.id.tv_nhietdoThapNhat3);
		tv_nhietdoCaoNhat3 = (TextView) findViewById(R.id.tv_nhietdoCaoNhat3);
		tv_date4 = (TextView) findViewById(R.id.tv_date4);
		tv_timeBinhMinh4 = (TextView) findViewById(R.id.tv_timeBinhMinh4);
		tv_timeHoangHon4 = (TextView) findViewById(R.id.tv_timeHoangHon4);
		tv_nhietdoThapNhat4 = (TextView) findViewById(R.id.tv_nhietdoThapNhat4);
		tv_nhietdoCaoNhat4 = (TextView) findViewById(R.id.tv_nhietdoCaoNhat4);
		tv_date5 = (TextView) findViewById(R.id.tv_date5);
		tv_timeBinhMinh5 = (TextView) findViewById(R.id.tv_timeBinhMinh5);
		tv_timeHoangHon5 = (TextView) findViewById(R.id.tv_timeHoangHon5);
		tv_nhietdoThapNhat5 = (TextView) findViewById(R.id.tv_nhietdoThapNhat5);
		tv_nhietdoCaoNhat5 = (TextView) findViewById(R.id.tv_nhietdoCaoNhat5);
		tv_date6 = (TextView) findViewById(R.id.tv_date6);
		tv_timeBinhMinh6 = (TextView) findViewById(R.id.tv_timeBinhMinh6);
		tv_timeHoangHon6 = (TextView) findViewById(R.id.tv_timeHoangHon6);
		tv_nhietdoThapNhat6 = (TextView) findViewById(R.id.tv_nhietdoThapNhat6);
		tv_nhietdoCaoNhat6 = (TextView) findViewById(R.id.tv_nhietdoCaoNhat6);
		tv_date7 = (TextView) findViewById(R.id.tv_date7);
		tv_timeBinhMinh7 = (TextView) findViewById(R.id.tv_timeBinhMinh7);
		tv_timeHoangHon7 = (TextView) findViewById(R.id.tv_timeHoangHon7);
		tv_nhietdoThapNhat7 = (TextView) findViewById(R.id.tv_nhietdoThapNhat7);
		tv_nhietdoCaoNhat7 = (TextView) findViewById(R.id.tv_nhietdoCaoNhat7);

		tv_doC_weatherSang1 = (TextView) findViewById(R.id.tv_doC_weatherSang1);
		tv_doC_weatherSang2 = (TextView) findViewById(R.id.tv_doC_weatherSang2);
		tv_doC_weatherSang3 = (TextView) findViewById(R.id.tv_doC_weatherSang3);
		tv_doC_weatherSang4 = (TextView) findViewById(R.id.tv_doC_weatherSang4);
		tv_doC_weatherSang5 = (TextView) findViewById(R.id.tv_doC_weatherSang5);
		tv_doC_weatherSang6 = (TextView) findViewById(R.id.tv_doC_weatherSang6);
		tv_doC_weatherSang7 = (TextView) findViewById(R.id.tv_doC_weatherSang7);
		tv_doC_weatherChieu1 = (TextView) findViewById(R.id.tv_doC_weatherChieu1);
		tv_doC_weatherChieu2 = (TextView) findViewById(R.id.tv_doC_weatherChieu2);
		tv_doC_weatherChieu3 = (TextView) findViewById(R.id.tv_doC_weatherChieu3);
		tv_doC_weatherChieu4 = (TextView) findViewById(R.id.tv_doC_weatherChieu4);
		tv_doC_weatherChieu5 = (TextView) findViewById(R.id.tv_doC_weatherChieu5);
		tv_doC_weatherChieu6 = (TextView) findViewById(R.id.tv_doC_weatherChieu6);
		tv_doC_weatherChieu7 = (TextView) findViewById(R.id.tv_doC_weatherChieu7);
		tv_doC_weatherTrua1 = (TextView) findViewById(R.id.tv_doC_weatherTrua1);
		tv_doC_weatherTrua2 = (TextView) findViewById(R.id.tv_doC_weatherTrua2);
		tv_doC_weatherTrua3 = (TextView) findViewById(R.id.tv_doC_weatherTrua3);
		tv_doC_weatherTrua4 = (TextView) findViewById(R.id.tv_doC_weatherTrua4);
		tv_doC_weatherTrua5 = (TextView) findViewById(R.id.tv_doC_weatherTrua5);
		tv_doC_weatherTrua6 = (TextView) findViewById(R.id.tv_doC_weatherTrua6);
		tv_doC_weatherTrua7 = (TextView) findViewById(R.id.tv_doC_weatherTrua7);
		tv_doC_weatherDem1 = (TextView) findViewById(R.id.tv_doC_weatherDem1);
		tv_doC_weatherDem2 = (TextView) findViewById(R.id.tv_doC_weatherDem2);
		tv_doC_weatherDem3 = (TextView) findViewById(R.id.tv_doC_weatherDem3);
		tv_doC_weatherDem4 = (TextView) findViewById(R.id.tv_doC_weatherDem4);
		tv_doC_weatherDem5 = (TextView) findViewById(R.id.tv_doC_weatherDem5);
		tv_doC_weatherDem6 = (TextView) findViewById(R.id.tv_doC_weatherDem6);
		tv_doC_weatherDem7 = (TextView) findViewById(R.id.tv_doC_weatherDem7);

		tv_doAm_weatherSang1 = (TextView) findViewById(R.id.tv_doAm_weatherSang1);
		tv_doAm_weatherSang2 = (TextView) findViewById(R.id.tv_doAm_weatherSang2);
		tv_doAm_weatherSang3 = (TextView) findViewById(R.id.tv_doAm_weatherSang3);
		tv_doAm_weatherSang4 = (TextView) findViewById(R.id.tv_doAm_weatherSang4);
		tv_doAm_weatherSang5 = (TextView) findViewById(R.id.tv_doAm_weatherSang5);
		tv_doAm_weatherSang6 = (TextView) findViewById(R.id.tv_doAm_weatherSang6);
		tv_doAm_weatherSang7 = (TextView) findViewById(R.id.tv_doAm_weatherSang7);
		tv_doAm_weatherTrua1 = (TextView) findViewById(R.id.tv_doAm_weatherTrua1);
		tv_doAm_weatherTrua2 = (TextView) findViewById(R.id.tv_doAm_weatherTrua2);
		tv_doAm_weatherTrua3 = (TextView) findViewById(R.id.tv_doAm_weatherTrua3);
		tv_doAm_weatherTrua4 = (TextView) findViewById(R.id.tv_doAm_weatherTrua4);
		tv_doAm_weatherTrua5 = (TextView) findViewById(R.id.tv_doAm_weatherTrua5);
		tv_doAm_weatherTrua6 = (TextView) findViewById(R.id.tv_doAm_weatherTrua6);
		tv_doAm_weatherTrua7 = (TextView) findViewById(R.id.tv_doAm_weatherTrua7);
		tv_doAm_weatherChieu1 = (TextView) findViewById(R.id.tv_doAm_weatherChieu1);
		tv_doAm_weatherChieu2 = (TextView) findViewById(R.id.tv_doAm_weatherChieu2);
		tv_doAm_weatherChieu3 = (TextView) findViewById(R.id.tv_doAm_weatherChieu3);
		tv_doAm_weatherChieu4 = (TextView) findViewById(R.id.tv_doAm_weatherChieu4);
		tv_doAm_weatherChieu5 = (TextView) findViewById(R.id.tv_doAm_weatherChieu5);
		tv_doAm_weatherChieu6 = (TextView) findViewById(R.id.tv_doAm_weatherChieu6);
		tv_doAm_weatherChieu7 = (TextView) findViewById(R.id.tv_doAm_weatherChieu7);
		tv_doAm_weatherDem1 = (TextView) findViewById(R.id.tv_doAm_weatherDem1);
		tv_doAm_weatherDem2 = (TextView) findViewById(R.id.tv_doAm_weatherDem2);
		tv_doAm_weatherDem3 = (TextView) findViewById(R.id.tv_doAm_weatherDem3);
		tv_doAm_weatherDem4 = (TextView) findViewById(R.id.tv_doAm_weatherDem4);
		tv_doAm_weatherDem5 = (TextView) findViewById(R.id.tv_doAm_weatherDem5);
		tv_doAm_weatherDem6 = (TextView) findViewById(R.id.tv_doAm_weatherDem6);
		tv_doAm_weatherDem7 = (TextView) findViewById(R.id.tv_doAm_weatherDem7);

		tv_tamNhin_weatherSang1 = (TextView) findViewById(R.id.tv_tamNhin_weatherSang1);
		tv_tamNhin_weatherSang2 = (TextView) findViewById(R.id.tv_tamNhin_weatherSang2);
		tv_tamNhin_weatherSang3 = (TextView) findViewById(R.id.tv_tamNhin_weatherSang3);
		tv_tamNhin_weatherSang4 = (TextView) findViewById(R.id.tv_tamNhin_weatherSang4);
		tv_tamNhin_weatherSang5 = (TextView) findViewById(R.id.tv_tamNhin_weatherSang5);
		tv_tamNhin_weatherSang6 = (TextView) findViewById(R.id.tv_tamNhin_weatherSang6);
		tv_tamNhin_weatherSang7 = (TextView) findViewById(R.id.tv_tamNhin_weatherSang7);
		tv_tamNhin_weatherTrua1 = (TextView) findViewById(R.id.tv_tamNhin_weatherTrua1);
		tv_tamNhin_weatherTrua2 = (TextView) findViewById(R.id.tv_tamNhin_weatherTrua2);
		tv_tamNhin_weatherTrua3 = (TextView) findViewById(R.id.tv_tamNhin_weatherTrua3);
		tv_tamNhin_weatherTrua4 = (TextView) findViewById(R.id.tv_tamNhin_weatherTrua4);
		tv_tamNhin_weatherTrua5 = (TextView) findViewById(R.id.tv_tamNhin_weatherTrua5);
		tv_tamNhin_weatherTrua6 = (TextView) findViewById(R.id.tv_tamNhin_weatherTrua6);
		tv_tamNhin_weatherTrua7 = (TextView) findViewById(R.id.tv_tamNhin_weatherTrua7);
		tv_tamNhin_weatherChieu1 = (TextView) findViewById(R.id.tv_tamNhin_weatherChieu1);
		tv_tamNhin_weatherChieu2 = (TextView) findViewById(R.id.tv_tamNhin_weatherChieu2);
		tv_tamNhin_weatherChieu3 = (TextView) findViewById(R.id.tv_tamNhin_weatherChieu3);
		tv_tamNhin_weatherChieu4 = (TextView) findViewById(R.id.tv_tamNhin_weatherChieu4);
		tv_tamNhin_weatherChieu5 = (TextView) findViewById(R.id.tv_tamNhin_weatherChieu5);
		tv_tamNhin_weatherChieu6 = (TextView) findViewById(R.id.tv_tamNhin_weatherChieu6);
		tv_tamNhin_weatherChieu7 = (TextView) findViewById(R.id.tv_tamNhin_weatherChieu7);
		tv_tamNhin_weatherDem1 = (TextView) findViewById(R.id.tv_tamNhin_weatherDem1);
		tv_tamNhin_weatherDem2 = (TextView) findViewById(R.id.tv_tamNhin_weatherDem2);
		tv_tamNhin_weatherDem3 = (TextView) findViewById(R.id.tv_tamNhin_weatherDem3);
		tv_tamNhin_weatherDem4 = (TextView) findViewById(R.id.tv_tamNhin_weatherDem4);
		tv_tamNhin_weatherDem5 = (TextView) findViewById(R.id.tv_tamNhin_weatherDem5);
		tv_tamNhin_weatherDem6 = (TextView) findViewById(R.id.tv_tamNhin_weatherDem6);
		tv_tamNhin_weatherDem7 = (TextView) findViewById(R.id.tv_tamNhin_weatherDem7);

		tv_apSuat_weatherSang1 = (TextView) findViewById(R.id.tv_apSuat_weatherSang1);
		tv_apSuat_weatherSang2 = (TextView) findViewById(R.id.tv_apSuat_weatherSang2);
		tv_apSuat_weatherSang3 = (TextView) findViewById(R.id.tv_apSuat_weatherSang3);
		tv_apSuat_weatherSang4 = (TextView) findViewById(R.id.tv_apSuat_weatherSang4);
		tv_apSuat_weatherSang5 = (TextView) findViewById(R.id.tv_apSuat_weatherSang5);
		tv_apSuat_weatherSang6 = (TextView) findViewById(R.id.tv_apSuat_weatherSang6);
		tv_apSuat_weatherSang7 = (TextView) findViewById(R.id.tv_apSuat_weatherSang7);
		tv_apSuat_weatherTrua1 = (TextView) findViewById(R.id.tv_apSuat_weatherTrua1);
		tv_apSuat_weatherTrua2 = (TextView) findViewById(R.id.tv_apSuat_weatherTrua2);
		tv_apSuat_weatherTrua3 = (TextView) findViewById(R.id.tv_apSuat_weatherTrua3);
		tv_apSuat_weatherTrua4 = (TextView) findViewById(R.id.tv_apSuat_weatherTrua4);
		tv_apSuat_weatherTrua5 = (TextView) findViewById(R.id.tv_apSuat_weatherTrua5);
		tv_apSuat_weatherTrua6 = (TextView) findViewById(R.id.tv_apSuat_weatherTrua6);
		tv_apSuat_weatherTrua7 = (TextView) findViewById(R.id.tv_apSuat_weatherTrua7);
		tv_apSuat_weatherChieu1 = (TextView) findViewById(R.id.tv_apSuat_weatherChieu1);
		tv_apSuat_weatherChieu2 = (TextView) findViewById(R.id.tv_apSuat_weatherChieu2);
		tv_apSuat_weatherChieu3 = (TextView) findViewById(R.id.tv_apSuat_weatherChieu3);
		tv_apSuat_weatherChieu4 = (TextView) findViewById(R.id.tv_apSuat_weatherChieu4);
		tv_apSuat_weatherChieu5 = (TextView) findViewById(R.id.tv_apSuat_weatherChieu5);
		tv_apSuat_weatherChieu6 = (TextView) findViewById(R.id.tv_apSuat_weatherChieu6);
		tv_apSuat_weatherChieu7 = (TextView) findViewById(R.id.tv_apSuat_weatherChieu7);
		tv_apSuat_weatherDem1 = (TextView) findViewById(R.id.tv_apSuat_weatherDem1);
		tv_apSuat_weatherDem2 = (TextView) findViewById(R.id.tv_apSuat_weatherDem2);
		tv_apSuat_weatherDem3 = (TextView) findViewById(R.id.tv_apSuat_weatherDem3);
		tv_apSuat_weatherDem4 = (TextView) findViewById(R.id.tv_apSuat_weatherDem4);
		tv_apSuat_weatherDem5 = (TextView) findViewById(R.id.tv_apSuat_weatherDem5);
		tv_apSuat_weatherDem6 = (TextView) findViewById(R.id.tv_apSuat_weatherDem6);
		tv_apSuat_weatherDem7 = (TextView) findViewById(R.id.tv_apSuat_weatherDem7);

		tv_tocDoGio_weatherSang1 = (TextView) findViewById(R.id.tv_tocDoGio_weatherSang1);
		tv_tocDoGio_weatherSang2 = (TextView) findViewById(R.id.tv_tocDoGio_weatherSang2);
		tv_tocDoGio_weatherSang3 = (TextView) findViewById(R.id.tv_tocDoGio_weatherSang3);
		tv_tocDoGio_weatherSang4 = (TextView) findViewById(R.id.tv_tocDoGio_weatherSang4);
		tv_tocDoGio_weatherSang5 = (TextView) findViewById(R.id.tv_tocDoGio_weatherSang5);
		tv_tocDoGio_weatherSang6 = (TextView) findViewById(R.id.tv_tocDoGio_weatherSang6);
		tv_tocDoGio_weatherSang7 = (TextView) findViewById(R.id.tv_tocDoGio_weatherSang7);
		tv_tocDoGio_weatherTrua1 = (TextView) findViewById(R.id.tv_tocDoGio_weatherTrua1);
		tv_tocDoGio_weatherTrua2 = (TextView) findViewById(R.id.tv_tocDoGio_weatherTrua2);
		tv_tocDoGio_weatherTrua3 = (TextView) findViewById(R.id.tv_tocDoGio_weatherTrua3);
		tv_tocDoGio_weatherTrua4 = (TextView) findViewById(R.id.tv_tocDoGio_weatherTrua4);
		tv_tocDoGio_weatherTrua5 = (TextView) findViewById(R.id.tv_tocDoGio_weatherTrua5);
		tv_tocDoGio_weatherTrua6 = (TextView) findViewById(R.id.tv_tocDoGio_weatherTrua6);
		tv_tocDoGio_weatherTrua7 = (TextView) findViewById(R.id.tv_tocDoGio_weatherTrua7);
		tv_tocDoGio_weatherChieu1 = (TextView) findViewById(R.id.tv_tocDoGio_weatherChieu1);
		tv_tocDoGio_weatherChieu2 = (TextView) findViewById(R.id.tv_tocDoGio_weatherChieu2);
		tv_tocDoGio_weatherChieu3 = (TextView) findViewById(R.id.tv_tocDoGio_weatherChieu3);
		tv_tocDoGio_weatherChieu4 = (TextView) findViewById(R.id.tv_tocDoGio_weatherChieu4);
		tv_tocDoGio_weatherChieu5 = (TextView) findViewById(R.id.tv_tocDoGio_weatherChieu5);
		tv_tocDoGio_weatherChieu6 = (TextView) findViewById(R.id.tv_tocDoGio_weatherChieu6);
		tv_tocDoGio_weatherChieu7 = (TextView) findViewById(R.id.tv_tocDoGio_weatherChieu7);
		tv_tocDoGio_weatherDem1 = (TextView) findViewById(R.id.tv_tocDoGio_weatherDem1);
		tv_tocDoGio_weatherDem2 = (TextView) findViewById(R.id.tv_tocDoGio_weatherDem2);
		tv_tocDoGio_weatherDem3 = (TextView) findViewById(R.id.tv_tocDoGio_weatherDem3);
		tv_tocDoGio_weatherDem4 = (TextView) findViewById(R.id.tv_tocDoGio_weatherDem4);
		tv_tocDoGio_weatherDem5 = (TextView) findViewById(R.id.tv_tocDoGio_weatherDem5);
		tv_tocDoGio_weatherDem6 = (TextView) findViewById(R.id.tv_tocDoGio_weatherDem6);
		tv_tocDoGio_weatherDem7 = (TextView) findViewById(R.id.tv_tocDoGio_weatherDem7);

		updateCurrentWeather();
	}

	private Runnable loadData = new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			updateCurrentWeather();
		}
	};
	private void updateCurrentWeather(){
		String str1 = MainActivity.listCurrentCondition.get(0).getWeatherCode();
		String status1 = MainActivity.listCurrentCondition.get(0)
				.getIsdaytime();
		switch (status1) {
		case "yes":
			switch (str1) {
			case "113":
				iv_CurrWeather.setImageResource(R.drawable.day_sm_113);
				break;
			case "116":
				iv_CurrWeather.setImageResource(R.drawable.day_sm_116);
				break;
			case "119":
				iv_CurrWeather.setImageResource(R.drawable.day_sm_119);
				break;
			case "122":
				iv_CurrWeather.setImageResource(R.drawable.day_sm_122);
				break;
			case "143":
				iv_CurrWeather.setImageResource(R.drawable.day_sm_143);
				break;
			case "176":
				iv_CurrWeather.setImageResource(R.drawable.day_sm_176);
				break;
			case "179":
				iv_CurrWeather.setImageResource(R.drawable.day_sm_179);
				break;
			case "182":
				iv_CurrWeather.setImageResource(R.drawable.day_sm_182);
				break;
			case "185":
				iv_CurrWeather.setImageResource(R.drawable.day_sm_185);
				break;
			case "200":
				iv_CurrWeather.setImageResource(R.drawable.day_sm_200);
				break;
			case "227":
				iv_CurrWeather.setImageResource(R.drawable.day_sm_227);
				break;
			case "230":
				iv_CurrWeather.setImageResource(R.drawable.day_sm_230);
				break;
			case "248":
				iv_CurrWeather.setImageResource(R.drawable.day_sm_248);
				break;
			case "260":
				iv_CurrWeather.setImageResource(R.drawable.day_sm_260);
				break;
			case "263":
				iv_CurrWeather.setImageResource(R.drawable.day_sm_263);
				break;
			case "266":
				iv_CurrWeather.setImageResource(R.drawable.day_sm_266);
				break;
			case "281":
				iv_CurrWeather.setImageResource(R.drawable.day_sm_281);
				break;
			case "284":
				iv_CurrWeather.setImageResource(R.drawable.day_sm_284);
				break;
			case "293":
				iv_CurrWeather.setImageResource(R.drawable.day_sm_293);
				break;
			case "296":
				iv_CurrWeather.setImageResource(R.drawable.day_sm_296);
				break;
			case "299":
				iv_CurrWeather.setImageResource(R.drawable.day_sm_299);
				break;
			case "302":
				iv_CurrWeather.setImageResource(R.drawable.day_sm_302);
				break;
			case "305":
				iv_CurrWeather.setImageResource(R.drawable.day_sm_305);
				break;
			case "308":
				iv_CurrWeather.setImageResource(R.drawable.day_sm_308);
				break;
			case "311":
				iv_CurrWeather.setImageResource(R.drawable.day_sm_311);
				break;
			case "314":
				iv_CurrWeather.setImageResource(R.drawable.day_sm_314);
				break;
			case "317":
				iv_CurrWeather.setImageResource(R.drawable.day_sm_317);
				break;
			case "320":
				iv_CurrWeather.setImageResource(R.drawable.day_sm_320);
				break;
			case "323":
				iv_CurrWeather.setImageResource(R.drawable.day_sm_323);
				break;
			case "326":
				iv_CurrWeather.setImageResource(R.drawable.day_sm_326);
				break;
			case "329":
				iv_CurrWeather.setImageResource(R.drawable.day_sm_329);
				break;
			case "332":
				iv_CurrWeather.setImageResource(R.drawable.day_sm_332);
				break;
			case "335":
				iv_CurrWeather.setImageResource(R.drawable.day_sm_335);
				break;
			case "338":
				iv_CurrWeather.setImageResource(R.drawable.day_sm_338);
				break;
			case "350":
				iv_CurrWeather.setImageResource(R.drawable.day_sm_350);
				break;
			case "353":
				iv_CurrWeather.setImageResource(R.drawable.day_sm_353);
				break;
			case "356":
				iv_CurrWeather.setImageResource(R.drawable.day_sm_356);
				break;
			case "359":
				iv_CurrWeather.setImageResource(R.drawable.day_sm_359);
				break;
			case "362":
				iv_CurrWeather.setImageResource(R.drawable.day_sm_362);
				break;
			case "365":
				iv_CurrWeather.setImageResource(R.drawable.day_sm_365);
				break;
			case "368":
				iv_CurrWeather.setImageResource(R.drawable.day_sm_368);
				break;
			case "371":
				iv_CurrWeather.setImageResource(R.drawable.day_sm_371);
				break;
			case "374":
				iv_CurrWeather.setImageResource(R.drawable.day_sm_374);
				break;
			case "377":
				iv_CurrWeather.setImageResource(R.drawable.day_sm_377);
				break;
			case "386":
				iv_CurrWeather.setImageResource(R.drawable.day_sm_386);
				break;
			case "389":
				iv_CurrWeather.setImageResource(R.drawable.day_sm_389);
				break;
			case "392":
				iv_CurrWeather.setImageResource(R.drawable.day_sm_392);
				break;
			case "395":
				iv_CurrWeather.setImageResource(R.drawable.day_sm_395);
				break;
			}
			break;
		case "no":
			switch (str1) {
			case "113":
				iv_CurrWeather.setImageResource(R.drawable.night_sm_113);
				break;
			case "116":
				iv_CurrWeather.setImageResource(R.drawable.night_sm_116);
				break;
			case "119":
				iv_CurrWeather.setImageResource(R.drawable.night_sm_119);
				break;
			case "122":
				iv_CurrWeather.setImageResource(R.drawable.night_sm_122);
				break;
			case "143":
				iv_CurrWeather.setImageResource(R.drawable.night_sm_143);
				break;
			case "176":
				iv_CurrWeather.setImageResource(R.drawable.night_sm_176);
				break;
			case "179":
				iv_CurrWeather.setImageResource(R.drawable.night_sm_179);
				break;
			case "182":
				iv_CurrWeather.setImageResource(R.drawable.night_sm_182);
				break;
			case "185":
				iv_CurrWeather.setImageResource(R.drawable.night_sm_185);
				break;
			case "200":
				iv_CurrWeather.setImageResource(R.drawable.night_sm_200);
				break;
			case "227":
				iv_CurrWeather.setImageResource(R.drawable.night_sm_227);
				break;
			case "230":
				iv_CurrWeather.setImageResource(R.drawable.night_sm_230);
				break;
			case "248":
				iv_CurrWeather.setImageResource(R.drawable.night_sm_248);
				break;
			case "260":
				iv_CurrWeather.setImageResource(R.drawable.night_sm_260);
				break;
			case "263":
				iv_CurrWeather.setImageResource(R.drawable.night_sm_263);
				break;
			case "266":
				iv_CurrWeather.setImageResource(R.drawable.night_sm_266);
				break;
			case "281":
				iv_CurrWeather.setImageResource(R.drawable.night_sm_281);
				break;
			case "284":
				iv_CurrWeather.setImageResource(R.drawable.night_sm_284);
				break;
			case "293":
				iv_CurrWeather.setImageResource(R.drawable.night_sm_293);
				break;
			case "296":
				iv_CurrWeather.setImageResource(R.drawable.night_sm_296);
				break;
			case "299":
				iv_CurrWeather.setImageResource(R.drawable.night_sm_299);
				break;
			case "302":
				iv_CurrWeather.setImageResource(R.drawable.night_sm_302);
				break;
			case "305":
				iv_CurrWeather.setImageResource(R.drawable.night_sm_305);
				break;
			case "308":
				iv_CurrWeather.setImageResource(R.drawable.night_sm_308);
				break;
			case "311":
				iv_CurrWeather.setImageResource(R.drawable.night_sm_311);
				break;
			case "314":
				iv_CurrWeather.setImageResource(R.drawable.night_sm_314);
				break;
			case "317":
				iv_CurrWeather.setImageResource(R.drawable.night_sm_317);
				break;
			case "320":
				iv_CurrWeather.setImageResource(R.drawable.night_sm_320);
				break;
			case "323":
				iv_CurrWeather.setImageResource(R.drawable.night_sm_323);
				break;
			case "326":
				iv_CurrWeather.setImageResource(R.drawable.night_sm_326);
				break;
			case "329":
				iv_CurrWeather.setImageResource(R.drawable.night_sm_329);
				break;
			case "332":
				iv_CurrWeather.setImageResource(R.drawable.night_sm_332);
				break;
			case "335":
				iv_CurrWeather.setImageResource(R.drawable.night_sm_335);
				break;
			case "338":
				iv_CurrWeather.setImageResource(R.drawable.night_sm_338);
				break;
			case "350":
				iv_CurrWeather.setImageResource(R.drawable.night_sm_350);
				break;
			case "353":
				iv_CurrWeather.setImageResource(R.drawable.night_sm_353);
				break;
			case "356":
				iv_CurrWeather.setImageResource(R.drawable.night_sm_356);
				break;
			case "359":
				iv_CurrWeather.setImageResource(R.drawable.night_sm_359);
				break;
			case "362":
				iv_CurrWeather.setImageResource(R.drawable.night_sm_362);
				break;
			case "365":
				iv_CurrWeather.setImageResource(R.drawable.night_sm_365);
				break;
			case "368":
				iv_CurrWeather.setImageResource(R.drawable.night_sm_368);
				break;
			case "371":
				iv_CurrWeather.setImageResource(R.drawable.night_sm_371);
				break;
			case "374":
				iv_CurrWeather.setImageResource(R.drawable.night_sm_374);
				break;
			case "377":
				iv_CurrWeather.setImageResource(R.drawable.night_sm_377);
				break;
			case "386":
				iv_CurrWeather.setImageResource(R.drawable.night_sm_386);
				break;
			case "389":
				iv_CurrWeather.setImageResource(R.drawable.night_sm_389);
				break;
			case "392":
				iv_CurrWeather.setImageResource(R.drawable.night_sm_392);
				break;
			case "395":
				iv_CurrWeather.setImageResource(R.drawable.night_sm_395);
				break;
			}
			break;
		}
		for (int i = 0; i <= 6; i++) {
			updateWeather(MainActivity.listWeather, i, 2);
			updateWeather(MainActivity.listWeather, i, 3);
			updateWeather(MainActivity.listWeather, i, 4);
			updateWeather(MainActivity.listWeather, i, 6);
		}

		String doF = MainActivity.listCurrentCondition.get(0).getTempF();
		int changeDoF = (int) ((Integer.parseInt(doF) - 32) / 1.8);
		tv_CurrdoC.setText(changeDoF + "°C" + "");
		tv_locationCurr_doAm.setText(MainActivity.listCurrentCondition.get(0)
				.getHumidity() + "%");
		tv_locationCurr_Apsuat.setText(MainActivity.listCurrentCondition.get(0)
				.getPressure() + "Mb");
		tv_locationCurr_tamNhin.setText(MainActivity.listCurrentCondition
				.get(0).getVisibility() + "Km");
		tv_locationCurr_tocDoGio.setText(MainActivity.listCurrentCondition.get(
				0).getWindspeedKmph()
				+ "Km/h");

		String timeCurr = MainActivity.listRequest.get(0).getLocalTime();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date parsedDate = null;
		try {
			parsedDate = dateFormat.parse(timeCurr);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar newDate = Calendar.getInstance();
		newDate.setTime(parsedDate);
		tv_timeCurrent_wearther.setText(newDate.get(Calendar.HOUR) + "h" + ":"
				+ newDate.get(Calendar.MINUTE) + "");
		tv_dateCurrent.setText(newDate.get(Calendar.DAY_OF_MONTH) + "/"
				+ (newDate.get(Calendar.MONTH) + 1) + "/"
				+ newDate.get(Calendar.YEAR) + "");

		String time1 = MainActivity.listWeather.get(0).getDate();
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		Date parsedDate1 = null;
		try {
			parsedDate1 = dateFormat1.parse(time1);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar newDate1 = Calendar.getInstance();
		newDate1.setTime(parsedDate1);
		tv_date1.setText("Ngày " + newDate1.get(Calendar.DAY_OF_MONTH)
				+ " tháng " + (newDate1.get(Calendar.MONTH) + 1) + " năm "
				+ newDate1.get(Calendar.YEAR));
		tv_timeBinhMinh1.setText(MainActivity.listWeather.get(0)
				.getAstronomyList().get(0).getSunrise());
		tv_timeHoangHon1.setText(MainActivity.listWeather.get(0)
				.getAstronomyList().get(0).getSunset());
		String doFMax1 = MainActivity.listWeather.get(0).getMaxTempF();
		String doFMin1 = MainActivity.listWeather.get(0).getMinTempF();
		int changeDoFMax1 = (int) ((Integer.parseInt(doFMax1) - 32) / 1.8);
		int changeDoFMin1 = (int) ((Integer.parseInt(doFMin1) - 32) / 1.8);
		tv_nhietdoCaoNhat1.setText(changeDoFMax1 + "°C" + "");
		tv_nhietdoThapNhat1.setText(changeDoFMin1 + "°C" + "");
		String doF1 = MainActivity.listWeather.get(0).getHourlyList().get(1)
				.getTempF();
		int changeDoF1 = (int) ((Integer.parseInt(doF1) - 32) / 1.8);
		tv_doC_weatherSang1.setText(changeDoF1 + "°C" + "");
		String doFTrua1 = MainActivity.listWeather.get(0).getHourlyList()
				.get(3).getTempF();
		int changeDoFTrua1 = (int) ((Integer.parseInt(doFTrua1) - 32) / 1.8);
		tv_doC_weatherTrua1.setText(changeDoFTrua1 + "°C" + "");
		String doFChieu1 = MainActivity.listWeather.get(0).getHourlyList()
				.get(5).getTempF();
		int changeDoFChieu1 = (int) ((Integer.parseInt(doFChieu1) - 32) / 1.8);
		tv_doC_weatherChieu1.setText(changeDoFChieu1 + "°C" + "");
		String doFDem1 = MainActivity.listWeather.get(0).getHourlyList().get(7)
				.getTempF();
		int changeDoFDem1 = (int) ((Integer.parseInt(doFDem1) - 32) / 1.8);
		tv_doC_weatherDem1.setText(changeDoFDem1 + "°C" + "");
		tv_doAm_weatherSang1.setText(MainActivity.listWeather.get(0)
				.getHourlyList().get(1).getHumidity()
				+ "%");
		tv_tamNhin_weatherSang1.setText(MainActivity.listWeather.get(0)
				.getHourlyList().get(1).getVisibility()
				+ "Km");
		tv_apSuat_weatherSang1.setText(MainActivity.listWeather.get(0)
				.getHourlyList().get(1).getPressure()
				+ "Mb");
		tv_tocDoGio_weatherSang1.setText(MainActivity.listWeather.get(0)
				.getHourlyList().get(1).getWindSpeedKmph()
				+ "Km/h");

		tv_doAm_weatherTrua1.setText(MainActivity.listWeather.get(0)
				.getHourlyList().get(3).getHumidity()
				+ "%");
		tv_tamNhin_weatherTrua1.setText(MainActivity.listWeather.get(0)
				.getHourlyList().get(3).getVisibility()
				+ "Km");
		tv_apSuat_weatherTrua1.setText(MainActivity.listWeather.get(0)
				.getHourlyList().get(3).getPressure()
				+ "Mb");
		tv_tocDoGio_weatherTrua1.setText(MainActivity.listWeather.get(0)
				.getHourlyList().get(3).getWindSpeedKmph()
				+ "Km/h");

		tv_doAm_weatherChieu1.setText(MainActivity.listWeather.get(0)
				.getHourlyList().get(5).getHumidity()
				+ "%");
		tv_tamNhin_weatherChieu1.setText(MainActivity.listWeather.get(0)
				.getHourlyList().get(5).getVisibility()
				+ "Km");
		tv_apSuat_weatherChieu1.setText(MainActivity.listWeather.get(0)
				.getHourlyList().get(5).getPressure()
				+ "Mb");
		tv_tocDoGio_weatherChieu1.setText(MainActivity.listWeather.get(0)
				.getHourlyList().get(5).getWindSpeedKmph()
				+ "Km/h");

		tv_doAm_weatherDem1.setText(MainActivity.listWeather.get(0)
				.getHourlyList().get(7).getHumidity()
				+ "%");
		tv_tamNhin_weatherDem1.setText(MainActivity.listWeather.get(0)
				.getHourlyList().get(7).getVisibility()
				+ "Km");
		tv_apSuat_weatherDem1.setText(MainActivity.listWeather.get(0)
				.getHourlyList().get(7).getPressure()
				+ "Mb");
		tv_tocDoGio_weatherDem1.setText(MainActivity.listWeather.get(0)
				.getHourlyList().get(7).getWindSpeedKmph()
				+ "Km/h");

		String time2 = MainActivity.listWeather.get(1).getDate();
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
		Date parsedDate2 = null;
		try {
			parsedDate2 = dateFormat2.parse(time2);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar newDate2 = Calendar.getInstance();
		newDate2.setTime(parsedDate2);
		tv_date2.setText("Ngày " + newDate2.get(Calendar.DAY_OF_MONTH)
				+ " tháng " + (newDate2.get(Calendar.MONTH) + 1) + " năm "
				+ newDate2.get(Calendar.YEAR));
		tv_timeBinhMinh2.setText(MainActivity.listWeather.get(1)
				.getAstronomyList().get(0).getSunrise());
		tv_timeHoangHon2.setText(MainActivity.listWeather.get(1)
				.getAstronomyList().get(0).getSunset());
		String doFMax2 = MainActivity.listWeather.get(1).getMaxTempF();
		String doFMin2 = MainActivity.listWeather.get(1).getMinTempF();
		int changeDoFMax2 = (int) ((Integer.parseInt(doFMax2) - 32) / 1.8);
		int changeDoFMin2 = (int) ((Integer.parseInt(doFMin2) - 32) / 1.8);
		tv_nhietdoCaoNhat2.setText(changeDoFMax2 + "°C" + "");
		tv_nhietdoThapNhat2.setText(changeDoFMin2 + "°C" + "");
		String doF2 = MainActivity.listWeather.get(1).getHourlyList().get(0)
				.getTempF();
		int changeDoF2 = (int) ((Integer.parseInt(doF2) - 32) / 1.8);
		tv_doC_weatherSang2.setText(changeDoF2 + "°C" + "");
		String doFTrua2 = MainActivity.listWeather.get(1).getHourlyList()
				.get(3).getTempF();
		int changeDoFTrua2 = (int) ((Integer.parseInt(doFTrua2) - 32) / 1.8);
		tv_doC_weatherTrua2.setText(changeDoFTrua2 + "°C" + "");
		String doFChieu2 = MainActivity.listWeather.get(1).getHourlyList()
				.get(5).getTempF();
		int changeDoFChieu2 = (int) ((Integer.parseInt(doFChieu2) - 32) / 1.8);
		tv_doC_weatherChieu2.setText(changeDoFChieu2 + "°C" + "");
		String doFDem2 = MainActivity.listWeather.get(1).getHourlyList().get(7)
				.getTempF();
		int changeDoFDem2 = (int) ((Integer.parseInt(doFDem2) - 32) / 1.8);
		tv_doC_weatherDem2.setText(changeDoFDem2 + "°C" + "");
		tv_doAm_weatherSang2.setText(MainActivity.listWeather.get(1)
				.getHourlyList().get(1).getHumidity()
				+ "%");
		tv_tamNhin_weatherSang2.setText(MainActivity.listWeather.get(1)
				.getHourlyList().get(1).getVisibility()
				+ "Km");
		tv_apSuat_weatherSang2.setText(MainActivity.listWeather.get(1)
				.getHourlyList().get(1).getPressure()
				+ "Mb");
		tv_tocDoGio_weatherSang2.setText(MainActivity.listWeather.get(1)
				.getHourlyList().get(1).getWindSpeedKmph()
				+ "Km/h");

		tv_doAm_weatherTrua2.setText(MainActivity.listWeather.get(1)
				.getHourlyList().get(3).getHumidity()
				+ "%");
		tv_tamNhin_weatherTrua2.setText(MainActivity.listWeather.get(1)
				.getHourlyList().get(3).getVisibility()
				+ "Km");
		tv_apSuat_weatherTrua2.setText(MainActivity.listWeather.get(1)
				.getHourlyList().get(3).getPressure()
				+ "Mb");
		tv_tocDoGio_weatherTrua2.setText(MainActivity.listWeather.get(1)
				.getHourlyList().get(3).getWindSpeedKmph()
				+ "Km/h");

		tv_doAm_weatherChieu2.setText(MainActivity.listWeather.get(1)
				.getHourlyList().get(5).getHumidity()
				+ "%");
		tv_tamNhin_weatherChieu2.setText(MainActivity.listWeather.get(1)
				.getHourlyList().get(5).getVisibility()
				+ "Km");
		tv_apSuat_weatherChieu2.setText(MainActivity.listWeather.get(1)
				.getHourlyList().get(5).getPressure()
				+ "Mb");
		tv_tocDoGio_weatherChieu2.setText(MainActivity.listWeather.get(1)
				.getHourlyList().get(5).getWindSpeedKmph()
				+ "Km/h");

		tv_doAm_weatherDem2.setText(MainActivity.listWeather.get(1)
				.getHourlyList().get(7).getHumidity()
				+ "%");
		tv_tamNhin_weatherDem2.setText(MainActivity.listWeather.get(1)
				.getHourlyList().get(7).getVisibility()
				+ "Km");
		tv_apSuat_weatherDem2.setText(MainActivity.listWeather.get(1)
				.getHourlyList().get(7).getPressure()
				+ "Mb");
		tv_tocDoGio_weatherDem2.setText(MainActivity.listWeather.get(1)
				.getHourlyList().get(7).getWindSpeedKmph()
				+ "Km/h");

		String time3 = MainActivity.listWeather.get(2).getDate();
		SimpleDateFormat dateFormat3 = new SimpleDateFormat("yyyy-MM-dd");
		Date parsedDate3 = null;
		try {
			parsedDate3 = dateFormat3.parse(time3);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar newDate3 = Calendar.getInstance();
		newDate3.setTime(parsedDate3);
		tv_date3.setText("Ngày " + newDate3.get(Calendar.DAY_OF_MONTH)
				+ " tháng " + (newDate3.get(Calendar.MONTH) + 1) + " năm "
				+ newDate3.get(Calendar.YEAR));
		tv_timeBinhMinh3.setText(MainActivity.listWeather.get(2)
				.getAstronomyList().get(0).getSunrise());
		tv_timeHoangHon3.setText(MainActivity.listWeather.get(2)
				.getAstronomyList().get(0).getSunset());
		String doFMax3 = MainActivity.listWeather.get(2).getMaxTempF();
		String doFMin3 = MainActivity.listWeather.get(2).getMinTempF();
		int changeDoFMax3 = (int) ((Integer.parseInt(doFMax3) - 32) / 1.8);
		int changeDoFMin3 = (int) ((Integer.parseInt(doFMin3) - 32) / 1.8);
		tv_nhietdoCaoNhat3.setText(changeDoFMax3 + "°C" + "");
		tv_nhietdoThapNhat3.setText(changeDoFMin3 + "°C" + "");
		String doF3 = MainActivity.listWeather.get(2).getHourlyList().get(0)
				.getTempF();
		int changeDoF3 = (int) ((Integer.parseInt(doF3) - 32) / 1.8);
		tv_doC_weatherSang3.setText(changeDoF3 + "°C" + "");
		String doFTrua3 = MainActivity.listWeather.get(2).getHourlyList()
				.get(3).getTempF();
		int changeDoFTrua3 = (int) ((Integer.parseInt(doFTrua3) - 32) / 1.8);
		tv_doC_weatherTrua3.setText(changeDoFTrua3 + "°C" + "");
		String doFChieu3 = MainActivity.listWeather.get(2).getHourlyList()
				.get(5).getTempF();
		int changeDoFChieu3 = (int) ((Integer.parseInt(doFChieu3) - 32) / 1.8);
		tv_doC_weatherChieu3.setText(changeDoFChieu3 + "°C" + "");
		String doFDem3 = MainActivity.listWeather.get(2).getHourlyList().get(7)
				.getTempF();
		int changeDoFDem3 = (int) ((Integer.parseInt(doFDem3) - 32) / 1.8);
		tv_doC_weatherDem3.setText(changeDoFDem3 + "°C" + "");
		tv_doAm_weatherSang3.setText(MainActivity.listWeather.get(2)
				.getHourlyList().get(1).getHumidity()
				+ "%");
		tv_tamNhin_weatherSang3.setText(MainActivity.listWeather.get(2)
				.getHourlyList().get(1).getVisibility()
				+ "Km");
		tv_apSuat_weatherSang3.setText(MainActivity.listWeather.get(2)
				.getHourlyList().get(1).getPressure()
				+ "Mb");
		tv_tocDoGio_weatherSang3.setText(MainActivity.listWeather.get(2)
				.getHourlyList().get(1).getWindSpeedKmph()
				+ "Km/h");

		tv_doAm_weatherTrua3.setText(MainActivity.listWeather.get(2)
				.getHourlyList().get(3).getHumidity()
				+ "%");
		tv_tamNhin_weatherTrua3.setText(MainActivity.listWeather.get(2)
				.getHourlyList().get(3).getVisibility()
				+ "Km");
		tv_apSuat_weatherTrua3.setText(MainActivity.listWeather.get(2)
				.getHourlyList().get(3).getPressure()
				+ "Mb");
		tv_tocDoGio_weatherTrua3.setText(MainActivity.listWeather.get(2)
				.getHourlyList().get(3).getWindSpeedKmph()
				+ "Km/h");

		tv_doAm_weatherChieu3.setText(MainActivity.listWeather.get(2)
				.getHourlyList().get(5).getHumidity()
				+ "%");
		tv_tamNhin_weatherChieu3.setText(MainActivity.listWeather.get(2)
				.getHourlyList().get(5).getVisibility()
				+ "Km");
		tv_apSuat_weatherChieu3.setText(MainActivity.listWeather.get(2)
				.getHourlyList().get(5).getPressure()
				+ "Mb");
		tv_tocDoGio_weatherChieu3.setText(MainActivity.listWeather.get(2)
				.getHourlyList().get(5).getWindSpeedKmph()
				+ "Km/h");

		tv_doAm_weatherDem3.setText(MainActivity.listWeather.get(2)
				.getHourlyList().get(7).getHumidity()
				+ "%");
		tv_tamNhin_weatherDem3.setText(MainActivity.listWeather.get(2)
				.getHourlyList().get(7).getVisibility()
				+ "Km");
		tv_apSuat_weatherDem3.setText(MainActivity.listWeather.get(2)
				.getHourlyList().get(7).getPressure()
				+ "Mb");
		tv_tocDoGio_weatherDem3.setText(MainActivity.listWeather.get(2)
				.getHourlyList().get(7).getWindSpeedKmph()
				+ "Km/h");

		// //////////////////////////////////////////

		String time4 = MainActivity.listWeather.get(3).getDate();
		SimpleDateFormat dateFormat4 = new SimpleDateFormat("yyyy-MM-dd");
		Date parsedDate4 = null;
		try {
			parsedDate4 = dateFormat4.parse(time4);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar newDate4 = Calendar.getInstance();
		newDate4.setTime(parsedDate4);
		tv_date4.setText("Ngày " + newDate4.get(Calendar.DAY_OF_MONTH)
				+ " tháng " + (newDate4.get(Calendar.MONTH) + 1) + " năm "
				+ newDate4.get(Calendar.YEAR));
		tv_timeBinhMinh4.setText(MainActivity.listWeather.get(3)
				.getAstronomyList().get(0).getSunrise());
		tv_timeHoangHon4.setText(MainActivity.listWeather.get(3)
				.getAstronomyList().get(0).getSunset());
		String doFMax4 = MainActivity.listWeather.get(3).getMaxTempF();
		String doFMin4 = MainActivity.listWeather.get(3).getMinTempF();
		int changeDoFMax4 = (int) ((Integer.parseInt(doFMax4) - 32) / 1.8);
		int changeDoFMin4 = (int) ((Integer.parseInt(doFMin4) - 32) / 1.8);
		tv_nhietdoCaoNhat4.setText(changeDoFMax4 + "°C" + "");
		tv_nhietdoThapNhat4.setText(changeDoFMin4 + "°C" + "");
		String doF4 = MainActivity.listWeather.get(3).getHourlyList().get(1)
				.getTempF();
		int changeDoF4 = (int) ((Integer.parseInt(doF4) - 32) / 1.8);
		tv_doC_weatherSang4.setText(changeDoF4 + "°C" + "");
		String doFTrua4 = MainActivity.listWeather.get(3).getHourlyList()
				.get(3).getTempF();
		int changeDoFTrua4 = (int) ((Integer.parseInt(doFTrua4) - 32) / 1.8);
		tv_doC_weatherTrua4.setText(changeDoFTrua4 + "°C" + "");
		String doFChieu4 = MainActivity.listWeather.get(3).getHourlyList()
				.get(5).getTempF();
		int changeDoFChieu4 = (int) ((Integer.parseInt(doFChieu4) - 32) / 1.8);
		tv_doC_weatherChieu4.setText(changeDoFChieu4 + "°C" + "");
		String doFDem4 = MainActivity.listWeather.get(3).getHourlyList().get(7)
				.getTempF();
		int changeDoFDem4 = (int) ((Integer.parseInt(doFDem4) - 32) / 1.8);
		tv_doC_weatherDem4.setText(changeDoFDem4 + "°C" + "");
		tv_doAm_weatherSang4.setText(MainActivity.listWeather.get(3)
				.getHourlyList().get(1).getHumidity()
				+ "%");
		tv_tamNhin_weatherSang4.setText(MainActivity.listWeather.get(3)
				.getHourlyList().get(1).getVisibility()
				+ "Km");
		tv_apSuat_weatherSang4.setText(MainActivity.listWeather.get(3)
				.getHourlyList().get(1).getPressure()
				+ "Mb");
		tv_tocDoGio_weatherSang4.setText(MainActivity.listWeather.get(3)
				.getHourlyList().get(1).getWindSpeedKmph()
				+ "Km/h");

		tv_doAm_weatherTrua4.setText(MainActivity.listWeather.get(3)
				.getHourlyList().get(3).getHumidity()
				+ "%");
		tv_tamNhin_weatherTrua4.setText(MainActivity.listWeather.get(3)
				.getHourlyList().get(3).getVisibility()
				+ "Km");
		tv_apSuat_weatherTrua4.setText(MainActivity.listWeather.get(3)
				.getHourlyList().get(3).getPressure()
				+ "Mb");
		tv_tocDoGio_weatherTrua4.setText(MainActivity.listWeather.get(3)
				.getHourlyList().get(3).getWindSpeedKmph()
				+ "Km/h");

		tv_doAm_weatherChieu4.setText(MainActivity.listWeather.get(3)
				.getHourlyList().get(5).getHumidity()
				+ "%");
		tv_tamNhin_weatherChieu4.setText(MainActivity.listWeather.get(3)
				.getHourlyList().get(5).getVisibility()
				+ "Km");
		tv_apSuat_weatherChieu4.setText(MainActivity.listWeather.get(3)
				.getHourlyList().get(5).getPressure()
				+ "Mb");
		tv_tocDoGio_weatherChieu4.setText(MainActivity.listWeather.get(3)
				.getHourlyList().get(5).getWindSpeedKmph()
				+ "Km/h");

		tv_doAm_weatherDem4.setText(MainActivity.listWeather.get(3)
				.getHourlyList().get(7).getHumidity()
				+ "%");
		tv_tamNhin_weatherDem4.setText(MainActivity.listWeather.get(3)
				.getHourlyList().get(7).getVisibility()
				+ "Km");
		tv_apSuat_weatherDem4.setText(MainActivity.listWeather.get(3)
				.getHourlyList().get(7).getPressure()
				+ "Mb");
		tv_tocDoGio_weatherDem4.setText(MainActivity.listWeather.get(3)
				.getHourlyList().get(7).getWindSpeedKmph()
				+ "Km/h");

		// //////////////////////////////////////////
		String time5 = MainActivity.listWeather.get(4).getDate();
		SimpleDateFormat dateFormat5 = new SimpleDateFormat("yyyy-MM-dd");
		Date parsedDate5 = null;
		try {
			parsedDate5 = dateFormat5.parse(time5);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar newDate5 = Calendar.getInstance();
		newDate5.setTime(parsedDate5);
		tv_date5.setText("Ngày " + newDate5.get(Calendar.DAY_OF_MONTH)
				+ " tháng " + (newDate5.get(Calendar.MONTH) + 1) + " năm "
				+ newDate5.get(Calendar.YEAR));
		tv_timeBinhMinh5.setText(MainActivity.listWeather.get(4)
				.getAstronomyList().get(0).getSunrise());
		tv_timeHoangHon5.setText(MainActivity.listWeather.get(4)
				.getAstronomyList().get(0).getSunset());
		String doFMax5 = MainActivity.listWeather.get(1).getMaxTempF();
		String doFMin5 = MainActivity.listWeather.get(1).getMinTempF();
		int changeDoFMax5 = (int) ((Integer.parseInt(doFMax5) - 32) / 1.8);
		int changeDoFMin5 = (int) ((Integer.parseInt(doFMin5) - 32) / 1.8);
		tv_nhietdoCaoNhat5.setText(changeDoFMax5 + "°C" + "");
		tv_nhietdoThapNhat5.setText(changeDoFMin5 + "°C" + "");
		String doF5 = MainActivity.listWeather.get(4).getHourlyList().get(1)
				.getTempF();
		int changeDoF5 = (int) ((Integer.parseInt(doF5) - 32) / 1.8);
		tv_doC_weatherSang5.setText(changeDoF5 + "°C" + "");
		String doFTrua5 = MainActivity.listWeather.get(4).getHourlyList()
				.get(3).getTempF();
		int changeDoFTrua5 = (int) ((Integer.parseInt(doFTrua5) - 32) / 1.8);
		tv_doC_weatherTrua5.setText(changeDoFTrua5 + "°C" + "");
		String doFChieu5 = MainActivity.listWeather.get(4).getHourlyList()
				.get(5).getTempF();
		int changeDoFChieu5 = (int) ((Integer.parseInt(doFChieu5) - 32) / 1.8);
		tv_doC_weatherChieu5.setText(changeDoFChieu5 + "°C" + "");
		String doFDem5 = MainActivity.listWeather.get(4).getHourlyList().get(7)
				.getTempF();
		int changeDoFDem5 = (int) ((Integer.parseInt(doFDem5) - 32) / 1.8);
		tv_doC_weatherDem5.setText(changeDoFDem5 + "°C" + "");
		tv_doAm_weatherSang5.setText(MainActivity.listWeather.get(4)
				.getHourlyList().get(1).getHumidity()
				+ "%");
		tv_tamNhin_weatherSang5.setText(MainActivity.listWeather.get(4)
				.getHourlyList().get(1).getVisibility()
				+ "Km");
		tv_apSuat_weatherSang5.setText(MainActivity.listWeather.get(4)
				.getHourlyList().get(1).getPressure()
				+ "Mb");
		tv_tocDoGio_weatherSang5.setText(MainActivity.listWeather.get(4)
				.getHourlyList().get(1).getWindSpeedKmph()
				+ "Km/h");

		tv_doAm_weatherTrua5.setText(MainActivity.listWeather.get(4)
				.getHourlyList().get(3).getHumidity()
				+ "%");
		tv_tamNhin_weatherTrua5.setText(MainActivity.listWeather.get(4)
				.getHourlyList().get(3).getVisibility()
				+ "Km");
		tv_apSuat_weatherTrua5.setText(MainActivity.listWeather.get(4)
				.getHourlyList().get(3).getPressure()
				+ "Mb");
		tv_tocDoGio_weatherTrua5.setText(MainActivity.listWeather.get(4)
				.getHourlyList().get(3).getWindSpeedKmph()
				+ "Km/h");

		tv_doAm_weatherChieu5.setText(MainActivity.listWeather.get(4)
				.getHourlyList().get(5).getHumidity()
				+ "%");
		tv_tamNhin_weatherChieu5.setText(MainActivity.listWeather.get(4)
				.getHourlyList().get(5).getVisibility()
				+ "Km");
		tv_apSuat_weatherChieu5.setText(MainActivity.listWeather.get(4)
				.getHourlyList().get(5).getPressure()
				+ "Mb");
		tv_tocDoGio_weatherChieu5.setText(MainActivity.listWeather.get(4)
				.getHourlyList().get(5).getWindSpeedKmph()
				+ "Km/h");

		tv_doAm_weatherDem5.setText(MainActivity.listWeather.get(4)
				.getHourlyList().get(7).getHumidity()
				+ "%");
		tv_tamNhin_weatherDem5.setText(MainActivity.listWeather.get(4)
				.getHourlyList().get(7).getVisibility()
				+ "Km");
		tv_apSuat_weatherDem5.setText(MainActivity.listWeather.get(4)
				.getHourlyList().get(7).getPressure()
				+ "Mb");
		tv_tocDoGio_weatherDem5.setText(MainActivity.listWeather.get(4)
				.getHourlyList().get(7).getWindSpeedKmph()
				+ "Km/h");
		// //////////////////////////////////////////
		String time6 = MainActivity.listWeather.get(5).getDate();
		SimpleDateFormat dateFormat6 = new SimpleDateFormat("yyyy-MM-dd");
		Date parsedDate6 = null;
		try {
			parsedDate6 = dateFormat6.parse(time6);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar newDate6 = Calendar.getInstance();
		newDate6.setTime(parsedDate6);
		tv_date6.setText("Ngày " + newDate6.get(Calendar.DAY_OF_MONTH)
				+ " tháng " + (newDate6.get(Calendar.MONTH) + 1) + " năm "
				+ newDate6.get(Calendar.YEAR));
		tv_timeBinhMinh6.setText(MainActivity.listWeather.get(5)
				.getAstronomyList().get(0).getSunrise());
		tv_timeHoangHon6.setText(MainActivity.listWeather.get(5)
				.getAstronomyList().get(0).getSunset());
		String doFMax6 = MainActivity.listWeather.get(5).getMaxTempF();
		String doFMin6 = MainActivity.listWeather.get(5).getMinTempF();
		int changeDoFMax6 = (int) ((Integer.parseInt(doFMax6) - 32) / 1.8);
		int changeDoFMin6 = (int) ((Integer.parseInt(doFMin6) - 32) / 1.8);
		tv_nhietdoCaoNhat3.setText(changeDoFMax6 + "°C" + "");
		tv_nhietdoThapNhat3.setText(changeDoFMin6 + "°C" + "");
		String doF6 = MainActivity.listWeather.get(5).getHourlyList().get(1)
				.getTempF();
		int changeDoF6 = (int) ((Integer.parseInt(doF6) - 32) / 1.8);
		tv_doC_weatherSang6.setText(changeDoF6 + "°C" + "");
		String doFTrua6 = MainActivity.listWeather.get(5).getHourlyList()
				.get(3).getTempF();
		int changeDoFTrua6 = (int) ((Integer.parseInt(doFTrua6) - 32) / 1.8);
		tv_doC_weatherTrua6.setText(changeDoFTrua6 + "°C" + "");
		String doFChieu6 = MainActivity.listWeather.get(5).getHourlyList()
				.get(5).getTempF();
		int changeDoFChieu6 = (int) ((Integer.parseInt(doFChieu6) - 32) / 1.8);
		tv_doC_weatherChieu6.setText(changeDoFChieu6 + "°C" + "");
		String doFDem6 = MainActivity.listWeather.get(5).getHourlyList().get(7)
				.getTempF();
		int changeDoFDem6 = (int) ((Integer.parseInt(doFDem6) - 32) / 1.8);
		tv_doC_weatherDem6.setText(changeDoFDem6 + "°C" + "");
		tv_doAm_weatherSang6.setText(MainActivity.listWeather.get(5)
				.getHourlyList().get(1).getHumidity()
				+ "%");
		tv_tamNhin_weatherSang6.setText(MainActivity.listWeather.get(5)
				.getHourlyList().get(1).getVisibility()
				+ "Km");
		tv_apSuat_weatherSang6.setText(MainActivity.listWeather.get(5)
				.getHourlyList().get(1).getPressure()
				+ "Mb");
		tv_tocDoGio_weatherSang6.setText(MainActivity.listWeather.get(5)
				.getHourlyList().get(1).getWindSpeedKmph()
				+ "Km/h");

		tv_doAm_weatherTrua6.setText(MainActivity.listWeather.get(5)
				.getHourlyList().get(3).getHumidity()
				+ "%");
		tv_tamNhin_weatherTrua6.setText(MainActivity.listWeather.get(5)
				.getHourlyList().get(3).getVisibility()
				+ "Km");
		tv_apSuat_weatherTrua6.setText(MainActivity.listWeather.get(5)
				.getHourlyList().get(3).getPressure()
				+ "Mb");
		tv_tocDoGio_weatherTrua6.setText(MainActivity.listWeather.get(5)
				.getHourlyList().get(3).getWindSpeedKmph()
				+ "Km/h");

		tv_doAm_weatherChieu6.setText(MainActivity.listWeather.get(5)
				.getHourlyList().get(5).getHumidity()
				+ "%");
		tv_tamNhin_weatherChieu6.setText(MainActivity.listWeather.get(5)
				.getHourlyList().get(5).getVisibility()
				+ "Km");
		tv_apSuat_weatherChieu6.setText(MainActivity.listWeather.get(5)
				.getHourlyList().get(5).getPressure()
				+ "Mb");
		tv_tocDoGio_weatherChieu6.setText(MainActivity.listWeather.get(5)
				.getHourlyList().get(5).getWindSpeedKmph()
				+ "Km/h");

		tv_doAm_weatherDem6.setText(MainActivity.listWeather.get(5)
				.getHourlyList().get(7).getHumidity()
				+ "%");
		tv_tamNhin_weatherDem6.setText(MainActivity.listWeather.get(5)
				.getHourlyList().get(7).getVisibility()
				+ "Km");
		tv_apSuat_weatherDem6.setText(MainActivity.listWeather.get(5)
				.getHourlyList().get(7).getPressure()
				+ "Mb");
		tv_tocDoGio_weatherDem6.setText(MainActivity.listWeather.get(5)
				.getHourlyList().get(7).getWindSpeedKmph()
				+ "Km/h");

		// //////////////////////////////////////////

		String time7 = MainActivity.listWeather.get(6).getDate();
		SimpleDateFormat dateFormat7 = new SimpleDateFormat("yyyy-MM-dd");
		Date parsedDate7 = null;
		try {
			parsedDate7 = dateFormat7.parse(time7);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar newDate7 = Calendar.getInstance();
		newDate7.setTime(parsedDate7);
		tv_date7.setText("Ngày " + newDate7.get(Calendar.DAY_OF_MONTH)
				+ " tháng " + (newDate7.get(Calendar.MONTH) + 1) + " năm "
				+ newDate7.get(Calendar.YEAR));
		tv_timeBinhMinh7.setText(MainActivity.listWeather.get(6)
				.getAstronomyList().get(0).getSunrise());
		tv_timeHoangHon7.setText(MainActivity.listWeather.get(6)
				.getAstronomyList().get(0).getSunset());
		String doFMax7 = MainActivity.listWeather.get(6).getMaxTempF();
		String doFMin7 = MainActivity.listWeather.get(6).getMinTempF();
		int changeDoFMax7 = (int) ((Integer.parseInt(doFMax7) - 32) / 1.8);
		int changeDoFMin7 = (int) ((Integer.parseInt(doFMin7) - 32) / 1.8);
		tv_nhietdoCaoNhat7.setText(changeDoFMax7 + "°C" + "");
		tv_nhietdoThapNhat7.setText(changeDoFMin7 + "°C" + "");
		String doF7 = MainActivity.listWeather.get(6).getHourlyList().get(1)
				.getTempF();
		int changeDoF7 = (int) ((Integer.parseInt(doF7) - 32) / 1.8);
		tv_doC_weatherSang7.setText(changeDoF7 + "°C" + "");
		String doFTrua7 = MainActivity.listWeather.get(6).getHourlyList()
				.get(3).getTempF();
		int changeDoFTrua7 = (int) ((Integer.parseInt(doFTrua7) - 32) / 1.8);
		tv_doC_weatherTrua7.setText(changeDoFTrua7 + "°C" + "");
		String doFChieu7 = MainActivity.listWeather.get(6).getHourlyList()
				.get(5).getTempF();
		int changeDoFChieu7 = (int) ((Integer.parseInt(doFChieu7) - 32) / 1.8);
		tv_doC_weatherChieu7.setText(changeDoFChieu7 + "°C" + "");
		String doFDem7 = MainActivity.listWeather.get(6).getHourlyList().get(7)
				.getTempF();
		int changeDoFDem7 = (int) ((Integer.parseInt(doFDem7) - 32) / 1.8);
		tv_doC_weatherDem7.setText(changeDoFDem7 + "°C" + "");
		tv_doAm_weatherSang7.setText(MainActivity.listWeather.get(6)
				.getHourlyList().get(1).getHumidity()
				+ "%");
		tv_tamNhin_weatherSang7.setText(MainActivity.listWeather.get(6)
				.getHourlyList().get(1).getVisibility()
				+ "Km");
		tv_apSuat_weatherSang7.setText(MainActivity.listWeather.get(6)
				.getHourlyList().get(1).getPressure()
				+ "Mb");
		tv_tocDoGio_weatherSang7.setText(MainActivity.listWeather.get(6)
				.getHourlyList().get(1).getWindSpeedKmph()
				+ "Km/h");

		tv_doAm_weatherTrua7.setText(MainActivity.listWeather.get(6)
				.getHourlyList().get(3).getHumidity()
				+ "%");
		tv_tamNhin_weatherTrua7.setText(MainActivity.listWeather.get(6)
				.getHourlyList().get(3).getVisibility()
				+ "Km");
		tv_apSuat_weatherTrua7.setText(MainActivity.listWeather.get(6)
				.getHourlyList().get(3).getPressure()
				+ "Mb");
		tv_tocDoGio_weatherTrua7.setText(MainActivity.listWeather.get(6)
				.getHourlyList().get(3).getWindSpeedKmph()
				+ "Km/h");

		tv_doAm_weatherChieu7.setText(MainActivity.listWeather.get(6)
				.getHourlyList().get(5).getHumidity()
				+ "%");
		tv_tamNhin_weatherChieu7.setText(MainActivity.listWeather.get(6)
				.getHourlyList().get(5).getVisibility()
				+ "Km");
		tv_apSuat_weatherChieu7.setText(MainActivity.listWeather.get(6)
				.getHourlyList().get(5).getPressure()
				+ "Mb");
		tv_tocDoGio_weatherChieu7.setText(MainActivity.listWeather.get(6)
				.getHourlyList().get(5).getWindSpeedKmph()
				+ "Km/h");

		tv_doAm_weatherDem7.setText(MainActivity.listWeather.get(6)
				.getHourlyList().get(7).getHumidity()
				+ "%");
		tv_tamNhin_weatherDem7.setText(MainActivity.listWeather.get(6)
				.getHourlyList().get(7).getVisibility()
				+ "Km");
		tv_apSuat_weatherDem7.setText(MainActivity.listWeather.get(6)
				.getHourlyList().get(7).getPressure()
				+ "Mb");
		tv_tocDoGio_weatherDem7.setText(MainActivity.listWeather.get(6)
				.getHourlyList().get(7).getWindSpeedKmph()
				+ "Km/h");
	}
	private void updateWeather(
			List<GetWeatherResponse.Data.Weather> listWeather, int i, int j) {
		String str2 = listWeather.get(i).getHourlyList().get(j)
				.getWeatherCode();
		String status2 = listWeather.get(i).getHourlyList().get(j)
				.getIsdaytime();
		if (status2.equalsIgnoreCase("yes")) {
			changeWeatherDay(str2, i, j);
		} else if (status2.equalsIgnoreCase("no")) {
			changeWeatherNight(str2, i);
		}
	}

	private void changeBuoiSang(int day, int resource) {
		if (day == 0) {
			iv_weatherSang1.setImageResource(resource);
		} else if (day == 1) {
			iv_weatherSang2.setImageResource(resource);
		} else if (day == 2) {
			iv_weatherSang3.setImageResource(resource);
		} else if (day == 3) {
			iv_weatherSang4.setImageResource(resource);
		} else if (day == 4) {
			iv_weatherSang5.setImageResource(resource);
		} else if (day == 5) {
			iv_weatherSang6.setImageResource(resource);
		} else if (day == 6) {
			iv_weatherSang7.setImageResource(resource);
		}
	}

	private void changeBuoiTrua(int day, int resource) {
		if (day == 0) {
			iv_weatherTrua1.setImageResource(resource);
		} else if (day == 1) {
			iv_weatherTrua2.setImageResource(resource);
		} else if (day == 2) {
			iv_weatherTrua3.setImageResource(resource);
		} else if (day == 3) {
			iv_weatherTrua4.setImageResource(resource);
		} else if (day == 4) {
			iv_weatherTrua5.setImageResource(resource);
		} else if (day == 5) {
			iv_weatherTrua6.setImageResource(resource);
		} else if (day == 6) {
			iv_weatherTrua7.setImageResource(resource);
		}
	}

	private void changeBuoiChieu(int day, int resource) {
		if (day == 0) {
			iv_weatherChieu1.setImageResource(resource);
		} else if (day == 1) {
			iv_weatherChieu2.setImageResource(resource);
		} else if (day == 2) {
			iv_weatherChieu3.setImageResource(resource);
		} else if (day == 3) {
			iv_weatherChieu4.setImageResource(resource);
		} else if (day == 4) {
			iv_weatherChieu5.setImageResource(resource);
		} else if (day == 5) {
			iv_weatherChieu6.setImageResource(resource);
		} else if (day == 6) {
			iv_weatherChieu7.setImageResource(resource);
		}
	}

	private void changeBuoiDem(int day, int resource) {
		if (day == 0) {
			iv_weatherDem1.setImageResource(resource);
		} else if (day == 1) {
			iv_weatherDem2.setImageResource(resource);
		} else if (day == 2) {
			iv_weatherDem3.setImageResource(resource);
		} else if (day == 3) {
			iv_weatherDem4.setImageResource(resource);
		} else if (day == 4) {
			iv_weatherDem5.setImageResource(resource);
		} else if (day == 5) {
			iv_weatherDem6.setImageResource(resource);
		} else if (day == 6) {
			iv_weatherDem7.setImageResource(resource);
		}
	}

	private void changeWeatherDay(String weatherCode, int day, int hour) {
		int resource = -1;
		switch (weatherCode) {
		case "113":
			resource = R.drawable.day_sm_113;
			break;
		case "116":
			resource = R.drawable.day_sm_116;
			break;
		case "119":
			resource = R.drawable.day_sm_119;
			break;
		case "122":
			resource = R.drawable.day_sm_122;
			break;
		case "143":
			resource = R.drawable.day_sm_143;
			break;
		case "176":
			resource = R.drawable.day_sm_176;
			break;
		case "179":
			resource = R.drawable.day_sm_179;
			break;
		case "182":
			resource = R.drawable.day_sm_182;
			break;
		case "185":
			resource = R.drawable.day_sm_185;
			break;
		case "200":
			resource = R.drawable.day_sm_200;
			break;
		case "227":
			resource = R.drawable.day_sm_227;
			break;
		case "230":
			resource = R.drawable.day_sm_230;
			break;
		case "248":
			resource = R.drawable.day_sm_248;
			break;
		case "260":
			resource = R.drawable.day_sm_260;
			break;
		case "263":
			resource = R.drawable.day_sm_263;
			break;
		case "266":
			resource = R.drawable.day_sm_266;
			break;
		case "281":
			resource = R.drawable.day_sm_281;
			break;
		case "284":
			resource = R.drawable.day_sm_284;
			break;
		case "293":
			resource = R.drawable.day_sm_293;
			break;
		case "296":
			resource = R.drawable.day_sm_296;
			break;
		case "299":
			resource = R.drawable.day_sm_299;
			break;
		case "302":
			resource = R.drawable.day_sm_302;
			break;
		case "305":
			resource = R.drawable.day_sm_305;
			break;
		case "308":
			resource = R.drawable.day_sm_308;
			break;
		case "311":
			resource = R.drawable.day_sm_311;
			break;
		case "314":
			resource = R.drawable.day_sm_314;
			break;
		case "317":
			resource = R.drawable.day_sm_317;
			break;
		case "320":
			resource = R.drawable.day_sm_320;
			break;
		case "323":
			resource = R.drawable.day_sm_323;
			break;
		case "326":
			resource = R.drawable.day_sm_326;
			break;
		case "329":
			resource = R.drawable.day_sm_329;
			break;
		case "332":
			resource = R.drawable.day_sm_332;
			break;
		case "335":
			resource = R.drawable.day_sm_335;
			break;
		case "338":
			resource = R.drawable.day_sm_338;
			break;
		case "350":
			resource = R.drawable.day_sm_350;
			break;
		case "353":
			resource = R.drawable.day_sm_353;
			break;
		case "356":
			resource = R.drawable.day_sm_356;
			break;
		case "359":
			resource = R.drawable.day_sm_359;
			break;
		case "362":
			resource = R.drawable.day_sm_362;
			break;
		case "365":
			resource = R.drawable.day_sm_365;
			break;
		case "368":
			resource = R.drawable.day_sm_368;
			break;
		case "371":
			resource = R.drawable.day_sm_371;
			break;

		case "374":
			resource = R.drawable.day_sm_374;
			break;
		case "377":
			resource = R.drawable.day_sm_377;
			break;
		case "386":
			resource = R.drawable.day_sm_386;
			break;
		case "389":
			resource = R.drawable.day_sm_389;
			break;
		case "392":
			resource = R.drawable.day_sm_392;
			break;
		case "395":
			resource = R.drawable.day_sm_395;
			break;
		}
		if (resource < 0)
			return;

		if (hour == 2) {
			changeBuoiSang(day, resource);
		} else if (hour == 3) {
			changeBuoiTrua(day, resource);
		} else if (hour == 4) {
			changeBuoiChieu(day, resource);
		}
	}

	private void changeWeatherNight(String weatherCode, int day) {
		switch (weatherCode) {
		case "113":
			changeBuoiDem(day, R.drawable.night_sm_113);
			break;
		case "116":
			changeBuoiDem(day, R.drawable.night_sm_116);
			break;
		case "119":
			changeBuoiDem(day, R.drawable.night_sm_119);
			break;
		case "122":
			changeBuoiDem(day, R.drawable.night_sm_122);
			break;
		case "143":
			changeBuoiDem(day, R.drawable.night_sm_143);
			break;
		case "176":
			changeBuoiDem(day, R.drawable.night_sm_176);
			break;
		case "179":
			changeBuoiDem(day, R.drawable.night_sm_179);
			break;
		case "182":
			changeBuoiDem(day, R.drawable.night_sm_182);
			break;
		case "185":
			changeBuoiDem(day, R.drawable.night_sm_185);
			break;
		case "200":
			changeBuoiDem(day, R.drawable.night_sm_200);
			break;
		case "227":
			changeBuoiDem(day, R.drawable.night_sm_227);
			break;
		case "230":
			changeBuoiDem(day, R.drawable.night_sm_230);
			break;
		case "248":
			changeBuoiDem(day, R.drawable.night_sm_248);
			break;
		case "260":
			changeBuoiDem(day, R.drawable.night_sm_260);
			break;
		case "263":
			changeBuoiDem(day, R.drawable.night_sm_263);
			break;
		case "266":
			changeBuoiDem(day, R.drawable.night_sm_266);
			break;
		case "281":
			changeBuoiDem(day, R.drawable.night_sm_281);
			break;
		case "284":
			changeBuoiDem(day, R.drawable.night_sm_284);
			break;
		case "293":
			changeBuoiDem(day, R.drawable.night_sm_293);
			break;
		case "296":
			changeBuoiDem(day, R.drawable.night_sm_296);
			break;
		case "299":
			changeBuoiDem(day, R.drawable.night_sm_299);
			break;
		case "302":
			changeBuoiDem(day, R.drawable.night_sm_302);
			break;
		case "305":
			changeBuoiDem(day, R.drawable.night_sm_305);
			break;
		case "308":
			changeBuoiDem(day, R.drawable.night_sm_308);
			break;
		case "311":
			changeBuoiDem(day, R.drawable.night_sm_311);
			break;
		case "314":
			changeBuoiDem(day, R.drawable.night_sm_314);
			break;
		case "317":
			changeBuoiDem(day, R.drawable.night_sm_317);
			break;
		case "320":
			changeBuoiDem(day, R.drawable.night_sm_320);
			break;
		case "323":
			changeBuoiDem(day, R.drawable.night_sm_323);
			break;
		case "326":
			changeBuoiDem(day, R.drawable.night_sm_326);
			break;
		case "329":
			changeBuoiDem(day, R.drawable.night_sm_329);
			break;
		case "332":
			changeBuoiDem(day, R.drawable.night_sm_332);
			break;
		case "335":
			changeBuoiDem(day, R.drawable.night_sm_335);
			break;
		case "338":
			changeBuoiDem(day, R.drawable.night_sm_338);
			break;
		case "350":
			changeBuoiDem(day, R.drawable.night_sm_350);
			break;
		case "353":
			changeBuoiDem(day, R.drawable.night_sm_353);
			break;
		case "356":
			changeBuoiDem(day, R.drawable.night_sm_356);
			break;
		case "359":
			changeBuoiDem(day, R.drawable.night_sm_359);
			break;
		case "362":
			changeBuoiDem(day, R.drawable.night_sm_362);
			break;
		case "365":
			changeBuoiDem(day, R.drawable.night_sm_365);
			break;
		case "368":
			changeBuoiDem(day, R.drawable.night_sm_368);
			break;
		case "371":
			changeBuoiDem(day, R.drawable.night_sm_371);
			break;
		case "374":
			changeBuoiDem(day, R.drawable.night_sm_374);
			break;
		case "377":
			changeBuoiDem(day, R.drawable.night_sm_377);
			break;
		case "386":
			changeBuoiDem(day, R.drawable.night_sm_386);
			break;
		case "389":
			changeBuoiDem(day, R.drawable.night_sm_389);
			break;
		case "392":
			changeBuoiDem(day, R.drawable.night_sm_392);
			break;
		case "395":
			changeBuoiDem(day, R.drawable.night_sm_395);
			break;
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_back:
			onBackPressed();
			break;
		case R.id.iv_tinhthanh:
			Intent intent = new Intent(DuBaoThoiTietActivity.this,
					TinhThanhActivity.class);
			startActivityForResult(intent, 2);
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		// check if the request code is same as what is passed here it is 2
		if (requestCode == 2) {
			String latlong = data.getStringExtra("LATLONG");
			String name = data.getStringExtra("NAME");
			tv_location_wearther.setText(name);
			LichNgayFragment.tv_diadiem.setText(name);
			MainActivity.loadDataAPI("1fb4bcfe9e152330111305",
					"8bcb0015fc200346120401", "json", latlong, "yes", "7",
					"yes", "isDayTime,utcDateTime");
			
			(new Handler()).postDelayed(loadData, 3*1000);
		}
	}
	
	
	
}
