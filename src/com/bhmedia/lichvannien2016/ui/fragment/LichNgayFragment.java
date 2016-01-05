package com.bhmedia.lichvannien2016.ui.fragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bhmedia.lichvannien2016.R;
import com.bhmedia.lichvannien2016.ui.activity.ChiTietNgayActivity;
import com.bhmedia.lichvannien2016.ui.activity.DuBaoThoiTietActivity;
import com.bhmedia.lichvannien2016.ui.activity.MainActivity;
import com.bhmedia.lichvannien2016.ui.activity.MyApp;
import com.bhmedia.lichvannien2016.ui.activity.ThemSuKienActivity;
import com.bhmedia.lichvannien2016.ui.database.DanhNgonDataBaseHelper;
import com.bhmedia.lichvannien2016.ui.database.NgayLeDataBaseHelper;
import com.bhmedia.lichvannien2016.ui.model.DanhNgon;
import com.bhmedia.lichvannien2016.ui.utility.CalendarUtil;
import com.google.android.gms.analytics.HitBuilders;


/**
 * Created by Van on 1/14/2015.
 */
public class LichNgayFragment extends BaseFragment implements
		View.OnClickListener {
	CalendarUtil calendarUtil = new CalendarUtil();
	Context context;
	private ViewPager mPager;

	TextView tv_namAmLichCanChi, tv_namAmLich, tv_thangAmLich,
			tv_thangAmLichCanChi, tv_ngayAmLichCanChi, tv_ngayAmLich,
			tv_gioAmLichCanChi;
	Calendar calendar;
	int dduong, mduong, yduong, hour;
	int mPage = 100000;
	public static ImageView iv_thoitiet;
	public static TextView tv_nhietdo, tv_diadiem, tv_noInformation;
	public static View ll_weather;
	MyFragmentStatePagerAdapter mAdapter;

	@Override
	protected int getLayoutId() {

		return R.layout.layout_fragment_lichngay;
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

	private int mid_value = 0;

	@Override
	protected void onInitializeView(Bundle savedInstanceState) {
		super.onInitializeView(savedInstanceState);
		MyApp.tracker().setScreenName("Lich Ngay Android");
		MyApp.tracker().send(new HitBuilders.AppViewBuilder().build());
		   
		iv_thoitiet = (ImageView) findViewById(R.id.iv_thoitiet);
		tv_nhietdo = (TextView) findViewById(R.id.tv_nhietdo);
		tv_diadiem = (TextView) findViewById(R.id.tv_diadiem);
		tv_namAmLichCanChi = (TextView) findViewById(R.id.tv_namAmLichCanChi);
		tv_namAmLich = (TextView) findViewById(R.id.tv_namAmLich);
		tv_thangAmLich = (TextView) findViewById(R.id.tv_thangAmLich);
		tv_thangAmLichCanChi = (TextView) findViewById(R.id.tv_thangAmLichCanChi);
		tv_ngayAmLich = (TextView) findViewById(R.id.tv_ngayAmLich);
		tv_ngayAmLichCanChi = (TextView) findViewById(R.id.tv_ngayAmLichCanChi);
		tv_gioAmLichCanChi = (TextView) findViewById(R.id.tv_gioAmLichCanChi);
		tv_noInformation = (TextView) findViewById(R.id.tv_noInformation);
		changeAmDuongLich();

		mAdapter = new MyFragmentStatePagerAdapter(getActivity());
		mPager = (ViewPager) findViewById(R.id.vp_container_lichngay);
		mPager.setAdapter(mAdapter);
		mid_value = (int) Math.ceil(mPage / 2) - 1;
		mPager.setCurrentItem(mid_value, false);

		mAdapter.notifyDataSetChanged();

		((MainActivity) getActivity()).tv_ngay_menu
				.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						mPager.setCurrentItem(mid_value, false);
					}
				});
		mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {

			}

			@Override
			public void onPageSelected(final int position) {
				Date dateCurrent = new Date();
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(dateCurrent);
				calendar.add(Calendar.DAY_OF_MONTH, position);
				calendar.add(Calendar.DAY_OF_MONTH, 0 - mid_value);
				dduong = calendar.get(Calendar.DAY_OF_MONTH);
				mduong = calendar.get(Calendar.MONTH) + 1;
				yduong = calendar.get(Calendar.YEAR);
				hour = calendar.get(Calendar.HOUR_OF_DAY);
				int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
				((MainActivity) getActivity()).changeThu(dayOfWeek);

				int[] convert = calendarUtil.convertSolar2Lunar(dduong, mduong,
						yduong, 7);
				calendar.set(Calendar.DAY_OF_MONTH, convert[0]);
				calendar.set(Calendar.MONTH, convert[1] - 1);
				calendar.set(Calendar.YEAR, convert[2]);
				tv_ngayAmLich.setText(calendar.get(Calendar.DAY_OF_MONTH) + "");
				tv_thangAmLich.setText((calendar.get(Calendar.MONTH) + 1) + "");
				tv_namAmLich.setText(calendar.get(Calendar.YEAR) + "");
				tv_ngayAmLichCanChi.setText(calendarUtil.getCanChiOfDay(dduong,
						mduong, yduong));
				tv_thangAmLichCanChi.setText(calendarUtil.getCanOfMonth(
						calendar.get(Calendar.MONTH) + 1,
						calendar.get(Calendar.YEAR))
						+ " "
						+ calendarUtil.getChiOfMonth(calendar
								.get(Calendar.MONTH) + 1));
				tv_gioAmLichCanChi.setText(calendarUtil.getCanChiOfHour(hour,
						calendar.get(Calendar.DAY_OF_MONTH),
						calendar.get(Calendar.MONTH) + 1,
						calendar.get(Calendar.YEAR)));
				tv_namAmLichCanChi.setText(calendarUtil.getCanOfYear(calendar
						.get(Calendar.YEAR))
						+ " "
						+ calendarUtil.getChiOfYear(calendar.get(Calendar.YEAR)));
				((MainActivity) getActivity()).cancelAsyncTask();
			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});

		TextView tv_chitietngay = (TextView) findViewById(R.id.tv_chitietngay);
		TextView tv_themsukien = (TextView) findViewById(R.id.tv_themsukien);
		ll_weather = findViewById(R.id.ll_weather);
		tv_chitietngay.setOnClickListener(this);
		tv_themsukien.setOnClickListener(this);
		ll_weather.setOnClickListener(this);
	}

	private void changeAmDuongLich() {
		Date dateCurrent = new Date();
		calendar = Calendar.getInstance();
		calendar.setTime(dateCurrent);
		dduong = calendar.get(Calendar.DAY_OF_MONTH);
		mduong = calendar.get(Calendar.MONTH) + 1;
		yduong = calendar.get(Calendar.YEAR);
		hour = calendar.get(Calendar.HOUR_OF_DAY);

		int[] convert = calendarUtil.convertSolar2Lunar(dduong, mduong, yduong,
				7);
		calendar.set(Calendar.DAY_OF_MONTH, convert[0]);
		calendar.set(Calendar.MONTH, convert[1] - 1);
		calendar.set(Calendar.YEAR, convert[2]);

		tv_ngayAmLich.setText(calendar.get(Calendar.DAY_OF_MONTH) + "");
		tv_thangAmLich.setText((calendar.get(Calendar.MONTH) + 1) + "");
		tv_namAmLich.setText(calendar.get(Calendar.YEAR) + "");
		tv_ngayAmLichCanChi.setText(calendarUtil.getCanChiOfDay(dduong, mduong,
				yduong));
		tv_thangAmLichCanChi.setText(calendarUtil.getCanOfMonth(
				calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR))
				+ " "
				+ calendarUtil.getChiOfMonth(calendar.get(Calendar.MONTH) + 1));
		tv_gioAmLichCanChi.setText(calendarUtil.getCanChiOfHour(hour,
				calendar.get(Calendar.DAY_OF_MONTH),
				calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR)));
		tv_namAmLichCanChi.setText(calendarUtil.getCanOfYear(calendar
				.get(Calendar.YEAR))
				+ " "
				+ calendarUtil.getChiOfYear(calendar.get(Calendar.YEAR)));
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_chitietngay:
			((MainActivity) getActivity()).cancelAsyncTask();
			Intent intent = new Intent(getActivity(), ChiTietNgayActivity.class);
			Bundle mBundle = new Bundle();
			mBundle.putInt("NGAY", dduong);
			mBundle.putInt("THANG", mduong);
			mBundle.putInt("NAM", yduong);
			intent.putExtra("Bundle", mBundle);
			startActivity(intent);
			break;
		case R.id.tv_themsukien:
			((MainActivity) getActivity()).cancelAsyncTask();
			Intent intent1 = new Intent(getActivity(), ThemSuKienActivity.class);
			Bundle mBundle1 = new Bundle();
			mBundle1.putInt("NGAY_SUKIEN", dduong);
			mBundle1.putInt("THANG_SUKIEN", mduong);
			mBundle1.putInt("NAM_SUKIEN", yduong);
			intent1.putExtra("Bundle", mBundle1);
			startActivity(intent1);
			break;
		case R.id.ll_weather:
			((MainActivity) getActivity()).cancelAsyncTask();
			Intent intent2 = new Intent(getActivity(),
					DuBaoThoiTietActivity.class);
			Bundle mBundle2 = new Bundle();
			mBundle2.putString("DIACHI", tv_diadiem.getText().toString());
			intent2.putExtra("Bundle", mBundle2);
			startActivity(intent2);
			break;
		}
	}

	@Override
	public void onStop() {
		super.onStop();
		((MainActivity) getActivity()).cancelAsyncTask();
	}

	class MyFragmentStatePagerAdapter extends PagerAdapter {
		private Context context;
		private ArrayList<DanhNgon> listDanhNgon;

		public MyFragmentStatePagerAdapter(Context mContext) {
			context = mContext;
			DanhNgonDataBaseHelper danhNgonDataBaseHelper = new DanhNgonDataBaseHelper(
					mContext);
			listDanhNgon = danhNgonDataBaseHelper.showDatabase(mContext);
			// danhNgonDataBaseHelper.close();
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			RelativeLayout view = (RelativeLayout) LayoutInflater.from(context)
					.inflate(R.layout.viewpager_lichngay, container, false);
			TextView tv_ngay = (TextView) view.findViewById(R.id.tv_ngay);
			TextView tv_thang_nam = (TextView) view
					.findViewById(R.id.tv_thang_nam);
			TextView tv_hoangHacDao = (TextView) view
					.findViewById(R.id.tv_hoangHacDao);
			ImageView iv_hoangHacDao = (ImageView) view
					.findViewById(R.id.iv_icon_hoangHacDao);
			TextView tv_danhngon = (TextView) view
					.findViewById(R.id.tv_danhngon);
			TextView tv_tenTacGia = (TextView) view
					.findViewById(R.id.tv_tenTacGia);
			CalendarUtil calendarUtil1 = new CalendarUtil();

			Date dateCurrent = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dateCurrent);
			calendar.add(Calendar.DAY_OF_MONTH,
					0 - ((int) Math.ceil(mPage / 2) - 1));
			calendar.add(Calendar.DAY_OF_MONTH, position);
			int day = calendar.get(Calendar.DAY_OF_MONTH);
			int month = calendar.get(Calendar.MONTH) + 1;
			int year = calendar.get(Calendar.YEAR);
			int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
			int[] convert = calendarUtil
					.convertSolar2Lunar(day, month, year, 7);
			if (calendarUtil.checkLeap(year)) {
				if (month == 2) {
					tv_ngay.setText((calendar.get(Calendar.DAY_OF_MONTH) + 1)
							+ "");
				}
			}
			tv_ngay.setText(calendar.get(Calendar.DAY_OF_MONTH) + "");
			switch (month) {
			case 1:
				tv_thang_nam.setText("January "+ year);
				break;
			case 2:
				tv_thang_nam.setText("February "+ year);
				break;
			case 3:
				tv_thang_nam.setText("March "+ year);
				break;
			case 4:
				tv_thang_nam.setText("April "+ year);
				break;
			case 5:
				tv_thang_nam.setText("May "+ year);
				break;
			case 6:
				tv_thang_nam.setText("June "+ year);
				break;
			case 7:
				tv_thang_nam.setText("July "+ year);
				break;
			case 8:
				tv_thang_nam.setText("August "+ year);
				break;
			case 9:
				tv_thang_nam.setText("September "+ year);
				break;
			case 10:
				tv_thang_nam.setText("October "+ year);
				break;
			case 11:
				tv_thang_nam.setText("November "+ year);
				break;
			case 12:
				tv_thang_nam.setText("December "+ year);
				break;

			default:
				break;
			}
			if (calendarUtil1.IsBadGoodDay(day, month, year, convert[1]) == CalendarUtil.NGAYHACDAO) {
				iv_hoangHacDao.setImageResource(R.drawable.hacdao_icon);
				tv_hoangHacDao.setText(CalendarUtil.STR_NGAYHACDAO);
			} else if (calendarUtil1.IsBadGoodDay(day, month, year, convert[1]) == CalendarUtil.NGAYHOANGDAO) {
				iv_hoangHacDao.setImageResource(R.drawable.hoangdao_icon);
				tv_hoangHacDao.setText(CalendarUtil.STR_NGAYHOANGDAO);
			} else {
				iv_hoangHacDao.setVisibility(View.GONE);
				tv_hoangHacDao.setText(CalendarUtil.STR_NGAYBINHTHUONG);
			}

			if (ngayDacBietDuong(day, month)
					|| ngayDacBietAm(convert[0], convert[1])
					|| (dayOfWeek == Calendar.SUNDAY)) {
				tv_ngay.setTextColor(Color.parseColor("#FF0000"));
			}

			boolean ngayLeDuong = ngayLeDuong(day, month);

			if (ngayLeDuong) {
				if (day == 1 && month == 1) {
					tv_danhngon.setText(R.string.ngay_0101_duong);
				} else if (day == 4 && month == 7) {
					tv_danhngon.setText(R.string.ngay_0407_duong);
				} else if (day == 24 && month == 12) {
					tv_danhngon.setText(R.string.ngay_2412_duong);
				} else if (day == 25 && month == 12) {
					tv_danhngon.setText(R.string.ngay_2512_duong);
				} else if (day == 31 && month == 12) {
					tv_danhngon.setText(R.string.ngay_3112_duong);
				} else if (day == 11 && month == 11) {
					tv_danhngon.setText(R.string.ngay_1111_duong);
				} else if (day == 14 && month == 2) {
					tv_danhngon.setText(R.string.ngay_1402_duong);
				} else if (day == 17 && month == 3) {
					tv_danhngon.setText(R.string.ngay_1703_duong);
				} else if (day == 31 && month == 10) {
					tv_danhngon.setText(R.string.ngay_3110_duong);
				} else if (day == 8 && month == 3) {
					tv_danhngon.setText(R.string.ngay_0803_duong);
				} else if (day == 1 && month == 4) {
					tv_danhngon.setText(R.string.ngay_0104_duong);
				} else if (day == 1 && month == 5) {
					tv_danhngon.setText(R.string.ngay_0105_duong);
				} else if (day == 12 && month == 2) {
					tv_danhngon.setText(R.string.ngay_1202_duong);
				} else if (day == 9 && month == 10) {
					tv_danhngon.setText(R.string.ngay_0910_duong);
				} else if (day == 27 && month == 1) {
					tv_danhngon.setText(R.string.ngay_2701_duong);
				} else if (day == 2 && month == 6) {
					tv_danhngon.setText(R.string.ngay_0206_duong);
				} else if (day == 11 && month == 2) {
					tv_danhngon.setText(R.string.ngay_1102_duong);
				} else if (day == 26 && month == 12) {
					tv_danhngon.setText(R.string.ngay_2612_duong);
				} else if (day == 15 && month == 8) {
					tv_danhngon.setText(R.string.ngay_1508_duong);
				} else if (day == 25 && month == 1) {
					tv_danhngon.setText(R.string.ngay_2501_duong);
				} else if (day == 1 && month == 5) {
					tv_danhngon.setText(R.string.ngay_0105_duong);
				} else if (day == 1 && month == 7) {
					tv_danhngon.setText(R.string.ngay_0107_duong);
				} else if (day == 7 && month == 9) {
					tv_danhngon.setText(R.string.ngay_0709_duong);
				} else if (day == 12 && month == 6) {
					tv_danhngon.setText(R.string.ngay_1206_duong);
				} else if (day == 6 && month == 1) {
					tv_danhngon.setText(R.string.ngay_0601_duong);
				} else if (day == 10 && month == 12) {
					tv_danhngon.setText(R.string.ngay_1012_duong);
				} else if (day == 5 && month == 7) {
					tv_danhngon.setText(R.string.ngay_0507_duong);
				} 
			}else {
				// Danh Ngon Lich Ngay
				Random random = new Random();
				int a = random.nextInt(listDanhNgon.size());
				tv_danhngon.setText(listDanhNgon.get(a).getTacgia());
				tv_tenTacGia.setText(listDanhNgon.get(a).getTitle());
			}

			((ViewPager) container).addView(view, 0);

			return view;
		}

		@Override
		public void destroyItem(View collection, int position, Object view) {
			((ViewPager) collection).removeView((View) view);
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == ((View) object);
		}

		@Override
		public void finishUpdate(View arg0) {
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {
		}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public int getCount() {
			return mPage;
		}

		@Override
		public void startUpdate(View arg0) {
		}
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


	private boolean ngayLeDuong(int ngayDuong, int thangDuong) {
		boolean ret = false;
		if ((ngayDuong == 1 && thangDuong == 1)
				|| (ngayDuong == 2 && thangDuong == 1)
				|| (ngayDuong == 9 && thangDuong == 1)
				|| (ngayDuong == 11 && thangDuong == 1)
				|| (ngayDuong == 27 && thangDuong == 1)
				|| (ngayDuong == 29 && thangDuong == 1)
				|| (ngayDuong == 3 && thangDuong == 2)
				|| (ngayDuong == 7 && thangDuong == 2)
				|| (ngayDuong == 8 && thangDuong == 2)
				|| (ngayDuong == 14 && thangDuong == 2)
				|| (ngayDuong == 15 && thangDuong == 2)
				|| (ngayDuong == 27 && thangDuong == 2)
				|| (ngayDuong == 8 && thangDuong == 3)
				|| (ngayDuong == 10 && thangDuong == 3)
				|| (ngayDuong == 18 && thangDuong == 3)
				|| (ngayDuong == 26 && thangDuong == 3)
				|| (ngayDuong == 27 && thangDuong == 3)
				|| (ngayDuong == 1 && thangDuong == 4)
				|| (ngayDuong == 27 && thangDuong == 4)
				|| (ngayDuong == 30 && thangDuong == 4)
				|| (ngayDuong == 1 && thangDuong == 5)
				|| (ngayDuong == 6 && thangDuong == 5)
				|| (ngayDuong == 7 && thangDuong == 5)
				|| (ngayDuong == 9 && thangDuong == 5)
				|| (ngayDuong == 13 && thangDuong == 5)
				|| (ngayDuong == 15 && thangDuong == 5)
				|| (ngayDuong == 19 && thangDuong == 5)
				|| (ngayDuong == 1 && thangDuong == 6)
				|| (ngayDuong == 5 && thangDuong == 6)
				|| (ngayDuong == 6 && thangDuong == 6)
				|| (ngayDuong == 11 && thangDuong == 6)
				|| (ngayDuong == 21 && thangDuong == 6)
				|| (ngayDuong == 11 && thangDuong == 7)
				|| (ngayDuong == 15 && thangDuong == 7)
				|| (ngayDuong == 17 && thangDuong == 7)
				|| (ngayDuong == 27 && thangDuong == 7)
				|| (ngayDuong == 28 && thangDuong == 7)
				|| (ngayDuong == 15 && thangDuong == 5)
				|| (ngayDuong == 6 && thangDuong == 8)
				|| (ngayDuong == 10 && thangDuong == 8)
				|| (ngayDuong == 15 && thangDuong == 8)
				|| (ngayDuong == 19 && thangDuong == 8)
				|| (ngayDuong == 26 && thangDuong == 8)
				|| (ngayDuong == 2 && thangDuong == 9)
				|| (ngayDuong == 12 && thangDuong == 9)
				|| (ngayDuong == 20 && thangDuong == 9)
				|| (ngayDuong == 30 && thangDuong == 9)
				|| (ngayDuong == 1 && thangDuong == 10)
				|| (ngayDuong == 13 && thangDuong == 10)
				|| (ngayDuong == 14 && thangDuong == 10)
				|| (ngayDuong == 15 && thangDuong == 10)
				|| (ngayDuong == 20 && thangDuong == 10)
				|| (ngayDuong == 31 && thangDuong == 10)
				|| (ngayDuong == 7 && thangDuong == 11)
				|| (ngayDuong == 15 && thangDuong == 11)
				|| (ngayDuong == 18 && thangDuong == 11)
				|| (ngayDuong == 20 && thangDuong == 11)
				|| (ngayDuong == 23 && thangDuong == 11)
				|| (ngayDuong == 1 && thangDuong == 12)
				|| (ngayDuong == 3 && thangDuong == 12)
				|| (ngayDuong == 6 && thangDuong == 12)
				|| (ngayDuong == 10 && thangDuong == 12)
				|| (ngayDuong == 14 && thangDuong == 12)
				|| (ngayDuong == 20 && thangDuong == 12)
				|| (ngayDuong == 22 && thangDuong == 12)
				|| (ngayDuong == 25 && thangDuong == 12)
				|| (ngayDuong == 26 && thangDuong == 11)
				|| (ngayDuong == 31 && thangDuong == 12))
			ret = true;
		return ret;
	}

	private boolean ngayLeAm(int ngayAm, int thangAm) {
		boolean ret = false;

		if ((ngayAm <= 10 && thangAm == 1) || (ngayAm == 12 && thangAm == 1)
				|| (ngayAm == 13 && thangAm == 1)
				|| (ngayAm == 15 && thangAm == 1)
				|| (ngayAm == 17 && thangAm == 1)
				|| (ngayAm == 18 && thangAm == 1)
				|| (ngayAm == 10 && thangAm == 2)
				|| (ngayAm == 19 && thangAm == 2)
				|| (ngayAm == 5 && thangAm == 3)
				|| (ngayAm == 6 && thangAm == 3)
				|| (ngayAm == 14 && thangAm == 3)
				|| (ngayAm == 10 && thangAm == 3)
				|| (ngayAm == 1 && thangAm == 4)
				|| (ngayAm == 6 && thangAm == 4)
				|| (ngayAm == 15 && thangAm == 4)
				|| (ngayAm == 23 && thangAm == 4)
				|| (ngayAm == 15 && thangAm == 7)
				|| (ngayAm == 15 && thangAm == 8)
				|| (ngayAm == 16 && thangAm == 8)
				|| (ngayAm == 13 && thangAm == 9)
				|| (ngayAm == 14 && thangAm == 11)
				|| (ngayAm == 23 && thangAm == 12))
			ret = true;
		return ret;
	}

	private boolean ngayDacBietAm(int ngayAm, int thangAm) {
		boolean ret = false;

		if ((ngayAm >= 1 && ngayAm <= 3 && thangAm == 1)
				|| (ngayAm == 10 && thangAm == 3))
			ret = true;

		return ret;
	}

}
