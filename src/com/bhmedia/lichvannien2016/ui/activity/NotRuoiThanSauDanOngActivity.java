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
public class NotRuoiThanSauDanOngActivity extends FragmentActivity implements
		View.OnClickListener {
	TextView tv_title_detail_notruoithansaudo, tv_title;
	RadioButton radioButton35, radioButton36, radioButton37_1, radioButton37_2,
			radioButton38_1, radioButton38_2, radioButton39_1, radioButton39_2,
			radioButton40, radioButton41_1, radioButton41_2, radioButton42,
			radioButton43_1, radioButton43_2, radioButton44_1, radioButton44_2,
			radioButton45_1, radioButton45_2, radioButton46_1, radioButton46_2,
			radioButton47_1, radioButton47_2, radioButton48_1, radioButton48_2,
			radioButton49_1, radioButton49_2, radioButton50_1, radioButton50_2,
			radioButton51_1, radioButton51_2, radioButton52_1, radioButton52_2,
			radioButton53_1, radioButton53_2;
	ImageView iv_detail_notruoithansaudo_back;
	ArrayList<NotRuoi> list;
	WebView wv_ketqua_vantrinhnam;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notruoi_thansaudanong);
		tv_title = (TextView) findViewById(R.id.tv_thu_menu);
		tv_title.setText("Thân sau đàn ông");
		tv_title_detail_notruoithansaudo = (TextView) findViewById(R.id.tv_title_detail_notruoithansaudo);
		wv_ketqua_vantrinhnam = (WebView) findViewById(R.id.wv_vanKhan);
		// Typeface font= Typeface.createFromAsset(this.getAssets(),
		// "fonts/UVNBaiSau_R_0.TTF");
		// tv_title_detail_notruoithansaudo.setTypeface(font);
		// tv_detail_notruoithansaudo.setTypeface(font);
		// tv_title.setTypeface(font);
		iv_detail_notruoithansaudo_back = (ImageView) findViewById(R.id.rb_home);
		iv_detail_notruoithansaudo_back.setOnClickListener(this);
		DataNew2DataBaseHelper boiNotRuoiDataBaseHelper = new DataNew2DataBaseHelper(
				this);
		list = boiNotRuoiDataBaseHelper.getAllLabelnrbodyb(this);
		boiNotRuoiDataBaseHelper.closeDataBase();
		boiNotRuoiDataBaseHelper.close();
		radioButton35 = (RadioButton) findViewById(R.id.radioButton35);
		radioButton36 = (RadioButton) findViewById(R.id.radioButton36);
		radioButton37_1 = (RadioButton) findViewById(R.id.radioButton37_1);
		radioButton37_2 = (RadioButton) findViewById(R.id.radioButton37_2);
		radioButton38_1 = (RadioButton) findViewById(R.id.radioButton38_1);
		radioButton38_2 = (RadioButton) findViewById(R.id.radioButton38_2);
		radioButton39_1 = (RadioButton) findViewById(R.id.radioButton39_1);
		radioButton39_2 = (RadioButton) findViewById(R.id.radioButton39_2);
		radioButton40 = (RadioButton) findViewById(R.id.radioButton40);
		radioButton41_1 = (RadioButton) findViewById(R.id.radioButton41_1);
		radioButton41_2 = (RadioButton) findViewById(R.id.radioButton41_2);
		radioButton42 = (RadioButton) findViewById(R.id.radioButton42);
		radioButton43_1 = (RadioButton) findViewById(R.id.radioButton43_1);
		radioButton43_2 = (RadioButton) findViewById(R.id.radioButton43_2);
		radioButton44_1 = (RadioButton) findViewById(R.id.radioButton44_1);
		radioButton44_2 = (RadioButton) findViewById(R.id.radioButton44_2);
		radioButton45_1 = (RadioButton) findViewById(R.id.radioButton45_1);
		radioButton45_2 = (RadioButton) findViewById(R.id.radioButton45_2);
		radioButton46_1 = (RadioButton) findViewById(R.id.radioButton46_1);
		radioButton46_2 = (RadioButton) findViewById(R.id.radioButton46_2);
		radioButton47_1 = (RadioButton) findViewById(R.id.radioButton47_1);
		radioButton47_2 = (RadioButton) findViewById(R.id.radioButton47_2);
		radioButton48_1 = (RadioButton) findViewById(R.id.radioButton48_1);
		radioButton48_2 = (RadioButton) findViewById(R.id.radioButton48_2);
		radioButton49_1 = (RadioButton) findViewById(R.id.radioButton49_1);
		radioButton49_2 = (RadioButton) findViewById(R.id.radioButton49_2);
		radioButton50_1 = (RadioButton) findViewById(R.id.radioButton50_1);
		radioButton50_2 = (RadioButton) findViewById(R.id.radioButton50_2);
		radioButton51_1 = (RadioButton) findViewById(R.id.radioButton51_1);
		radioButton51_2 = (RadioButton) findViewById(R.id.radioButton51_2);
		radioButton52_1 = (RadioButton) findViewById(R.id.radioButton52_1);
		radioButton52_2 = (RadioButton) findViewById(R.id.radioButton52_2);
		radioButton53_1 = (RadioButton) findViewById(R.id.radioButton53_1);
		radioButton53_2 = (RadioButton) findViewById(R.id.radioButton53_2);
		radioButton35.setOnClickListener(listener);
		radioButton36.setOnClickListener(listener);
		radioButton37_1.setOnClickListener(listener);
		radioButton37_2.setOnClickListener(listener);
		radioButton38_1.setOnClickListener(listener);
		radioButton38_2.setOnClickListener(listener);
		radioButton39_1.setOnClickListener(listener);
		radioButton39_2.setOnClickListener(listener);
		radioButton40.setOnClickListener(listener);
		radioButton41_1.setOnClickListener(listener);
		radioButton41_2.setOnClickListener(listener);
		radioButton42.setOnClickListener(listener);
		radioButton43_1.setOnClickListener(listener);
		radioButton43_2.setOnClickListener(listener);
		radioButton44_1.setOnClickListener(listener);
		radioButton44_2.setOnClickListener(listener);
		radioButton45_1.setOnClickListener(listener);
		radioButton45_2.setOnClickListener(listener);
		radioButton46_1.setOnClickListener(listener);
		radioButton46_2.setOnClickListener(listener);
		radioButton47_1.setOnClickListener(listener);
		radioButton47_2.setOnClickListener(listener);
		radioButton48_1.setOnClickListener(listener);
		radioButton48_2.setOnClickListener(listener);
		radioButton49_1.setOnClickListener(listener);
		radioButton49_2.setOnClickListener(listener);
		radioButton50_1.setOnClickListener(listener);
		radioButton50_2.setOnClickListener(listener);
		radioButton51_1.setOnClickListener(listener);
		radioButton51_2.setOnClickListener(listener);
		radioButton52_1.setOnClickListener(listener);
		radioButton52_2.setOnClickListener(listener);
		radioButton53_1.setOnClickListener(listener);
		radioButton53_2.setOnClickListener(listener);
		clearBackGround();
	}

	private void clearBackGround() {
		radioButton35.setBackgroundResource(R.drawable.nr);
		radioButton36.setBackgroundResource(R.drawable.nr);
		radioButton37_1.setBackgroundResource(R.drawable.nr);
		radioButton37_2.setBackgroundResource(R.drawable.nr);
		radioButton38_1.setBackgroundResource(R.drawable.nr);
		radioButton38_2.setBackgroundResource(R.drawable.nr);
		radioButton39_1.setBackgroundResource(R.drawable.nr);
		radioButton39_2.setBackgroundResource(R.drawable.nr);
		radioButton40.setBackgroundResource(R.drawable.nr);
		radioButton41_1.setBackgroundResource(R.drawable.nr);
		radioButton41_2.setBackgroundResource(R.drawable.nr);
		radioButton42.setBackgroundResource(R.drawable.nr);
		radioButton43_1.setBackgroundResource(R.drawable.nr);
		radioButton43_2.setBackgroundResource(R.drawable.nr);
		radioButton44_1.setBackgroundResource(R.drawable.nr);
		radioButton44_2.setBackgroundResource(R.drawable.nr);
		radioButton45_1.setBackgroundResource(R.drawable.nr);
		radioButton45_2.setBackgroundResource(R.drawable.nr);
		radioButton46_1.setBackgroundResource(R.drawable.nr);
		radioButton46_2.setBackgroundResource(R.drawable.nr);
		radioButton47_1.setBackgroundResource(R.drawable.nr);
		radioButton47_2.setBackgroundResource(R.drawable.nr);
		radioButton48_1.setBackgroundResource(R.drawable.nr);
		radioButton48_2.setBackgroundResource(R.drawable.nr);
		radioButton49_1.setBackgroundResource(R.drawable.nr);
		radioButton49_2.setBackgroundResource(R.drawable.nr);
		radioButton50_1.setBackgroundResource(R.drawable.nr);
		radioButton50_2.setBackgroundResource(R.drawable.nr);
		radioButton51_1.setBackgroundResource(R.drawable.nr);
		radioButton51_2.setBackgroundResource(R.drawable.nr);
		radioButton52_1.setBackgroundResource(R.drawable.nr);
		radioButton52_2.setBackgroundResource(R.drawable.nr);
		radioButton53_1.setBackgroundResource(R.drawable.nr);
		radioButton53_2.setBackgroundResource(R.drawable.nr);
	}

	private View.OnClickListener listener = new View.OnClickListener() {	
		@Override
		public void onClick(View v) {
			clearBackGround();
			((RadioButton) v).setBackgroundResource(R.drawable.nr_active);
			switch (v.getId()) {
			case R.id.radioButton35:
				tv_title_detail_notruoithansaudo.setVisibility(View.GONE);
				findWebview(list.get(0).getContent());
				break;
			case R.id.radioButton36:
				tv_title_detail_notruoithansaudo.setVisibility(View.GONE);
				findWebview(list.get(1).getContent());
				break;
			case R.id.radioButton37_1:
				tv_title_detail_notruoithansaudo.setVisibility(View.GONE);
				findWebview(list.get(2).getContent());
				break;
			case R.id.radioButton37_2:
				tv_title_detail_notruoithansaudo.setVisibility(View.GONE);
				findWebview(list.get(2).getContent());
				break;
			case R.id.radioButton38_1:
				tv_title_detail_notruoithansaudo.setVisibility(View.GONE);
				findWebview(list.get(3).getContent());
				break;
			case R.id.radioButton38_2:
				tv_title_detail_notruoithansaudo.setVisibility(View.GONE);
				findWebview(list.get(3).getContent());
				break;
			case R.id.radioButton39_1:
				tv_title_detail_notruoithansaudo.setVisibility(View.GONE);
				findWebview(list.get(4).getContent());
				break;
			case R.id.radioButton39_2:
				tv_title_detail_notruoithansaudo.setVisibility(View.GONE);
				findWebview(list.get(4).getContent());
				break;
			case R.id.radioButton40:
				tv_title_detail_notruoithansaudo.setVisibility(View.GONE);
				findWebview(list.get(5).getContent());
				break;
			case R.id.radioButton41_1:
				tv_title_detail_notruoithansaudo.setVisibility(View.GONE);
				findWebview(list.get(6).getContent());
				break;
			case R.id.radioButton41_2:
				tv_title_detail_notruoithansaudo.setVisibility(View.GONE);
				findWebview(list.get(6).getContent());
				break;
			case R.id.radioButton42:
				tv_title_detail_notruoithansaudo.setVisibility(View.GONE);
				findWebview(list.get(7).getContent());
				break;
			case R.id.radioButton43_1:
				tv_title_detail_notruoithansaudo.setVisibility(View.GONE);
				findWebview(list.get(8).getContent());
				break;
			case R.id.radioButton43_2:
				tv_title_detail_notruoithansaudo.setVisibility(View.GONE);
				findWebview(list.get(8).getContent());
				break;
			case R.id.radioButton44_1:
				tv_title_detail_notruoithansaudo.setVisibility(View.GONE);
				findWebview(list.get(9).getContent());
				break;
			case R.id.radioButton44_2:
				tv_title_detail_notruoithansaudo.setVisibility(View.GONE);
				findWebview(list.get(9).getContent());
				break;
			case R.id.radioButton45_1:
				tv_title_detail_notruoithansaudo.setVisibility(View.GONE);
				findWebview(list.get(10).getContent());
				break;
			case R.id.radioButton45_2:
				tv_title_detail_notruoithansaudo.setVisibility(View.GONE);
				findWebview(list.get(10).getContent());
				break;
			case R.id.radioButton46_1:
				tv_title_detail_notruoithansaudo.setVisibility(View.GONE);
				findWebview(list.get(11).getContent());
				break;
			case R.id.radioButton46_2:
				tv_title_detail_notruoithansaudo.setVisibility(View.GONE);
				findWebview(list.get(11).getContent());
				break;
			case R.id.radioButton47_1:
				tv_title_detail_notruoithansaudo.setVisibility(View.GONE);
				findWebview(list.get(12).getContent());
				break;
			case R.id.radioButton47_2:
				tv_title_detail_notruoithansaudo.setVisibility(View.GONE);
				findWebview(list.get(12).getContent());
				break;
			case R.id.radioButton48_1:
				tv_title_detail_notruoithansaudo.setVisibility(View.GONE);
				findWebview(list.get(13).getContent());
				break;
			case R.id.radioButton48_2:
				tv_title_detail_notruoithansaudo.setVisibility(View.GONE);
				findWebview(list.get(13).getContent());
				break;
			case R.id.radioButton49_1:
				tv_title_detail_notruoithansaudo.setVisibility(View.GONE);
				findWebview(list.get(14).getContent());
				break;
			case R.id.radioButton49_2:
				tv_title_detail_notruoithansaudo.setVisibility(View.GONE);
				findWebview(list.get(14).getContent());
				break;
			case R.id.radioButton50_1:
				tv_title_detail_notruoithansaudo.setVisibility(View.GONE);
				findWebview(list.get(15).getContent());
				break;
			case R.id.radioButton50_2:
				tv_title_detail_notruoithansaudo.setVisibility(View.GONE);
				findWebview(list.get(15).getContent());
				break;
			case R.id.radioButton51_1:
				tv_title_detail_notruoithansaudo.setVisibility(View.GONE);
				findWebview(list.get(16).getContent());
				break;
			case R.id.radioButton51_2:
				tv_title_detail_notruoithansaudo.setVisibility(View.GONE);
				findWebview(list.get(16).getContent());
				break;
			case R.id.radioButton52_1:
				tv_title_detail_notruoithansaudo.setVisibility(View.GONE);
				findWebview(list.get(17).getContent());
				break;
			case R.id.radioButton52_2:
				tv_title_detail_notruoithansaudo.setVisibility(View.GONE);
				findWebview(list.get(17).getContent());
				break;
			case R.id.radioButton53_1:
				tv_title_detail_notruoithansaudo.setVisibility(View.GONE);
				findWebview(list.get(18).getContent());
				break;
			case R.id.radioButton53_2:
				tv_title_detail_notruoithansaudo.setVisibility(View.GONE);
				findWebview(list.get(18).getContent());
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
