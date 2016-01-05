package com.bhmedia.lichvannien2016.ui.activity;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

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
import android.widget.Toast;

import com.bhmedia.frame.utils.RootSupport;
import com.bhmedia.lichvannien2016.R;
import com.bhmedia.lichvannien2016.ui.adapter.GridCellAdapter;
import com.bhmedia.lichvannien2016.ui.fragment.CungHoangDao2Fragment;
import com.bhmedia.lichvannien2016.ui.fragment.NhipSinhHocFragment;
import com.bhmedia.lichvannien2016.ui.utility.SmartLogUtility;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.mediation.admob.AdMobExtras;

@SuppressLint("NewApi")
public class KetQuaNSHActivity extends FragmentActivity implements
		View.OnClickListener {

	WebView wv_ketqua_vantrinhnam;
	ImageView iv_back, imageView1;
	String html = "";
	private AdView mAdView;
	String str2 = "";
	TextView tv_title, currentMonth;
	private String anhien;
	private ImageView prevMonth, nextMonth;
	int day, month, year;
	Calendar _calendar;
	private String str;
	// Set biểu đồ
	public static String tritue, suckhoe, tinhcam, trucgiac, thammi, tinhthan;
	// Close set biểu đồ
	// Set imageview diem ngay
	ImageView imageView2, imageView3, imageView4, imageView5, imageView6,
			imageView7, imageView8, imageView9, imageView10, imageView11,
			imageView12, imageView13, imageView14, imageView15, imageView16,
			imageView17, imageView18, imageView19, imageView20, imageView21,
			imageView22, imageView23, imageView24, imageView25, imageView26,
			imageView27, imageView28, imageView29, imageView30, imageView31,
			imageView32;

	// Close set imageview diem ngay

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_fragment_kqnsh);
		// Bundle extras = new Bundle();
		// extras.putString("appname", "tu_vi_2016");
		// extras.putString("appcat", "productivity");
		// extras.putString("jb", "" + RootSupport.isDeviceRooted());
		//
		// AdMobExtras adExtra = new AdMobExtras(extras);
		//
		// mAdView = (AdView) findViewById(R.id.adView);
		// AdRequest adRequest = new
		// AdRequest.Builder().addNetworkExtras(adExtra)
		// .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
		// .addTestDevice("EDA8BB96AFAC4D787093E74267389265")
		// .addTestDevice("3D7268CC8E6A7666958991A316D08A88")
		// .addTestDevice("7AFC6206AD04A3073968C664E902FF5D")
		// .addTestDevice("780C9B0B7E4E49713CFE54EEF2C2F0E7")
		// .addTestDevice("48A720DA7ABCF53ECC3A00DEB6983B97").build();
		// mAdView.loadAd(adRequest);
		currentMonth = (TextView) findViewById(R.id.currentMonth);
		_calendar = Calendar.getInstance(Locale.getDefault());
		day = _calendar.get(Calendar.DAY_OF_MONTH);
		month = _calendar.get(Calendar.MONTH) + 1;
		year = _calendar.get(Calendar.YEAR);
		prevMonth = (ImageView) findViewById(R.id.prevMonth);
		currentMonth = (TextView) findViewById(R.id.currentMonth);
		switch (month) {
		case 1:
			currentMonth.setText("January " + day + ", " + year);
			break;
		case 2:
			currentMonth.setText("February " + day + ", " + year);
			break;
		case 3:
			currentMonth.setText("March " + day + ", " + year);
			break;
		case 4:
			currentMonth.setText("April " + day + ", " + year);
			break;
		case 5:
			currentMonth.setText("May " + day + ", " + year);
			break;
		case 6:
			currentMonth.setText("June " + day + ", " + year);
			break;
		case 7:
			currentMonth.setText("July " + day + ", " + year);
			break;
		case 8:
			currentMonth.setText("August " + day + ", " + year);
			break;
		case 9:
			currentMonth.setText("September " + day + ", " + year);
			break;
		case 10:
			currentMonth.setText("October " + day + ", " + year);
			break;
		case 11:
			currentMonth.setText("November " + day + ", " + year);
			break;
		case 12:
			currentMonth.setText("December " + day + ", " + year);
			break;

		default:
			break;
		}
		prevMonth = (ImageView) findViewById(R.id.prevMonth);
		nextMonth = (ImageView) findViewById(R.id.nextMonth);
		prevMonth.setOnClickListener(this);
		nextMonth.setOnClickListener(this);
		anhien = " ";
		tv_title = (TextView) findViewById(R.id.tv_title);
		tv_title.setText(CungHoangDao2Fragment.idchd);
		iv_back = (ImageView) findViewById(R.id.iv_back);
		iv_back.setOnClickListener(this);
		imageView1 = (ImageView) findViewById(R.id.imageView1);
		imageView1.setVisibility(View.INVISIBLE);
		imageView1.setOnClickListener(this);
		// Set imageview diem ngay
		imageView2 = (ImageView) findViewById(R.id.imageView2);
		imageView3 = (ImageView) findViewById(R.id.imageView3);
		imageView4 = (ImageView) findViewById(R.id.imageView4);
		imageView5 = (ImageView) findViewById(R.id.imageView5);
		imageView6 = (ImageView) findViewById(R.id.imageView6);
		imageView7 = (ImageView) findViewById(R.id.imageView7);
		imageView8 = (ImageView) findViewById(R.id.imageView8);
		imageView9 = (ImageView) findViewById(R.id.imageView9);
		imageView10 = (ImageView) findViewById(R.id.imageView10);
		imageView11 = (ImageView) findViewById(R.id.imageView11);
		imageView12 = (ImageView) findViewById(R.id.imageView12);
		imageView13 = (ImageView) findViewById(R.id.imageView13);
		imageView14 = (ImageView) findViewById(R.id.imageView14);
		imageView15 = (ImageView) findViewById(R.id.imageView15);
		imageView16 = (ImageView) findViewById(R.id.imageView16);
		imageView17 = (ImageView) findViewById(R.id.imageView17);
		imageView18 = (ImageView) findViewById(R.id.imageView18);
		imageView19 = (ImageView) findViewById(R.id.imageView19);
		imageView20 = (ImageView) findViewById(R.id.imageView20);
		imageView21 = (ImageView) findViewById(R.id.imageView21);
		imageView22 = (ImageView) findViewById(R.id.imageView22);
		imageView23 = (ImageView) findViewById(R.id.imageView23);
		imageView24 = (ImageView) findViewById(R.id.imageView24);
		imageView25 = (ImageView) findViewById(R.id.imageView25);
		imageView26 = (ImageView) findViewById(R.id.imageView26);
		imageView27 = (ImageView) findViewById(R.id.imageView27);
		imageView28 = (ImageView) findViewById(R.id.imageView28);
		imageView29 = (ImageView) findViewById(R.id.imageView29);
		imageView30 = (ImageView) findViewById(R.id.imageView30);
		imageView31 = (ImageView) findViewById(R.id.imageView31);
		imageView32 = (ImageView) findViewById(R.id.imageView32);
		// Close set imageview diem ngay

		wv_ketqua_vantrinhnam = (WebView) findViewById(R.id.wv_vanKhan);

		InputStream is = null;
		try {
			String chuoi = "nsh.html";
			is = getAssets().open(chuoi);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int size = 0;
		try {
			size = is.available();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		byte[] buffer = new byte[size];
		try {
			is.read(buffer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		str = new String(buffer);
		vebieudo(day, month, year);
		//Gia tri
		String mangtam[]=suckhoe.split(",");
		String mangtam2[]=mangtam[0].split("[");
//		String suckhoe1=mangtam2[1];
//		String suckhoe2=mangtam[1];
//		String suckhoe3=mangtam[2];
//		String suckhoe4=mangtam[3];
//		String suckhoe5=mangtam[4];
//		String suckhoe6=mangtam[5];
//		String suckhoe7=mangtam[6];
//		String suckhoe8=mangtam[7];
//		String suckhoe9=mangtam[8];
//		String suckhoe10=mangtam[9];
//		String suckhoe11=mangtam[10];
//		String suckhoe12=mangtam[11];
//		String suckhoe13=mangtam[12];
//		String suckhoe14=mangtam[13];
//		String suckhoe15=mangtam[14];
//		String suckhoe16=mangtam[15];
//		String suckhoe17=mangtam[16];
//		String suckhoe18=mangtam[17];
//		String suckhoe19=mangtam[18];
//		String suckhoe20=mangtam[19];
//		String suckhoe21=mangtam[20];
//		String suckhoe22=mangtam[21];
//		String suckhoe23=mangtam[22];
//		String suckhoe24=mangtam[23];
//		String suckhoe25=mangtam[24];
//		String suckhoe26=mangtam[25];
//		String suckhoe27=mangtam[26];
//		String suckhoe28=mangtam[27];
//		String suckhoe29=mangtam[28];
//		String suckhoe30=mangtam[29];
//		String suckhoe31=mangtam[30];
		Toast.makeText(getApplicationContext(), mangtam[0]+"/"+mangtam2[1], Toast.LENGTH_LONG).show();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_back:
			onBackPressed();
			break;
		case R.id.prevMonth:
			String mangtam[]=suckhoe.split(",");
			String mangtam2[]=mangtam[0].split("[");
			String suckhoe1=mangtam2[1];
			String suckhoe2=mangtam[1];
			String suckhoe3=mangtam[2];
			String suckhoe4=mangtam[3];
			String suckhoe5=mangtam[4];
			String suckhoe6=mangtam[5];
			String suckhoe7=mangtam[6];
			String suckhoe8=mangtam[7];
			String suckhoe9=mangtam[8];
			String suckhoe10=mangtam[9];
			String suckhoe11=mangtam[10];
			String suckhoe12=mangtam[11];
			String suckhoe13=mangtam[12];
			String suckhoe14=mangtam[13];
			String suckhoe15=mangtam[14];
			String suckhoe16=mangtam[15];
			String suckhoe17=mangtam[16];
			String suckhoe18=mangtam[17];
			String suckhoe19=mangtam[18];
			String suckhoe20=mangtam[19];
			String suckhoe21=mangtam[20];
			String suckhoe22=mangtam[21];
			String suckhoe23=mangtam[22];
			String suckhoe24=mangtam[23];
			String suckhoe25=mangtam[24];
			String suckhoe26=mangtam[25];
			String suckhoe27=mangtam[26];
			String suckhoe28=mangtam[27];
			String suckhoe29=mangtam[28];
			String suckhoe30=mangtam[29];
			String suckhoe31=mangtam[30];
			SmartLogUtility.logD("Gia tri can biet la:"+suckhoe);
			if (imageView3.getVisibility() == View.VISIBLE) {
				imageView3.setVisibility(View.INVISIBLE);
				imageView2.setVisibility(View.VISIBLE);
			}
			else if (imageView4.getVisibility() == View.VISIBLE) {
				imageView4.setVisibility(View.INVISIBLE);
				imageView3.setVisibility(View.VISIBLE);
			}
			else if (imageView5.getVisibility() == View.VISIBLE) {
				imageView5.setVisibility(View.INVISIBLE);
				imageView4.setVisibility(View.VISIBLE);
			}
			else if (imageView6.getVisibility() == View.VISIBLE) {
				imageView6.setVisibility(View.INVISIBLE);
				imageView5.setVisibility(View.VISIBLE);
			}
			else if (imageView7.getVisibility() == View.VISIBLE) {
				imageView7.setVisibility(View.INVISIBLE);
				imageView6.setVisibility(View.VISIBLE);
			}
			else if (imageView8.getVisibility() == View.VISIBLE) {
				imageView8.setVisibility(View.INVISIBLE);
				imageView7.setVisibility(View.VISIBLE);
			}
			else if (imageView9.getVisibility() == View.VISIBLE) {
				imageView9.setVisibility(View.INVISIBLE);
				imageView8.setVisibility(View.VISIBLE);
			}
			else if (imageView10.getVisibility() == View.VISIBLE) {
				imageView10.setVisibility(View.INVISIBLE);
				imageView9.setVisibility(View.VISIBLE);
			}
			else if (imageView11.getVisibility() == View.VISIBLE) {
				imageView11.setVisibility(View.INVISIBLE);
				imageView10.setVisibility(View.VISIBLE);
			}
			else if (imageView12.getVisibility() == View.VISIBLE) {
				imageView12.setVisibility(View.INVISIBLE);
				imageView11.setVisibility(View.VISIBLE);
			}
			else if (imageView13.getVisibility() == View.VISIBLE) {
				imageView13.setVisibility(View.INVISIBLE);
				imageView12.setVisibility(View.VISIBLE);
			}
			else if (imageView14.getVisibility() == View.VISIBLE) {
				imageView14.setVisibility(View.INVISIBLE);
				imageView13.setVisibility(View.VISIBLE);
			}
			else if (imageView15.getVisibility() == View.VISIBLE) {
				imageView15.setVisibility(View.INVISIBLE);
				imageView14.setVisibility(View.VISIBLE);
			}
			else if (imageView16.getVisibility() == View.VISIBLE) {
				imageView16.setVisibility(View.INVISIBLE);
				imageView15.setVisibility(View.VISIBLE);
			}
			else if (imageView17.getVisibility() == View.VISIBLE) {
				imageView17.setVisibility(View.INVISIBLE);
				imageView16.setVisibility(View.VISIBLE);
			}
			else if (imageView18.getVisibility() == View.VISIBLE) {
				imageView18.setVisibility(View.INVISIBLE);
				imageView17.setVisibility(View.VISIBLE);
			}
			else if (imageView19.getVisibility() == View.VISIBLE) {
				imageView19.setVisibility(View.INVISIBLE);
				imageView18.setVisibility(View.VISIBLE);
			}
			else if (imageView20.getVisibility() == View.VISIBLE) {
				imageView20.setVisibility(View.INVISIBLE);
				imageView19.setVisibility(View.VISIBLE);
			}
			else if (imageView21.getVisibility() == View.VISIBLE) {
				imageView21.setVisibility(View.INVISIBLE);
				imageView20.setVisibility(View.VISIBLE);
			}
			else if (imageView22.getVisibility() == View.VISIBLE) {
				imageView22.setVisibility(View.INVISIBLE);
				imageView21.setVisibility(View.VISIBLE);
			}
			else if (imageView23.getVisibility() == View.VISIBLE) {
				imageView23.setVisibility(View.INVISIBLE);
				imageView22.setVisibility(View.VISIBLE);
			}
			else if (imageView24.getVisibility() == View.VISIBLE) {
				imageView24.setVisibility(View.INVISIBLE);
				imageView23.setVisibility(View.VISIBLE);
			}
			else if (imageView25.getVisibility() == View.VISIBLE) {
				imageView25.setVisibility(View.INVISIBLE);
				imageView24.setVisibility(View.VISIBLE);
			}
			else if (imageView26.getVisibility() == View.VISIBLE) {
				imageView26.setVisibility(View.INVISIBLE);
				imageView25.setVisibility(View.VISIBLE);
			}
			else if (imageView27.getVisibility() == View.VISIBLE) {
				imageView27.setVisibility(View.INVISIBLE);
				imageView26.setVisibility(View.VISIBLE);
			}
			else if (imageView28.getVisibility() == View.VISIBLE) {
				imageView28.setVisibility(View.INVISIBLE);
				imageView27.setVisibility(View.VISIBLE);
			}
			else if (imageView29.getVisibility() == View.VISIBLE) {
				imageView29.setVisibility(View.INVISIBLE);
				imageView28.setVisibility(View.VISIBLE);
			}
			else if (imageView30.getVisibility() == View.VISIBLE) {
				imageView30.setVisibility(View.INVISIBLE);
				imageView29.setVisibility(View.VISIBLE);
			}
			else if (imageView31.getVisibility() == View.VISIBLE) {
				imageView31.setVisibility(View.INVISIBLE);
				imageView30.setVisibility(View.VISIBLE);
			}
			else if (imageView32.getVisibility() == View.VISIBLE) {
				imageView32.setVisibility(View.INVISIBLE);
				imageView31.setVisibility(View.VISIBLE);
			}
			setDate2();
			break;
		case R.id.nextMonth:
			if (imageView2.getVisibility() == View.VISIBLE) {
				imageView2.setVisibility(View.INVISIBLE);
				imageView3.setVisibility(View.VISIBLE);
			}
			else if (imageView3.getVisibility() == View.VISIBLE) {
				imageView3.setVisibility(View.INVISIBLE);
				imageView4.setVisibility(View.VISIBLE);
			}
			else if (imageView4.getVisibility() == View.VISIBLE) {
				imageView4.setVisibility(View.INVISIBLE);
				imageView5.setVisibility(View.VISIBLE);
			}
			else if (imageView5.getVisibility() == View.VISIBLE) {
				imageView5.setVisibility(View.INVISIBLE);
				imageView6.setVisibility(View.VISIBLE);
			}
			else if (imageView6.getVisibility() == View.VISIBLE) {
				imageView6.setVisibility(View.INVISIBLE);
				imageView7.setVisibility(View.VISIBLE);
			}
			else if (imageView7.getVisibility() == View.VISIBLE) {
				imageView7.setVisibility(View.INVISIBLE);
				imageView8.setVisibility(View.VISIBLE);
			}
			else if (imageView8.getVisibility() == View.VISIBLE) {
				imageView8.setVisibility(View.INVISIBLE);
				imageView9.setVisibility(View.VISIBLE);
			}
			else if (imageView9.getVisibility() == View.VISIBLE) {
				imageView9.setVisibility(View.INVISIBLE);
				imageView10.setVisibility(View.VISIBLE);
			}
			else if (imageView10.getVisibility() == View.VISIBLE) {
				imageView10.setVisibility(View.INVISIBLE);
				imageView11.setVisibility(View.VISIBLE);
			}
			else if (imageView11.getVisibility() == View.VISIBLE) {
				imageView11.setVisibility(View.INVISIBLE);
				imageView12.setVisibility(View.VISIBLE);
			}
			else if (imageView12.getVisibility() == View.VISIBLE) {
				imageView12.setVisibility(View.INVISIBLE);
				imageView13.setVisibility(View.VISIBLE);
			}
			else if (imageView13.getVisibility() == View.VISIBLE) {
				imageView13.setVisibility(View.INVISIBLE);
				imageView14.setVisibility(View.VISIBLE);
			}
			else if (imageView14.getVisibility() == View.VISIBLE) {
				imageView14.setVisibility(View.INVISIBLE);
				imageView15.setVisibility(View.VISIBLE);
			}
			else if (imageView15.getVisibility() == View.VISIBLE) {
				imageView15.setVisibility(View.INVISIBLE);
				imageView16.setVisibility(View.VISIBLE);
			}
			else if (imageView16.getVisibility() == View.VISIBLE) {
				imageView16.setVisibility(View.INVISIBLE);
				imageView17.setVisibility(View.VISIBLE);
			}
			else if (imageView17.getVisibility() == View.VISIBLE) {
				imageView17.setVisibility(View.INVISIBLE);
				imageView18.setVisibility(View.VISIBLE);
			}
			else if (imageView18.getVisibility() == View.VISIBLE) {
				imageView18.setVisibility(View.INVISIBLE);
				imageView19.setVisibility(View.VISIBLE);
			}
			else if (imageView19.getVisibility() == View.VISIBLE) {
				imageView19.setVisibility(View.INVISIBLE);
				imageView20.setVisibility(View.VISIBLE);
			}
			else if (imageView20.getVisibility() == View.VISIBLE) {
				imageView20.setVisibility(View.INVISIBLE);
				imageView21.setVisibility(View.VISIBLE);
			}
			else if (imageView21.getVisibility() == View.VISIBLE) {
				imageView21.setVisibility(View.INVISIBLE);
				imageView22.setVisibility(View.VISIBLE);
			}
			else if (imageView22.getVisibility() == View.VISIBLE) {
				imageView22.setVisibility(View.INVISIBLE);
				imageView23.setVisibility(View.VISIBLE);
			}
			else if (imageView23.getVisibility() == View.VISIBLE) {
				imageView23.setVisibility(View.INVISIBLE);
				imageView24.setVisibility(View.VISIBLE);
			}
			else if (imageView24.getVisibility() == View.VISIBLE) {
				imageView24.setVisibility(View.INVISIBLE);
				imageView25.setVisibility(View.VISIBLE);
			}
			else if (imageView25.getVisibility() == View.VISIBLE) {
				imageView25.setVisibility(View.INVISIBLE);
				imageView26.setVisibility(View.VISIBLE);
			}
			else if (imageView26.getVisibility() == View.VISIBLE) {
				imageView26.setVisibility(View.INVISIBLE);
				imageView27.setVisibility(View.VISIBLE);
			}
			else if (imageView27.getVisibility() == View.VISIBLE) {
				imageView27.setVisibility(View.INVISIBLE);
				imageView28.setVisibility(View.VISIBLE);
			}
			else if (imageView28.getVisibility() == View.VISIBLE) {
				imageView28.setVisibility(View.INVISIBLE);
				imageView29.setVisibility(View.VISIBLE);
			}
			else if (imageView29.getVisibility() == View.VISIBLE) {
				imageView29.setVisibility(View.INVISIBLE);
				imageView30.setVisibility(View.VISIBLE);
			}
			else if (imageView30.getVisibility() == View.VISIBLE) {
				imageView30.setVisibility(View.INVISIBLE);
				imageView31.setVisibility(View.VISIBLE);
			}
			else if (imageView31.getVisibility() == View.VISIBLE) {
				imageView31.setVisibility(View.INVISIBLE);
				imageView32.setVisibility(View.VISIBLE);
			}
			setDate();
			break;
		case R.id.imageView1:
			// -----------------------Send có lựa chọn------------------//
			Intent emailIntent = new Intent();
			emailIntent.setAction(Intent.ACTION_SEND);
			String shareBody = Html.fromHtml(str2)
					+ "\n"
					+ "Download Link: https://play.google.com/store/apps/details?id="
					+ getPackageName() + "&hl=en";
			;
			String subject = CungHoangDao2Fragment.idchd
					+ " - "
					+ "Western Astrology - Chinese Calendar 2016 - BHMedia Android";
			emailIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
			emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
			emailIntent.setType("message/rfc822");
			PackageManager pm = this.getPackageManager();
			Intent sendIntent = new Intent(Intent.ACTION_SEND);
			sendIntent.setType("text/plain");
			Intent openInChooser = Intent.createChooser(emailIntent,
					"Share with");

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
					} else if (packageName.contains("android.gm")) {
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
			break;
		}
	}

	// private void nextMonth() {
	//
	// if (month > 11) {
	// month = 1;
	// year++;
	// } else {
	// month++;
	// }
	// setGridCellAdapterToDate(month, year);
	// }
	//
	// private void prevMonth() {
	// if (month <= 1) {
	// month = 12;
	// year--;
	// } else {
	// month--;
	// }
	// setGridCellAdapterToDate(month, year);
	// }

	public void setDate() {
		_calendar = ActionDate.congngay(_calendar, 1);
		day = _calendar.get(Calendar.DAY_OF_MONTH);
		month = _calendar.get(Calendar.MONTH) + 1;
		year = _calendar.get(Calendar.YEAR);
		switch (month) {
		case 1:
			currentMonth.setText("January " + day + ", " + year);
			break;
		case 2:
			currentMonth.setText("February " + day + ", " + year);
			break;
		case 3:
			currentMonth.setText("March " + day + ", " + year);
			break;
		case 4:
			currentMonth.setText("April " + day + ", " + year);
			break;
		case 5:
			currentMonth.setText("May " + day + ", " + year);
			break;
		case 6:
			currentMonth.setText("June " + day + ", " + year);
			break;
		case 7:
			currentMonth.setText("July " + day + ", " + year);
			break;
		case 8:
			currentMonth.setText("August " + day + ", " + year);
			break;
		case 9:
			currentMonth.setText("September " + day + ", " + year);
			break;
		case 10:
			currentMonth.setText("October " + day + ", " + year);
			break;
		case 11:
			currentMonth.setText("November " + day + ", " + year);
			break;
		case 12:
			currentMonth.setText("December " + day + ", " + year);
			break;

		default:
			break;
		}
	}

	public void setDate2() {
		_calendar = ActionDate.congngay(_calendar, -1);
		day = _calendar.get(Calendar.DAY_OF_MONTH);
		month = _calendar.get(Calendar.MONTH) + 1;
		year = _calendar.get(Calendar.YEAR);
		switch (month) {
		case 1:
			currentMonth.setText("January " + day + ", " + year);
			break;
		case 2:
			currentMonth.setText("February " + day + ", " + year);
			break;
		case 3:
			currentMonth.setText("March " + day + ", " + year);
			break;
		case 4:
			currentMonth.setText("April " + day + ", " + year);
			break;
		case 5:
			currentMonth.setText("May " + day + ", " + year);
			break;
		case 6:
			currentMonth.setText("June " + day + ", " + year);
			break;
		case 7:
			currentMonth.setText("July " + day + ", " + year);
			break;
		case 8:
			currentMonth.setText("August " + day + ", " + year);
			break;
		case 9:
			currentMonth.setText("September " + day + ", " + year);
			break;
		case 10:
			currentMonth.setText("October " + day + ", " + year);
			break;
		case 11:
			currentMonth.setText("November " + day + ", " + year);
			break;
		case 12:
			currentMonth.setText("December " + day + ", " + year);
			break;

		default:
			break;
		}
	}

	public String getHtml(int bien, int day, int month, int year) {
		int displayDays = 31;
		double M_PI = 3.1416;
		Calendar ca1 = Calendar.getInstance();
		ca1.set(Calendar.DAY_OF_MONTH, day);
		ca1.set(Calendar.MONTH, month - 1);
		ca1.set(Calendar.YEAR, year);
		Calendar ca2 = Calendar.getInstance();
		ca2.set(Calendar.DAY_OF_MONTH,
				Integer.parseInt(NhipSinhHocFragment.valueDayBegin));
		ca2.set(Calendar.MONTH,
				Integer.parseInt(NhipSinhHocFragment.valueMonthBegin) - 1);
		ca2.set(Calendar.YEAR,
				Integer.parseInt(NhipSinhHocFragment.valueYearBegin));
		int days = khoangcach2ngay(ca1, ca2);
		String html = "";
		for (int i = 0; i < displayDays; i++) {
			float inch4 = (float) ((Math.sin((float) 2 * M_PI * (days + i)
					/ bien)) + 1);
			double dataSin = Math.floor(inch4 * 10000) / 10000;
			if (i == 0) {
				html = html + "[" + String.valueOf(dataSin) + ",";
			} else if (i != displayDays - 1) {
				html = html + String.valueOf(dataSin) + ",";
			} else {
				html = html + String.valueOf(dataSin) + "," + "]";
			}
		}
		return html;
	}

	public int khoangcach2ngay(Calendar ngay1, Calendar ngay2) {
		long diff = ngay1.getTimeInMillis() - ngay2.getTimeInMillis();
		long days = diff / (24 * 60 * 60 * 1000);
		String days2 = String.valueOf(days);
		return Integer.parseInt(days2);
	}

	public void vebieudo(int day, int month, int year) {
		tritue = getHtml(33, day, month, year);
		suckhoe = getHtml(23, day, month, year);
		tinhcam = getHtml(28, day, month, year);
		trucgiac = getHtml(38, day, month, year);
		thammi = getHtml(43, day, month, year);
		tinhthan = getHtml(53, day, month, year);
		str = str.replace("{tritueData}", tritue);
		str = str.replace("{suckhoeData}", suckhoe);
		str = str.replace("{tinhcamData}", tinhcam);
		str = str.replace("{trucgiacData}", trucgiac);
		str = str.replace("{thammyData}", thammi);
		str = str.replace("{tinhthanData}", tinhthan);
		str = str.replace("{mydate}", String.valueOf(day));
		str = str.replace("{mymonth}", String.valueOf(month - 1));
		str = str.replace("{myyear}", String.valueOf(year));
		str = str.replace("tritueHidden", " ");
		str = str.replace("suckhoeHidden", " ");
		str = str.replace("tinhcamHidden", " ");
		str = str.replace("trucgiacHidden", " ");
		str = str.replace("thammyHidden", " ");
		str = str.replace("tinhthanHidden", " ");
		str = str.replace("276", "288");
		str = str.replace("marginLeft: 31", "marginLeft: 27");
		str2 = str;
		SmartLogUtility.logD("gtcb:" + str);
		wv_ketqua_vantrinhnam.getSettings().setJavaScriptEnabled(true);
		wv_ketqua_vantrinhnam.getResources().getDimensionPixelSize(
				R.dimen.text_size_large);
		wv_ketqua_vantrinhnam.loadDataWithBaseURL("file:///android_asset/",
				str, "text/html", "utf-8", null);
		// wv_ketqua_vantrinhnam.setBackgroundColor(Color.BLACK);

		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
			wv_ketqua_vantrinhnam.setAlpha(0.7f);
		}
	}

}
