package com.bhmedia.lichvannien2016.ui.activity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bhmedia.frame.utils.RootSupport;
import com.bhmedia.lichvannien2016.R;
import com.bhmedia.lichvannien2016.ui.utility.CalendarUtil;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.mediation.admob.AdMobExtras;

/**
 * Created by Van on 4/21/2015.
 */
@SuppressLint("NewApi")
public class ChiTietNgayActivity extends FragmentActivity implements
		View.OnClickListener {

	TextView tv_tietKhi, tv_tuoiXungKhac, tv_huongXuatHanh, tv_gioHoangDao,
			tv_title;
	WebView wv_saoTotXau;
	Calendar calendar;
	Bundle mBundle;
	int dd, mm, yy;
	ImageView iv_back, iv_share;
	String html1 = "";
	private AdView mAdView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_fragment_chitietngay);
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
		tv_title = (TextView) findViewById(R.id.tv_title);
		iv_back = (ImageView) findViewById(R.id.iv_back);
		iv_share = (ImageView) findViewById(R.id.iv_share);
		iv_share.setOnClickListener(this);
		iv_back.setOnClickListener(this);
		tv_gioHoangDao = (TextView) findViewById(R.id.tv_gioHoangDao);
		tv_tietKhi = (TextView) findViewById(R.id.tv_tietKhi);
		tv_tuoiXungKhac = (TextView) findViewById(R.id.tv_tuoiXungKhac);
		tv_huongXuatHanh = (TextView) findViewById(R.id.tv_huongXuatHanh);
		wv_saoTotXau = (WebView) findViewById(R.id.wv_saoTotXau);
		// wv_saoTotXau.setBackgroundColor(Color.BLACK);

		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
			wv_saoTotXau.setAlpha(0.7f);
		}
		// wv_nhiThapBatTu.setBackgroundColor(Color.BLACK);

		;

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
		String str = calendarUtil.getSaoTotXau(dd, mm, yy, convert[0],
				convert[1]);
		String html = "<html><body>" + str + "</body></html>";
		wv_saoTotXau.getSettings().setJavaScriptEnabled(true);
		wv_saoTotXau
				.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);

		mBundle = getIntent().getExtras().getBundle("Bundle");

		tv_title.setText(ActionDate.getMonthEnglish(mBundle.getInt("THANG"))
				+ " " + mBundle.getInt("NGAY") + ", " + mBundle.getInt("NAM"));
		if (mBundle != null) {
			tv_gioHoangDao.setText(calendarUtil.getGioHoangDao(
					mBundle.getInt("NGAY"), mBundle.getInt("THANG"),
					mBundle.getInt("NAM")));
			tv_tietKhi.setText(calendarUtil.getTietKhi(mBundle.getInt("NGAY"),
					mBundle.getInt("THANG"), mBundle.getInt("NAM")));
			tv_tuoiXungKhac.setText(calendarUtil.getTuoiXungKhac(
					mBundle.getInt("NGAY"), mBundle.getInt("THANG"),
					mBundle.getInt("NAM")));
			tv_huongXuatHanh.setText(calendarUtil.getHuongXuatHanh(
					mBundle.getInt("NGAY"), mBundle.getInt("THANG"),
					mBundle.getInt("NAM")));

			int[] convert1 = calendarUtil.convertSolar2Lunar(
					mBundle.getInt("NGAY"), mBundle.getInt("THANG"),
					mBundle.getInt("NAM"), 7);
			String str1 = calendarUtil.getSaoTotXau(mBundle.getInt("NGAY"),
					mBundle.getInt("THANG"), mBundle.getInt("NAM"),
					convert1[0], convert1[1]);
			html1 = "<html><body>" + str1 + "</body></html>";
			wv_saoTotXau.getSettings().setJavaScriptEnabled(true);
			wv_saoTotXau.loadDataWithBaseURL(null, html1, "text/html", "utf-8",
					null);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_back:
			onBackPressed();
			break;
		case R.id.iv_share:
			// -----------------------Send có lựa chọn------------------//
			Intent emailIntent = new Intent();
			emailIntent.setAction(Intent.ACTION_SEND);
			String shareBody = "Full details of the day:"
					+ "Day "
					+ mBundle.getInt("NGAY")
					+ " Month "
					+ mBundle.getInt("THANG")
					+ " Year "
					+ mBundle.getInt("NAM")
					+ "\n"
					+ "Auspicious Hour\n"
					+ tv_gioHoangDao.getText().toString()
					+ "Solar Terms\n"
					+ tv_tietKhi.getText().toString()
					+ "Incompatible Zodiac Signs\n"
					+ tv_tuoiXungKhac.getText().toString()
					+ "Auspicious Directions\n"
					+ tv_huongXuatHanh.getText().toString()
					+ "Lucky Star - Unlucky Star\n"
					+ String.valueOf(Html.fromHtml(html1))
					+ "\n\n"
					+ "Download Link: https://play.google.com/store/apps/details?id="
					+ getPackageName() + "&hl=en";
			String subject = tv_title.getText().toString()+" - 'Daily View' - Chinese Calendar 2016 - BHMedia Android";
			emailIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
			emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
			emailIntent.setType("message/rfc822");
			PackageManager pm = this.getPackageManager();
			Intent sendIntent = new Intent(Intent.ACTION_SEND);
			sendIntent.setType("text/plain");
			Intent openInChooser = Intent.createChooser(emailIntent, "Chia sẻ");

			List<ResolveInfo> resInfo = pm.queryIntentActivities(sendIntent, 0);
			List<LabeledIntent> intentList = new ArrayList<LabeledIntent>();
			for (int i = 0; i < resInfo.size(); i++) {
				// Extract the label, append it, and repackage it in a
				// LabeledIntent
				ResolveInfo ri = resInfo.get(i);
				String packageName = ri.activityInfo.packageName;
				if (packageName.contains("android.email")) {
					emailIntent.setPackage(packageName);
				} else if (packageName.contains("twitter")
						|| packageName.contains("facebook")
						|| packageName.contains("mms")
						|| packageName.contains("android.gm")) {
					Intent intent = new Intent();
					intent.setComponent(new ComponentName(packageName,
							ri.activityInfo.name));
					intent.setAction(Intent.ACTION_SEND);
					intent.setType("text/plain");
					if (packageName.contains("twitter")) {
						intent.putExtra(Intent.EXTRA_TEXT,
								"Download Link: https://play.google.com/store/apps/details?id="
										+ getPackageName() + "&hl=en");
					} else if (packageName.contains("facebook")) {
						// Warning: Facebook IGNORES our text. They say
						// "These fields are intended for users to express themselves. Pre-filling these fields erodes the authenticity of the user voice."
						// One workaround is to use the Facebook SDK to post,
						// but that doesn't allow the user to choose how they
						// want to share. We can also make a custom landing
						// page, and the link
						// will show the <meta content ="..."> text from that
						// page with our link in Facebook.
						intent.putExtra(Intent.EXTRA_TEXT,
								"Download Link: https://play.google.com/store/apps/details?id="
										+ getPackageName() + "&hl=en");
					} else if (packageName.contains("mms")) {
						intent.putExtra(Intent.EXTRA_TEXT,
								"Download Link: https://play.google.com/store/apps/details?id="
										+ getPackageName() + "&hl=en");
					} else if (packageName.contains("android.gm")) { // If Gmail
																		// shows
																		// up
																		// twice,
																		// try
																		// removing
																		// this
																		// else-if
																		// clause
																		// and
																		// the
																		// reference
																		// to
																		// "android.gm"
																		// above
						intent.putExtra(Intent.EXTRA_TEXT, shareBody);
						intent.putExtra(Intent.EXTRA_SUBJECT, subject);
						intent.setType("message/rfc822");
					}

					intentList.add(new LabeledIntent(intent, packageName, ri
							.loadLabel(pm), ri.icon));
				}
			}
			// convert intentList to array
			LabeledIntent[] extraIntents = intentList
					.toArray(new LabeledIntent[intentList.size()]);

			openInChooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, extraIntents);
			this.startActivity(openInChooser);
			// -----------------------Close send có lựa chọn------------//

			// String shareBody = "Chi tiết ngày:" + "Ngày "
			// + mBundle.getInt("NGAY")
			// + " Tháng "
			// + mBundle.getInt("THANG")
			// + " Năm "
			// + mBundle.getInt("NAM")
			// + "\n"
			// + "GIỜ HOÀNG ĐẠO\n"
			// + tv_gioHoangDao.getText().toString()
			// + "TIẾT KHÍ \n"
			// + tv_tietKhi.getText().toString()
			// + "TUỔI XUNG KHẮC\n"
			// + tv_tuoiXungKhac.getText().toString()
			// + "HƯỚNG XUẤT HÀNH\n"
			// + tv_huongXuatHanh.getText().toString()
			// + "SAO TỐT - SAO XẤU\n"
			// + String.valueOf(Html.fromHtml(html1))
			// + "NHỊ THẬP BÁT TÚ\n"
			// + String.valueOf(Html.fromHtml(html_nhiThapBatTu1))
			// + "\n\n"
			// +
			// "Link download ứng dụng: https://play.google.com/store/apps/details?id="
			// + getPackageName() + "&hl=en";
			// String subject =
			// "Ứng dụng 'Lịch Vạn Niên 2015' - BHMedia Android";
			// Intent sharingIntent = new Intent(
			// android.content.Intent.ACTION_SEND);
			// sharingIntent.setType("text/plain");
			// sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
			// subject);
			// sharingIntent
			// .putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
			// startActivity(Intent.createChooser(sharingIntent, getResources()
			// .getString(R.string.share_using)));
			break;
		}
	}
}
