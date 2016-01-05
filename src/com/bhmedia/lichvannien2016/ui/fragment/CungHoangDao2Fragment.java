package com.bhmedia.lichvannien2016.ui.fragment;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;

import com.bhmedia.lichvannien2016.R;
import com.bhmedia.lichvannien2016.ui.activity.Itemlist;
import com.bhmedia.lichvannien2016.ui.activity.KQCHDActivity;
import com.bhmedia.lichvannien2016.ui.adapter.CungHoangDaoAdapter;
import com.google.android.gms.ads.AdView;

public class CungHoangDao2Fragment extends BaseFragment {
	Context context;
	// Wheel scrolled flag
	public static String thongtin;
	private AdView mAdView;
	public static String idchd = "";

	@Override
	protected int getLayoutId() {

		return R.layout.layout_fragment_cunghd2;
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
		ArrayList<Itemlist> image_details = GetSearchResults();
		final GridView gv1 = (GridView) findViewById(R.id.gvcunghoangdao);
		gv1.setAdapter(new CungHoangDaoAdapter(context, image_details));

		gv1.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// MyApp.sendAnalytic("Kết quả cung hoàng đạo");
				switch (position) {
				case 0:
					idchd = "Aries";
					// MainActivity.pos = 17;
					// if (AdHelp.time_expired == true) {
					// if (isConnected()) {
					// AdHelp.showInterstitialAds();
					// } else {
					// openKQCHD();
					// }
					// } else {
					openKQCHD();
					// }
					break;
				case 1:
					idchd = "Taurus";
					// MainActivity.pos = 18;
					// if (AdHelp.time_expired == true) {
					// if (isConnected()) {
					// AdHelp.showInterstitialAds();
					// } else {
					// openKQCHD();
					// }
					// } else {
					openKQCHD();
					// }
					break;
				case 2:
					idchd = "Gemini";
					// MainActivity.pos = 19;
					// if (AdHelp.time_expired == true) {
					// if (isConnected()) {
					// AdHelp.showInterstitialAds();
					// } else {
					// openKQCHD();
					// }
					// } else {
					openKQCHD();
					// }
					break;
				case 3:
					idchd = "Cancer";
					// MainActivity.pos = 20;
					// if (AdHelp.time_expired == true) {
					// if (isConnected()) {
					// AdHelp.showInterstitialAds();
					// } else {
					// openKQCHD();
					// }
					// } else {
					openKQCHD();
					// }
					break;
				case 4:
					idchd = "Leo";
					// MainActivity.pos = 21;
					// if (AdHelp.time_expired == true) {
					// if (isConnected()) {
					// AdHelp.showInterstitialAds();
					// } else {
					// openKQCHD();
					// }
					// } else {
					openKQCHD();
					// }
					break;
				case 5:
					idchd = "Virgo";
					// MainActivity.pos = 22;
					// if (AdHelp.time_expired == true) {
					// if (isConnected()) {
					// AdHelp.showInterstitialAds();
					// } else {
					// openKQCHD();
					// }
					// } else {
					openKQCHD();
					// }
					break;
				case 6:
					idchd = "Libra";
					// MainActivity.pos = 23;
					// if (AdHelp.time_expired == true) {
					// if (isConnected()) {
					// AdHelp.showInterstitialAds();
					// } else {
					// openKQCHD();
					// }
					// } else {
					openKQCHD();
					// }
					break;
				case 7:
					idchd = "Scorpio";
					// MainActivity.pos = 24;
					// if (AdHelp.time_expired == true) {
					// if (isConnected()) {
					// AdHelp.showInterstitialAds();
					// } else {
					// openKQCHD();
					// }
					// } else {
					openKQCHD();
					// }
					break;
				case 8:
					idchd = "Sagittarius";
					// MainActivity.pos = 25;
					// if (AdHelp.time_expired == true) {
					// if (isConnected()) {
					// AdHelp.showInterstitialAds();
					// } else {
					// openKQCHD();
					// }
					// } else {
					openKQCHD();
					// }
					break;
				case 9:
					idchd = "Capricorn";
					// MainActivity.pos = 26;
					// if (AdHelp.time_expired == true) {
					// if (isConnected()) {
					// AdHelp.showInterstitialAds();
					// } else {
					// openKQCHD();
					// }
					// } else {
					openKQCHD();
					// }
					break;
				case 10:
					idchd = "Aquarius";
					// MainActivity.pos = 27;
					// if (AdHelp.time_expired == true) {
					// if (isConnected()) {
					// AdHelp.showInterstitialAds();
					// } else {
					// openKQCHD();
					// }
					// } else {
					openKQCHD();
					// }
					break;
				case 11:
					idchd = "Pisces";
					// MainActivity.pos = 28;
					// if (AdHelp.time_expired == true) {
					// if (isConnected()) {
					// AdHelp.showInterstitialAds();
					// } else {
					// openKQCHD();
					// }
					// } else {
					openKQCHD();
					// }
					break;
				}
			}
		});
	}

	private ArrayList<Itemlist> GetSearchResults() {
		ArrayList<Itemlist> results = new ArrayList<Itemlist>();

		Itemlist item_details = new Itemlist();

		item_details = new Itemlist();
		item_details.setName("Aries");
		item_details.setItemDescription("21/3 - 20/4");
		item_details.setImageNumber(1);
		results.add(item_details);

		item_details = new Itemlist();
		item_details.setName("Taurus");
		item_details.setItemDescription("21/4 - 20/5");
		item_details.setImageNumber(2);
		results.add(item_details);

		item_details = new Itemlist();
		item_details.setName("Gemini");

		item_details.setItemDescription("21/5 - 21/6");
		item_details.setImageNumber(3);
		results.add(item_details);

		item_details = new Itemlist();
		item_details.setName("Cancer");
		item_details.setItemDescription("22/6 - 22/7");
		item_details.setImageNumber(4);
		results.add(item_details);

		item_details = new Itemlist();
		item_details.setName("Leo");
		item_details.setItemDescription("23/7 - 22/8");
		item_details.setImageNumber(5);
		results.add(item_details);

		item_details = new Itemlist();
		item_details.setName("Virgo");
		item_details.setItemDescription("23/8 - 22/9");
		item_details.setImageNumber(6);
		results.add(item_details);

		item_details = new Itemlist();
		item_details.setName("Libra");
		item_details.setItemDescription("23/9 - 22/10");
		item_details.setImageNumber(7);
		results.add(item_details);

		item_details = new Itemlist();
		item_details.setName("Scorpio");
		item_details.setItemDescription("23/10 - 21/11");
		item_details.setImageNumber(8);
		results.add(item_details);

		item_details = new Itemlist();
		item_details.setName("Sagittarius");
		item_details.setItemDescription("22/11 - 21/12");
		item_details.setImageNumber(9);
		results.add(item_details);

		item_details = new Itemlist();
		item_details.setName("Capricorn");
		item_details.setItemDescription("22/12 - 19/1");
		item_details.setImageNumber(10);
		results.add(item_details);

		item_details = new Itemlist();
		item_details.setName("Aquarius");
		item_details.setItemDescription("20/1 - 18/2");
		item_details.setImageNumber(11);
		results.add(item_details);

		item_details = new Itemlist();
		item_details.setName("Pisces");
		item_details.setItemDescription("19/2 - 20/3");
		item_details.setImageNumber(12);
		results.add(item_details);

		return results;
	}

	public void openKQCHD() {
		Intent it = new Intent(getActivity(), KQCHDActivity.class);
		startActivity(it);
	}
}
