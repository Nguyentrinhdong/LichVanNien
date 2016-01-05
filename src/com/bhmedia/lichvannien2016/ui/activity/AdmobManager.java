package com.bhmedia.lichvannien2016.ui.activity;

import java.util.Random;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RelativeLayout;

import com.bhmedia.frame.utils.Cons;
import com.bhmedia.frame.utils.RootSupport;
import com.bhmedia.frame.utils.ScreenUtils;
import com.bhmedia.lichvannien2016.ui.utility.Defi;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;
import com.google.android.gms.ads.mediation.admob.AdMobExtras;


@SuppressLint("NewApi")
public class AdmobManager extends BroadcastReceiver{
	private static AdmobManager adManager;

	public PublisherInterstitialAd adFull;
	private PublisherAdView adView;
	private PublisherAdRequest adRequestAdview;
	
	public boolean allowShowQCFull = true;
		
	public static AdmobManager getInstance(Context context) {
		if (adManager == null) {
			adManager = new AdmobManager();
			return adManager;
		}
		return adManager;
	}

	public void init() {
		loadQuangCaoBanner();
		loadQuangCaoFull();
	}

	public PublisherAdRequest getAdRequest(String appname, String appcat,
			String adposition, String appviews) {
		Bundle bundle = new Bundle();

		if (appname != null)
			bundle.putString("appname", appname);
		if (appcat != null)
			bundle.putString("appcat", appcat);
		if (adposition != null)
			bundle.putString("adposition", adposition);
		if (appviews != null)
			bundle.putString("appviews", appviews);

		bundle.putString("jb", "" + RootSupport.isDeviceRooted());

		AdMobExtras extras = new AdMobExtras(bundle);
		PublisherAdRequest adRequest = new PublisherAdRequest.Builder()
				.addNetworkExtras(extras)
				.addTestDevice("D4209CD7FC4281F07A66D2F2A85DE420").build();

		return adRequest;
	}

	public void loadQuangCaoBanner () {
		int a = new Random().nextInt();		
		adView = new PublisherAdView(MainActivity.me);

		float screenWidth = ScreenUtils.getWindowsWidth(MainActivity.me);
		float density = (float) MainActivity.me.getResources().getDisplayMetrics().density;
		if (screenWidth >= density * 728) {
			if (Cons.admob3.length() > 5 && (a % 2 == 0))
				adView.setAdUnitId(Cons.admob3);
			else
				adView.setAdUnitId(Defi.admob1);
			adView.setAdSizes(AdSize.LEADERBOARD);
		} else {
			if (Cons.admob3.length() > 5 && (a % 2 == 0))
				adView.setAdUnitId(Cons.admob3);
			else
				adView.setAdUnitId(Defi.admob2);
			adView.setAdSizes(AdSize.BANNER);
		}

		adView.setVisibility(View.GONE);

		if (MainActivity.me.rootadView != null) {
			RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.MATCH_PARENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);
			MainActivity.me.rootadView.addView(adView, params);
		}

		AdListener adlistener = new AdListener() {
			@Override
			public void onAdLoaded() {
				super.onAdLoaded();
				adView.setVisibility(View.VISIBLE);
			}
			@Override
			public void onAdFailedToLoad(int errorCode) {
				super.onAdFailedToLoad(errorCode);
				adView = null;
			}
		};
		adRequestAdview = getAdRequest(Defi.appname, Defi.appcat, Defi.adposition, null);
		adView.setAdListener(adlistener);
		adView.loadAd(adRequestAdview);
	}
	
	public void loadQuangCaoFull() {
		final PublisherAdRequest adRequestAdview12 = getAdRequest(Defi.appname,
				Defi.appcat, Defi.adposition, "100");

		if (adFull != null) {
			adFull = null;
		}
		adFull = new PublisherInterstitialAd(MainActivity.me);
		adFull.setAdUnitId("/35883025/bha_728a");
		adFull.loadAd(adRequestAdview12);
		adFull.getAdUnitId();
	}
	public void showQuangCaoFull(final View v) {
		if (allowShowQCFull && adFull.isLoaded()) {
			AdListener adlistener1 = new AdListener() {
				@Override
				public void onAdClosed() {
					super.onAdClosed();
					loadQuangCaoFull();
					final Handler handler = new Handler();
					handler.postDelayed(new Runnable() {
					  @Override
					  public void run() {
					    //Do something after ms
						  MainActivity.me.onClick(v);
					  }
					}, 700);
				}
			};
			adFull.setAdListener(adlistener1);
			adFull.show();
			allowShowQCFull = false;

			final Handler handler = new Handler();
			handler.postDelayed(new Runnable() {
				@Override
				public void run() {
					//Do something after ms
					allowShowQCFull = true;
				}
			}, 300000);
		}
	}
	
	//1
	public void showQuangCaoFull_Option(final AdapterView<?> parent, final View view,
			final int position, final long id) {
		if (allowShowQCFull && adFull.isLoaded()) {
			AdListener adlistener1 = new AdListener() {
				@Override
				public void onAdClosed() {
					super.onAdClosed();
					loadQuangCaoFull();
					final Handler handler = new Handler();
					handler.postDelayed(new Runnable() {
					  @Override
					  public void run() {
					    //Do something after ms
						  MainActivity.me.oItemClick(parent, view, position, id);
					  }
					}, 700);
				}
			};
			adFull.setAdListener(adlistener1);
			adFull.show();
			allowShowQCFull = false;

			final Handler handler = new Handler();
			handler.postDelayed(new Runnable() {
				@Override
				public void run() {
					//Do something after ms
					allowShowQCFull = true;
				}
			}, 300000);
		}
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		if (MainActivity.me == null) {
			return;
		}
		
		ConnectivityManager connectivityManager = (ConnectivityManager) 
				context.getSystemService(Context.CONNECTIVITY_SERVICE );
		NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
		boolean isConnected = activeNetInfo != null && activeNetInfo.isConnected();   
		if (isConnected) {
			if (adView == null) {
				loadQuangCaoBanner();
			}
			if (adFull==null || !adFull.isLoaded()) {
				loadQuangCaoFull();
			}
		}
	}
}
