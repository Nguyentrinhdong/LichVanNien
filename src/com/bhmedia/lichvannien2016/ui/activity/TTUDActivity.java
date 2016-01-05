package com.bhmedia.lichvannien2016.ui.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.bhmedia.lichvannien2016.R;

public class TTUDActivity extends Activity {
	private String strIntroduction;
	private String myHTML;
	private WebView wvDetail;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_info);
		wvDetail = (WebView) findViewById(R.id.wvDetail);
		wvDetail.getSettings().setJavaScriptEnabled(true);
		wvDetail.getResources().getDimensionPixelSize(
				R.dimen.text_size_large);
		wvDetail.setBackgroundColor(Color.TRANSPARENT);
		initData();
	}

	@SuppressLint("NewApi")
	private void initData() {

		// For webview
		strIntroduction = "<style>p{color:#FFFFFF}</style><p>What will 2016 bring? Will you get rich? Should you continue with the same job or look for a new one? Will you find your true love? Get answers to all these questions with our useful Lunar Calendar app. Clearly illustrated in English and easy to use, the app provides information on auspicious dates and hours, lucky stars, moon phases, solar terms to help you make important decisions, such as buying a house, finding a new job or getting married.<br/><br/>Features:<br/>- Lunar Calendar with auspicious days and hours, lucky directions, 28 Mansions, Solar Terms and more.<br/>- Convert Solar Calendar to Lunar Calendar<br/>- Add events and reminders<br/>- Moon Phases<br/>- Western Astrology: Free 2016 horoscopes for all 12 signs (Aries, Taurus, Gemini, Cancer, Leo, Virgo, Libra, Scorpio, Sagittarius, Capricorn, Aquarius, and Pisces)<br/>- Chinese Astrology: Free 2016 horoscopes for all 12 signs (Rat, Ox, Tiger, Rabbit, Dragon, Snake, Horse, Sheep, Monkey, Rooster, Dog, and Pig)<br/>- I Ching divination<br/>- Dream Interpretation<br/>- Biorhythm charts<br/><br/>Our Chinese Calendar app is handy and useful guide for those who want to make the most out of the Year of the Sheep.<br/>INSTALL the app NOW to be prepared for your opportunities or challenges in 2016.</p>";

		myHTML = "<html><body style=\"text-align:justify\">" + strIntroduction
				+ "</body></html>";

		wvDetail.loadDataWithBaseURL(null, myHTML,
				"text/html", "utf-8", null);

		
	}
}
