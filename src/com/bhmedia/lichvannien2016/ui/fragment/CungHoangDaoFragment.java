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

import com.bhmedia.lichvannien2016.R;
import com.bhmedia.lichvannien2016.ui.activity.CungHoangDaoActivity;
import com.bhmedia.lichvannien2016.ui.utility.CalendarUtil;
import com.bhmedia.lichvannien2016.ui.wheel.ArrayWheelAdapter;
import com.bhmedia.lichvannien2016.ui.wheel.OnWheelChangedListener;
import com.bhmedia.lichvannien2016.ui.wheel.OnWheelScrollListener;
import com.bhmedia.lichvannien2016.ui.wheel.WheelView;
import com.google.android.gms.ads.AdView;

public class CungHoangDaoFragment extends BaseFragment {
	Context context;
	WheelView tuoi, cung;
	// year 1
	String mangcunghd[] = new String[] { "Capricorn", "Aquarius", "Pisces",
			"Aries", "Taurus", "Gemini", "Cancer", "Leo", "Virgo", "Libra",
			"Scorpio", "Sagittarius" };
	String mangthang[] = new String[] { "January", "February", "March", "April",
			"May", "June", "July", "August", "September", "October",
			"November", "December" };
	// Wheel scrolled flag
	private boolean wheelScrolled = false;
	int posCungHD, posThang;
	TextView iv_ketqua_xsgh;
	DateArrayAdapter arrayAdapterTuoi, arrayAdapterCung;
	String valueYear1;
	public static String thongtin;
	private AdView mAdView;

	@Override
	protected int getLayoutId() {

		return R.layout.layout_fragment_chd;
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

		iv_ketqua_xsgh = (TextView) findViewById(R.id.iv_ketqua_xsgh);
		iv_ketqua_xsgh.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// MyApp.sendAnalytic("Kết quả tử vi sự nghiệp");
				// MainActivity.pos = 15;
				// if (AdHelp.time_expired == true) {
				// if(isConnected()){
				// AdHelp.showInterstitialAds();
				// }else {
				// openKQTVSN();
				// }
				// } else {
				openKQTVSN();
				// }
			}
		});
		tuoi = (WheelView) findViewById(R.id.year1_xsgh);
		cung = (WheelView) findViewById(R.id.gioitinh_xsgh);
		CalendarUtil calendarUtil = new CalendarUtil();
		// Cung
		arrayAdapterTuoi = new DateArrayAdapter(getActivity(), mangcunghd, 6);
		tuoi.setViewAdapter(arrayAdapterTuoi);
		tuoi.setVisibleItems(2);
		tuoi.setCurrentItem(6);
		tuoi.addChangingListener(changedListener);
		tuoi.addScrollingListener(scrolledListener);
		// Thang
		arrayAdapterCung = new DateArrayAdapter(getActivity(), mangthang, 6);
		cung.setViewAdapter(arrayAdapterCung);
		cung.setVisibleItems(2);
		cung.setCurrentItem(6);
		cung.addChangingListener(changedListener);
		cung.addScrollingListener(scrolledListener);
		updateStatus();
	}

	OnWheelScrollListener scrolledListener = new OnWheelScrollListener() {
		public void onScrollStarts(WheelView wheel) {
			wheelScrolled = true;
		}

		public void onScrollEnds(WheelView wheel) {
			wheelScrolled = false;
			updateStatus();
			// updateDayOfMonth();
			// arrayAdapterDay.changeColorCurrValue(day.getCurrentItem());
			// arrayAdapterMonth.changeColorCurrValue(month.getCurrentItem());
			arrayAdapterTuoi.changeColorCurrValue(tuoi.getCurrentItem());
			arrayAdapterCung.changeColorCurrValue(cung.getCurrentItem());
		}

		@Override
		public void onScrollingStarted(WheelView wheel) {

		}

		@Override
		public void onScrollingFinished(WheelView wheel) {

		}
	};

	private final OnWheelChangedListener changedListener = new OnWheelChangedListener() {
		public void onChanged(WheelView wheel, int oldValue, int newValue) {
			if (!wheelScrolled) {
				updateStatus();
				// updateDayOfMonth();
				// arrayAdapterDay.changeColorCurrValue(day.getCurrentItem());
				arrayAdapterTuoi.changeColorCurrValue(tuoi.getCurrentItem());
				arrayAdapterCung.changeColorCurrValue(cung.getCurrentItem());
			}
		}
	};

	private WheelView getWheel(int id) {
		return (WheelView) findViewById(id);
	}

	private int getWheelValue(int id) {
		return getWheel(id).getCurrentItem();
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

	private void updateStatus() {
		// int positionTuoi = tuoi.getCurrentItem();
		// valueYear1 = mangtuoi[positionTuoi];

		// int positionDay = day.getCurrentItem();
		// valueDay = days[positionDay];

		int positionTuoi = tuoi.getCurrentItem();
		posCungHD=positionTuoi;

		int positionCung = cung.getCurrentItem();
		posThang=positionCung;
	}

	private String[] getListString(int minValue, int maxValue) {
		int sizeDate = maxValue - minValue;
		String[] lists = new String[sizeDate];
		for (int i = 0; i < sizeDate; i++) {
			lists[i] = String.valueOf(minValue + i);
		}
		return lists;
	}

	public void openKQTVSN() {
		int hthird = posCungHD;
		String idcongiap="";
		if (hthird == 0) {
			idcongiap = "Capricorn";
		} else if (hthird == 1) {
			idcongiap = "Aquarius";
		} else if (hthird == 2) {
			idcongiap = "Pisces";
		} else if (hthird == 3) {
			idcongiap = "Aries";
		} else if (hthird == 4) {
			idcongiap = "Taurus";
		} else if (hthird == 5) {
			idcongiap = "Gemini";
		} else if (hthird == 6) {
			idcongiap = "Cancer";
		} else if (hthird == 7) {
			idcongiap = "Leo";
		} else if (hthird == 8) {
			idcongiap = "Virgo";
		} else if (hthird == 9) {
			idcongiap = "Libra";
		} else if (hthird == 10) {
			idcongiap = "Scorpio";
		} else if (hthird == 11) {
			idcongiap = "Sagittarius";
		}
		int hthird2 = posThang;
		String idthang="";
		if (hthird2 == 0) {
			idthang="january";
		} else if (hthird2 == 1) {
			idthang="february";
		} else if (hthird2 == 2) {
			idthang="march";
		} else if (hthird2 == 3) {
			idthang="april";
		} else if (hthird2 == 4) {
			idthang="may";
		} else if (hthird2 == 5) {
			idthang="june";
		} else if (hthird2 == 6) {
			idthang="july";
		} else if (hthird2 == 7) {
			idthang="august";
		} else if (hthird2 == 8) {
			idthang="september";
		} else if (hthird2 == 9) {
			idthang="october";
		} else if (hthird2 == 10) {
			idthang="november";
		} else if (hthird2 == 11) {
			idthang="december";
		}
		Bundle mBundle = new Bundle();
		wheelScrolled = false;
		mBundle.putString("IDCHD", idcongiap);
		mBundle.putString("IDTHANG", idthang);
		Intent intent = new Intent(getActivity(), CungHoangDaoActivity.class);
		intent.putExtra("Bundle", mBundle);
		startActivity(intent);
	}
}
