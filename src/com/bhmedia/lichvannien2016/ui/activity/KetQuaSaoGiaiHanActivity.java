package com.bhmedia.lichvannien2016.ui.activity;

import java.util.ArrayList;
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

import com.bhmedia.frame.utils.RootSupport;
import com.bhmedia.lichvannien2016.R;
import com.bhmedia.lichvannien2016.ui.fragment.XemSaoGiaiHanFragment;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.mediation.admob.AdMobExtras;

@SuppressLint("NewApi")
public class KetQuaSaoGiaiHanActivity extends FragmentActivity implements
		View.OnClickListener {

	WebView wv_ketqua_sgh;
	ImageView iv_back, iv_share;
	String html = "";
	private AdView mAdView;

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_fragment_kqsaogiaihan);
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
		iv_share = (ImageView) findViewById(R.id.iv_share);
		iv_back.setOnClickListener(this);
		iv_share.setOnClickListener(this);
		wv_ketqua_sgh = (WebView) findViewById(R.id.wv_ketqua_sgh);

		// VanTrinhNamDataBaseHelper vanTrinhNamDataBaseHelper = new
		// VanTrinhNamDataBaseHelper(this);
		// Bundle mBundle =getIntent().getExtras().getBundle("Bundle");
		// tv_ngaySinhDuongLich.setText(mBundle.getString("NGAY") + " - " +
		// mBundle.getString("THANG") + " - " + mBundle.getString("NAM"));
		// Calendar calendar = Calendar.getInstance();
		// CalendarUtil calendarUtil = new CalendarUtil();
		// int[] convert =
		// calendarUtil.convertSolar2Lunar(Integer.parseInt(mBundle.getString("NGAY")),
		// Integer.parseInt(mBundle.getString("THANG")),
		// Integer.parseInt(mBundle.getString("NAM")), 7);
		// calendar.set(Calendar.DAY_OF_MONTH, convert[0]);
		// calendar.set(Calendar.MONTH, convert[1] - 1);
		// calendar.set(Calendar.YEAR, convert[2]);
		// Toast.makeText(getApplicationContext(),
		// NguHanhNgayTotFragment.thongtin, Toast.LENGTH_LONG).show();
		// String str = NguHanhNgayTotFragment.thongtin;
		// html = "<html><body><font color ='#000000'" +
		// NguHanhNgayTotFragment.thongtin
		// + "</font></body></html>";
		wv_ketqua_sgh.getSettings().setJavaScriptEnabled(true);
		wv_ketqua_sgh.getResources().getDimensionPixelSize(
				R.dimen.text_size_large);
		wv_ketqua_sgh.loadDataWithBaseURL("file:///android_asset/images",
				XemSaoGiaiHanFragment.thongtin, "text/html", "utf-8", null);
		// wv_ketqua_vantrinhnam.setBackgroundColor(Color.BLACK);

		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
			wv_ketqua_sgh.setAlpha(0.7f);
		}
	}

	// }

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
			String shareBody = String.valueOf(Html
					.fromHtml(XemSaoGiaiHanFragment.thongtin))
					+ "\n\n"
					+ "Link download ứng dụng: https://play.google.com/store/apps/details?id="
					+ getPackageName() + "&hl=en";
			;
			String subject = "Ứng dụng 'Lịch Vạn Niên 2016' - BHMedia Android";
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
								"Link download ứng dụng: https://play.google.com/store/apps/details?id="
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
								"Link download ứng dụng: https://play.google.com/store/apps/details?id="
										+ getPackageName() + "&hl=en");
					} else if (packageName.contains("mms")) {
						intent.putExtra(Intent.EXTRA_TEXT,
								"Link download ứng dụng: https://play.google.com/store/apps/details?id="
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
			// String shareBody = String.valueOf(Html
			// .fromHtml(XemSaoGiaiHanFragment.thongtin))
			// + "\n\n"
			// +
			// "Link download ứng dụng: https://play.google.com/store/apps/details?id="
			// + getPackageName() + "&hl=en";
			// ;
			// String subject =
			// "Ứng dụng 'Lịch Vạn Niên 2016' - BHMedia Android";
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
