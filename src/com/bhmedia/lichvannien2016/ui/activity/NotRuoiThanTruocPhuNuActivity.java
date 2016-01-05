package com.bhmedia.lichvannien2016.ui.activity;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bhmedia.lichvannien2016.R;
import com.bhmedia.lichvannien2016.ui.database.DataNew2DataBaseHelper;
import com.bhmedia.lichvannien2016.ui.model.NotRuoi;

/**
 * Created by Van on 4/3/2015.
 */
public class NotRuoiThanTruocPhuNuActivity extends FragmentActivity implements
		View.OnClickListener {
	TextView tv_title_detail_notruoi, tv_title;
	RadioButton rb_thantruocphunu_noruoi1_1, rb_thantruocphunu_noruoi1_2,
			rb_thantruocphunu_noruoi3_1, rb_thantruocphunu_noruoi3_2,
			rb_thantruocphunu_noruoi4, rb_thantruocphunu_noruoi6,
			rb_thantruocphunu_noruoi7, rb_thantruocphunu_noruoi8,
			rb_thantruocphunu_noruoi9, rb_thantruocphunu_noruoi11_1,
			rb_thantruocphunu_noruoi11_2, rb_thantruocphunu_noruoi12_1,
			rb_thantruocphunu_noruoi12_2, rb_thantruocphunu_noruoi13_1,
			rb_thantruocphunu_noruoi13_2, rb_thantruocphunu_noruoi24,
			rb_thantruocphunu_noruoi23_1, rb_thantruocphunu_noruoi23_2,
			rb_thantruocphunu_noruoi25_1, rb_thantruocphunu_noruoi25_2,
			rb_thantruocphunu_noruoi26_1, rb_thantruocphunu_noruoi26_2,
			rb_thantruocphunu_noruoi27_1, rb_thantruocphunu_noruoi27_2,
			rb_thantruocphunu_noruoi28_1, rb_thantruocphunu_noruoi28_2,
			rb_thantruocphunu_noruoi29_1, rb_thantruocphunu_noruoi29_2,
			rb_thantruocphunu_noruoi30_1, rb_thantruocphunu_noruoi30_2,
			rb_thantruocphunu_noruoi31_1, rb_thantruocphunu_noruoi31_2,
			rb_thantruocphunu_noruoi32_1, rb_thantruocphunu_noruoi32_2,
			rb_thantruocphunu_noruoi33_1, rb_thantruocphunu_noruoi33_2,
			rb_thantruocphunu_noruoi34_1, rb_thantruocphunu_noruoi34_2;
	ImageView iv_detail_notruoithantruocpn_back;
	ArrayList<NotRuoi> list;
	WebView wv_ketqua_vantrinhnam;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notruoi_thantruocphunu);
		tv_title = (TextView) findViewById(R.id.tv_thu_menu);
		tv_title.setText("Thân trước phụ nữ");
		tv_title_detail_notruoi = (TextView) findViewById(R.id.tv_title_detail_notruoi);
		wv_ketqua_vantrinhnam = (WebView) findViewById(R.id.wv_vanKhan);
		// Typeface font= Typeface.createFromAsset(this.getAssets(),
		// "fonts/UVNBaiSau_R_0.TTF");
		// tv_title_detail_notruoi.setTypeface(font);
		// tv_detail_notruoi.setTypeface(font);
		// tv_title.setTypeface(font);
		iv_detail_notruoithantruocpn_back = (ImageView) findViewById(R.id.rb_home);
		iv_detail_notruoithantruocpn_back.setOnClickListener(this);
		DataNew2DataBaseHelper boiNotRuoiDataBaseHelper = new DataNew2DataBaseHelper(
				this);
		list = boiNotRuoiDataBaseHelper.getAllLabelnrbodyf(this);
		boiNotRuoiDataBaseHelper.closeDataBase();
		rb_thantruocphunu_noruoi1_1 = (RadioButton) findViewById(R.id.rb_thantruocphunu_noruoi1_1);
		rb_thantruocphunu_noruoi1_2 = (RadioButton) findViewById(R.id.rb_thantruocphunu_noruoi1_2);
		rb_thantruocphunu_noruoi3_1 = (RadioButton) findViewById(R.id.rb_thantruocphunu_noruoi3_1);
		rb_thantruocphunu_noruoi3_2 = (RadioButton) findViewById(R.id.rb_thantruocphunu_noruoi3_2);
		rb_thantruocphunu_noruoi4 = (RadioButton) findViewById(R.id.rb_thantruocphunu_noruoi4);
		rb_thantruocphunu_noruoi6 = (RadioButton) findViewById(R.id.rb_thantruocphunu_noruoi6);
		rb_thantruocphunu_noruoi7 = (RadioButton) findViewById(R.id.rb_thantruocphunu_noruoi7);
		rb_thantruocphunu_noruoi8 = (RadioButton) findViewById(R.id.rb_thantruocphunu_noruoi8);
		rb_thantruocphunu_noruoi9 = (RadioButton) findViewById(R.id.rb_thantruocphunu_noruoi9);
		rb_thantruocphunu_noruoi11_1 = (RadioButton) findViewById(R.id.rb_thantruocphunu_noruoi11_1);
		rb_thantruocphunu_noruoi11_2 = (RadioButton) findViewById(R.id.rb_thantruocphunu_noruoi11_2);
		rb_thantruocphunu_noruoi12_1 = (RadioButton) findViewById(R.id.rb_thantruocphunu_noruoi12_1);
		rb_thantruocphunu_noruoi12_2 = (RadioButton) findViewById(R.id.rb_thantruocphunu_noruoi12_2);
		rb_thantruocphunu_noruoi13_1 = (RadioButton) findViewById(R.id.rb_thantruocphunu_noruoi13_1);
		rb_thantruocphunu_noruoi13_2 = (RadioButton) findViewById(R.id.rb_thantruocphunu_noruoi13_2);
		rb_thantruocphunu_noruoi24 = (RadioButton) findViewById(R.id.rb_thantruocphunu_noruoi24);
		rb_thantruocphunu_noruoi23_1 = (RadioButton) findViewById(R.id.rb_thantruocphunu_noruoi23_1);
		rb_thantruocphunu_noruoi23_2 = (RadioButton) findViewById(R.id.rb_thantruocphunu_noruoi23_2);
		rb_thantruocphunu_noruoi25_1 = (RadioButton) findViewById(R.id.rb_thantruocphunu_noruoi25_1);
		rb_thantruocphunu_noruoi25_2 = (RadioButton) findViewById(R.id.rb_thantruocphunu_noruoi25_2);
		rb_thantruocphunu_noruoi26_1 = (RadioButton) findViewById(R.id.rb_thantruocphunu_noruoi26_1);
		rb_thantruocphunu_noruoi26_2 = (RadioButton) findViewById(R.id.rb_thantruocphunu_noruoi26_2);
		rb_thantruocphunu_noruoi27_1 = (RadioButton) findViewById(R.id.rb_thantruocphunu_noruoi27_1);
		rb_thantruocphunu_noruoi27_2 = (RadioButton) findViewById(R.id.rb_thantruocphunu_noruoi27_2);
		rb_thantruocphunu_noruoi28_1 = (RadioButton) findViewById(R.id.rb_thantruocphunu_noruoi28_1);
		rb_thantruocphunu_noruoi28_2 = (RadioButton) findViewById(R.id.rb_thantruocphunu_noruoi28_2);
		rb_thantruocphunu_noruoi29_1 = (RadioButton) findViewById(R.id.rb_thantruocphunu_noruoi29_1);
		rb_thantruocphunu_noruoi29_2 = (RadioButton) findViewById(R.id.rb_thantruocphunu_noruoi29_2);
		rb_thantruocphunu_noruoi30_1 = (RadioButton) findViewById(R.id.rb_thantruocphunu_noruoi30_1);
		rb_thantruocphunu_noruoi30_2 = (RadioButton) findViewById(R.id.rb_thantruocphunu_noruoi30_2);
		rb_thantruocphunu_noruoi31_1 = (RadioButton) findViewById(R.id.rb_thantruocphunu_noruoi31_1);
		rb_thantruocphunu_noruoi31_2 = (RadioButton) findViewById(R.id.rb_thantruocphunu_noruoi31_2);
		rb_thantruocphunu_noruoi32_1 = (RadioButton) findViewById(R.id.rb_thantruocphunu_noruoi32_1);
		rb_thantruocphunu_noruoi32_2 = (RadioButton) findViewById(R.id.rb_thantruocphunu_noruoi32_2);
		rb_thantruocphunu_noruoi33_1 = (RadioButton) findViewById(R.id.rb_thantruocphunu_noruoi33_1);
		rb_thantruocphunu_noruoi33_2 = (RadioButton) findViewById(R.id.rb_thantruocphunu_noruoi33_2);
		rb_thantruocphunu_noruoi34_1 = (RadioButton) findViewById(R.id.rb_thantruocphunu_noruoi34_1);
		rb_thantruocphunu_noruoi34_2 = (RadioButton) findViewById(R.id.rb_thantruocphunu_noruoi34_2);
		rb_thantruocphunu_noruoi1_1.setOnClickListener(listener);
		rb_thantruocphunu_noruoi1_2.setOnClickListener(listener);
		rb_thantruocphunu_noruoi3_1.setOnClickListener(listener);
		rb_thantruocphunu_noruoi3_2.setOnClickListener(listener);
		rb_thantruocphunu_noruoi4.setOnClickListener(listener);
		rb_thantruocphunu_noruoi6.setOnClickListener(listener);
		rb_thantruocphunu_noruoi7.setOnClickListener(listener);
		rb_thantruocphunu_noruoi8.setOnClickListener(listener);
		rb_thantruocphunu_noruoi9.setOnClickListener(listener);
		rb_thantruocphunu_noruoi11_1.setOnClickListener(listener);
		rb_thantruocphunu_noruoi11_2.setOnClickListener(listener);
		rb_thantruocphunu_noruoi12_1.setOnClickListener(listener);
		rb_thantruocphunu_noruoi12_2.setOnClickListener(listener);
		rb_thantruocphunu_noruoi13_1.setOnClickListener(listener);
		rb_thantruocphunu_noruoi13_2.setOnClickListener(listener);
		rb_thantruocphunu_noruoi24.setOnClickListener(listener);
		rb_thantruocphunu_noruoi23_1.setOnClickListener(listener);
		rb_thantruocphunu_noruoi23_2.setOnClickListener(listener);
		rb_thantruocphunu_noruoi25_1.setOnClickListener(listener);
		rb_thantruocphunu_noruoi25_2.setOnClickListener(listener);
		rb_thantruocphunu_noruoi26_1.setOnClickListener(listener);
		rb_thantruocphunu_noruoi26_2.setOnClickListener(listener);
		rb_thantruocphunu_noruoi27_1.setOnClickListener(listener);
		rb_thantruocphunu_noruoi27_2.setOnClickListener(listener);
		rb_thantruocphunu_noruoi28_1.setOnClickListener(listener);
		rb_thantruocphunu_noruoi28_2.setOnClickListener(listener);
		rb_thantruocphunu_noruoi29_1.setOnClickListener(listener);
		rb_thantruocphunu_noruoi29_2.setOnClickListener(listener);
		rb_thantruocphunu_noruoi30_1.setOnClickListener(listener);
		rb_thantruocphunu_noruoi30_2.setOnClickListener(listener);
		rb_thantruocphunu_noruoi31_1.setOnClickListener(listener);
		rb_thantruocphunu_noruoi31_2.setOnClickListener(listener);
		rb_thantruocphunu_noruoi32_1.setOnClickListener(listener);
		rb_thantruocphunu_noruoi32_2.setOnClickListener(listener);
		rb_thantruocphunu_noruoi33_1.setOnClickListener(listener);
		rb_thantruocphunu_noruoi33_2.setOnClickListener(listener);
		rb_thantruocphunu_noruoi34_1.setOnClickListener(listener);
		rb_thantruocphunu_noruoi34_2.setOnClickListener(listener);

		clearBackground();
	}

	private void clearBackground() {
		rb_thantruocphunu_noruoi1_1.setBackgroundResource(R.drawable.nr);
		rb_thantruocphunu_noruoi1_2.setBackgroundResource(R.drawable.nr);
		rb_thantruocphunu_noruoi3_1.setBackgroundResource(R.drawable.nr);
		rb_thantruocphunu_noruoi3_2.setBackgroundResource(R.drawable.nr);
		rb_thantruocphunu_noruoi4.setBackgroundResource(R.drawable.nr);
		rb_thantruocphunu_noruoi6.setBackgroundResource(R.drawable.nr);
		rb_thantruocphunu_noruoi7.setBackgroundResource(R.drawable.nr);
		rb_thantruocphunu_noruoi8.setBackgroundResource(R.drawable.nr);
		rb_thantruocphunu_noruoi9.setBackgroundResource(R.drawable.nr);
		rb_thantruocphunu_noruoi11_1.setBackgroundResource(R.drawable.nr);
		rb_thantruocphunu_noruoi11_2.setBackgroundResource(R.drawable.nr);
		rb_thantruocphunu_noruoi12_1.setBackgroundResource(R.drawable.nr);
		rb_thantruocphunu_noruoi12_2.setBackgroundResource(R.drawable.nr);
		rb_thantruocphunu_noruoi13_1.setBackgroundResource(R.drawable.nr);
		rb_thantruocphunu_noruoi13_2.setBackgroundResource(R.drawable.nr);
		rb_thantruocphunu_noruoi24.setBackgroundResource(R.drawable.nr);
		rb_thantruocphunu_noruoi23_1.setBackgroundResource(R.drawable.nr);
		rb_thantruocphunu_noruoi23_2.setBackgroundResource(R.drawable.nr);
		rb_thantruocphunu_noruoi25_1.setBackgroundResource(R.drawable.nr);
		rb_thantruocphunu_noruoi25_2.setBackgroundResource(R.drawable.nr);
		rb_thantruocphunu_noruoi26_1.setBackgroundResource(R.drawable.nr);
		rb_thantruocphunu_noruoi26_2.setBackgroundResource(R.drawable.nr);
		rb_thantruocphunu_noruoi27_1.setBackgroundResource(R.drawable.nr);
		rb_thantruocphunu_noruoi27_2.setBackgroundResource(R.drawable.nr);
		rb_thantruocphunu_noruoi28_1.setBackgroundResource(R.drawable.nr);
		rb_thantruocphunu_noruoi28_2.setBackgroundResource(R.drawable.nr);
		rb_thantruocphunu_noruoi29_1.setBackgroundResource(R.drawable.nr);
		rb_thantruocphunu_noruoi29_2.setBackgroundResource(R.drawable.nr);
		rb_thantruocphunu_noruoi30_1.setBackgroundResource(R.drawable.nr);
		rb_thantruocphunu_noruoi30_2.setBackgroundResource(R.drawable.nr);
		rb_thantruocphunu_noruoi31_1.setBackgroundResource(R.drawable.nr);
		rb_thantruocphunu_noruoi31_2.setBackgroundResource(R.drawable.nr);
		rb_thantruocphunu_noruoi32_1.setBackgroundResource(R.drawable.nr);
		rb_thantruocphunu_noruoi32_2.setBackgroundResource(R.drawable.nr);
		rb_thantruocphunu_noruoi33_1.setBackgroundResource(R.drawable.nr);
		rb_thantruocphunu_noruoi33_2.setBackgroundResource(R.drawable.nr);
		rb_thantruocphunu_noruoi34_1.setBackgroundResource(R.drawable.nr);
		rb_thantruocphunu_noruoi34_2.setBackgroundResource(R.drawable.nr);

	}

	private View.OnClickListener listener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			clearBackground();
			((RadioButton) v).setBackgroundResource(R.drawable.nr_active);
			switch (v.getId()) {
			case R.id.rb_thantruocphunu_noruoi1_1:
				tv_title_detail_notruoi.setVisibility(View.GONE);
				findWebview(list.get(0).getContent());
				break;
			case R.id.rb_thantruocphunu_noruoi1_2:
				tv_title_detail_notruoi.setVisibility(View.GONE);
				findWebview(list.get(0).getContent());
				break;
			case R.id.rb_thantruocphunu_noruoi3_1:
				tv_title_detail_notruoi.setVisibility(View.GONE);
				findWebview(list.get(2).getContent());
				break;
			case R.id.rb_thantruocphunu_noruoi3_2:
				tv_title_detail_notruoi.setVisibility(View.GONE);
				findWebview(list.get(2).getContent());
				break;
			case R.id.rb_thantruocphunu_noruoi4:
				tv_title_detail_notruoi.setVisibility(View.GONE);
				findWebview(list.get(3).getContent());
				break;
			case R.id.rb_thantruocphunu_noruoi6:
				tv_title_detail_notruoi.setVisibility(View.GONE);
				findWebview(list.get(5).getContent());
				break;
			case R.id.rb_thantruocphunu_noruoi7:
				tv_title_detail_notruoi.setVisibility(View.GONE);
				findWebview(list.get(6).getContent());
				break;
			case R.id.rb_thantruocphunu_noruoi8:
				tv_title_detail_notruoi.setVisibility(View.GONE);
				findWebview(list.get(7).getContent());
				break;
			case R.id.rb_thantruocphunu_noruoi9:
				tv_title_detail_notruoi.setVisibility(View.GONE);
				findWebview(list.get(8).getContent());
				break;
			case R.id.rb_thantruocphunu_noruoi11_1:
				tv_title_detail_notruoi.setVisibility(View.GONE);
				findWebview(list.get(10).getContent());
				break;
			case R.id.rb_thantruocphunu_noruoi11_2:
				tv_title_detail_notruoi.setVisibility(View.GONE);
				findWebview(list.get(10).getContent());
				break;
			case R.id.rb_thantruocphunu_noruoi12_1:
				tv_title_detail_notruoi.setVisibility(View.GONE);
				findWebview(list.get(11).getContent());
				break;
			case R.id.rb_thantruocphunu_noruoi12_2:
				tv_title_detail_notruoi.setVisibility(View.GONE);
				findWebview(list.get(11).getContent());
				break;
			case R.id.rb_thantruocphunu_noruoi13_1:
				tv_title_detail_notruoi.setVisibility(View.GONE);
				findWebview(list.get(12).getContent());
				break;
			case R.id.rb_thantruocphunu_noruoi13_2:
				tv_title_detail_notruoi.setVisibility(View.GONE);
				findWebview(list.get(12).getContent());
				break;
			case R.id.rb_thantruocphunu_noruoi23_1:
				tv_title_detail_notruoi.setVisibility(View.GONE);
				findWebview(list.get(22).getContent());
				break;
			case R.id.rb_thantruocphunu_noruoi23_2:
				tv_title_detail_notruoi.setVisibility(View.GONE);
				findWebview(list.get(22).getContent());
				break;
			case R.id.rb_thantruocphunu_noruoi24:
				tv_title_detail_notruoi.setVisibility(View.GONE);
				findWebview(list.get(23).getContent());
				break;
			case R.id.rb_thantruocphunu_noruoi25_1:
				tv_title_detail_notruoi.setVisibility(View.GONE);
				findWebview(list.get(24).getContent());
				break;
			case R.id.rb_thantruocphunu_noruoi25_2:
				tv_title_detail_notruoi.setVisibility(View.GONE);
				findWebview(list.get(24).getContent());
				break;
			case R.id.rb_thantruocphunu_noruoi26_1:
				tv_title_detail_notruoi.setVisibility(View.GONE);
				findWebview(list.get(25).getContent());
				break;
			case R.id.rb_thantruocphunu_noruoi26_2:
				tv_title_detail_notruoi.setVisibility(View.GONE);
				findWebview(list.get(25).getContent());
				break;
			case R.id.rb_thantruocphunu_noruoi27_1:
				tv_title_detail_notruoi.setVisibility(View.GONE);
				findWebview(list.get(26).getContent());
				break;
			case R.id.rb_thantruocphunu_noruoi27_2:
				tv_title_detail_notruoi.setVisibility(View.GONE);
				findWebview(list.get(26).getContent());
				break;
			case R.id.rb_thantruocphunu_noruoi28_1:
				tv_title_detail_notruoi.setVisibility(View.GONE);
				findWebview(list.get(27).getContent());
				break;
			case R.id.rb_thantruocphunu_noruoi28_2:
				tv_title_detail_notruoi.setVisibility(View.GONE);
				findWebview(list.get(27).getContent());
				break;
			case R.id.rb_thantruocphunu_noruoi29_1:
				tv_title_detail_notruoi.setVisibility(View.GONE);
				findWebview(list.get(28).getContent());
				break;
			case R.id.rb_thantruocphunu_noruoi29_2:
				tv_title_detail_notruoi.setVisibility(View.GONE);
				findWebview(list.get(28).getContent());
				break;
			case R.id.rb_thantruocphunu_noruoi30_1:
				tv_title_detail_notruoi.setVisibility(View.GONE);
				findWebview(list.get(29).getContent());
				break;
			case R.id.rb_thantruocphunu_noruoi30_2:
				tv_title_detail_notruoi.setVisibility(View.GONE);
				findWebview(list.get(29).getContent());
				break;
			case R.id.rb_thantruocphunu_noruoi31_1:
				tv_title_detail_notruoi.setVisibility(View.GONE);
				findWebview(list.get(30).getContent());
				break;
			case R.id.rb_thantruocphunu_noruoi31_2:
				tv_title_detail_notruoi.setVisibility(View.GONE);
				findWebview(list.get(30).getContent());
				break;
			case R.id.rb_thantruocphunu_noruoi32_1:
				tv_title_detail_notruoi.setVisibility(View.GONE);
				findWebview(list.get(31).getContent());
				break;
			case R.id.rb_thantruocphunu_noruoi32_2:
				tv_title_detail_notruoi.setVisibility(View.GONE);
				findWebview(list.get(31).getContent());
				break;
				
			case R.id.rb_thantruocphunu_noruoi33_1:
				tv_title_detail_notruoi.setVisibility(View.GONE);
				findWebview(list.get(32).getContent());
				break;
			case R.id.rb_thantruocphunu_noruoi33_2:
				tv_title_detail_notruoi.setVisibility(View.GONE);
				findWebview(list.get(32).getContent());
				break;
			case R.id.rb_thantruocphunu_noruoi34_1:
				tv_title_detail_notruoi.setVisibility(View.GONE);
				findWebview(list.get(33).getContent());
				break;
			case R.id.rb_thantruocphunu_noruoi34_2:
				tv_title_detail_notruoi.setVisibility(View.GONE);
				findWebview(list.get(33).getContent());
				break;
			}
		}
	};

	public void findWebview(String str) {
		String youtContentStr = String
				.valueOf(Html
						.fromHtml("<![CDATA[<body style=\"text-align:justify;color:#222222; \">"
								+ str + "</body>]]>"));
		wv_ketqua_vantrinhnam.getSettings().setJavaScriptEnabled(true);
		wv_ketqua_vantrinhnam.getResources().getDimensionPixelSize(
				R.dimen.text_size_large);
		wv_ketqua_vantrinhnam.loadDataWithBaseURL("file:///android_asset/",
				youtContentStr, "text/html", "utf-8", null);
		// wv_ketqua_vantrinhnam.setBackgroundColor(Color.BLACK);

		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
			wv_ketqua_vantrinhnam.setAlpha(0.7f);
		}
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rb_home:
			onBackPressed();
			break;
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// BH.getInstance(this).onDestroy();
	}
}
