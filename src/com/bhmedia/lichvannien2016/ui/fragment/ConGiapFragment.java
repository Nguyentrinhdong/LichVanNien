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

import com.bhmedia.lichvannien2016.R;
import com.bhmedia.lichvannien2016.ui.activity.Itemlist;
import com.bhmedia.lichvannien2016.ui.activity.KQPDActivity;
import com.bhmedia.lichvannien2016.ui.adapter.CungHoangDaoAdapter;
import com.google.android.gms.ads.AdView;

public class ConGiapFragment extends BaseFragment {
	Context context;
	// Wheel scrolled flag
	public static String thongtin;
	private AdView mAdView;
	public static String ketquapd="";
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
					ketquapd="Rat";
					idchd = "ty";
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
					ketquapd="Ox";
					idchd = "suu";
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
					ketquapd="Tiger";
					idchd = "dan";
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
					ketquapd="Rabbit";
					idchd = "mao";
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
					ketquapd="Dragon";
					idchd = "thin";
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
					ketquapd="Snake";
					idchd = "ti";
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
					ketquapd="Horse";
					idchd = "ngo";
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
					ketquapd="Sheep";
					idchd = "mui";
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
					ketquapd="Monkey";
					idchd = "than";
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
					ketquapd="Rooster";
					idchd = "dau";
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
					ketquapd="Dog";
					idchd = "tuat";
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
					ketquapd="Pig";
					idchd = "hoi";
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
		item_details.setName("Rat");
		item_details.setItemDescription("");
		item_details.setImageNumber(1);
		results.add(item_details);

		item_details = new Itemlist();
		item_details.setName("Ox");
		item_details.setItemDescription("");
		item_details.setImageNumber(2);
		results.add(item_details);

		item_details = new Itemlist();
		item_details.setName("Tiger");

		item_details.setItemDescription("21/5 - 21/6");
		item_details.setImageNumber(3);
		results.add(item_details);

		item_details = new Itemlist();
		item_details.setName("Rabbit");
		item_details.setItemDescription("");
		item_details.setImageNumber(4);
		results.add(item_details);

		item_details = new Itemlist();
		item_details.setName("Dragon");
		item_details.setItemDescription("");
		item_details.setImageNumber(5);
		results.add(item_details);

		item_details = new Itemlist();
		item_details.setName("Snake");
		item_details.setItemDescription("");
		item_details.setImageNumber(6);
		results.add(item_details);

		item_details = new Itemlist();
		item_details.setName("Horse");
		item_details.setItemDescription("");
		item_details.setImageNumber(7);
		results.add(item_details);

		item_details = new Itemlist();
		item_details.setName("Sheep");
		item_details.setItemDescription("");
		item_details.setImageNumber(8);
		results.add(item_details);

		item_details = new Itemlist();
		item_details.setName("Monkey");
		item_details.setItemDescription("");
		item_details.setImageNumber(9);
		results.add(item_details);

		item_details = new Itemlist();
		item_details.setName("Rooster");
		item_details.setItemDescription("");
		item_details.setImageNumber(10);
		results.add(item_details);

		item_details = new Itemlist();
		item_details.setName("Dog");
		item_details.setItemDescription("");
		item_details.setImageNumber(11);
		results.add(item_details);

		item_details = new Itemlist();
		item_details.setName("Pig");
		item_details.setItemDescription("");
		item_details.setImageNumber(12);
		results.add(item_details);

		return results;
	}

	public void openKQCHD() {
		Intent it = new Intent(getActivity(), KQPDActivity.class);
		startActivity(it);
	}
}
