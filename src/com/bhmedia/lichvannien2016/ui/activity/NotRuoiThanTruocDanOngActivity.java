package com.bhmedia.lichvannien2016.ui.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
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
public class NotRuoiThanTruocDanOngActivity extends Activity implements
		View.OnClickListener {
	TextView tv_title_detail_notruoithantruocdo, tv_title;
	RadioButton radioButton1_1, radioButton1_2, radioButton2_1, radioButton2_2,
			radioButton3_1, radioButton3_2, radioButton4, radioButton5_1,
			radioButton5_2, radioButton6, radioButton7, radioButton9,
			radioButton10, radioButton11_1, radioButton11_2, radioButton12_1,
			radioButton12_2, radioButton13_1, radioButton13_2, radioButton14_1,
			radioButton14_2, radioButton15_1, radioButton15_2, radioButton16_1,
			radioButton16_2, radioButton17_1, radioButton17_2, radioButton18_1,
			radioButton18_2, radioButton19_1, radioButton19_2, radioButton19_3,
			radioButton19_4, radioButton20_1, radioButton20_2, radioButton21_1,
			radioButton21_2;
	ImageView iv_detail_notruoithantruocdo_back;
	ArrayList<NotRuoi> list;
	WebView wv_ketqua_vantrinhnam;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notruoi_thantruocdanong);
		tv_title = (TextView) findViewById(R.id.tv_thu_menu);
		tv_title.setText("Thân trước đàn ông");
		tv_title_detail_notruoithantruocdo = (TextView) findViewById(R.id.tv_title_detail_notruoithantruocdo);
		wv_ketqua_vantrinhnam = (WebView) findViewById(R.id.wv_vanKhan);
		iv_detail_notruoithantruocdo_back = (ImageView) findViewById(R.id.rb_home);
		iv_detail_notruoithantruocdo_back.setOnClickListener(this);
		// Typeface font= Typeface.createFromAsset(this.getAssets(),
		// "fonts/UVNBaiSau_R_0.TTF");
		// tv_title_detail_notruoithantruocdo.setTypeface(font);
		// tv_detail_notruoithantruocdo.setTypeface(font);
		// tv_title.setTypeface(font);
		DataNew2DataBaseHelper boiNotRuoiDataBaseHelper = new DataNew2DataBaseHelper(
				this);
		list = boiNotRuoiDataBaseHelper.getAllLabelnrbodyf(this);
		boiNotRuoiDataBaseHelper.closeDataBase();
		radioButton1_1 = (RadioButton) findViewById(R.id.radioButton1_1);
		radioButton1_2 = (RadioButton) findViewById(R.id.radioButton1_2);
		radioButton2_1 = (RadioButton) findViewById(R.id.radioButton2_1);
		radioButton2_2 = (RadioButton) findViewById(R.id.radioButton2_2);
		radioButton3_1 = (RadioButton) findViewById(R.id.radioButton3_1);
		radioButton3_2 = (RadioButton) findViewById(R.id.radioButton3_2);
		radioButton4 = (RadioButton) findViewById(R.id.radioButton4);
		radioButton5_1 = (RadioButton) findViewById(R.id.radioButton5_1);
		radioButton5_2 = (RadioButton) findViewById(R.id.radioButton5_2);
		radioButton6 = (RadioButton) findViewById(R.id.radioButton6);
		radioButton7 = (RadioButton) findViewById(R.id.radioButton7);
		radioButton9 = (RadioButton) findViewById(R.id.radioButton9);
		radioButton10 = (RadioButton) findViewById(R.id.radioButton10);
		radioButton11_1 = (RadioButton) findViewById(R.id.radioButton11_1);
		radioButton11_2 = (RadioButton) findViewById(R.id.radioButton11_2);
		radioButton12_1 = (RadioButton) findViewById(R.id.radioButton12_1);
		radioButton12_2 = (RadioButton) findViewById(R.id.radioButton12_2);
		radioButton13_1 = (RadioButton) findViewById(R.id.radioButton13_1);
		radioButton13_2 = (RadioButton) findViewById(R.id.radioButton13_2);
		radioButton14_1 = (RadioButton) findViewById(R.id.radioButton14_1);
		radioButton14_2 = (RadioButton) findViewById(R.id.radioButton14_2);
		radioButton15_1 = (RadioButton) findViewById(R.id.radioButton15_1);
		radioButton15_2 = (RadioButton) findViewById(R.id.radioButton15_2);
		radioButton16_1 = (RadioButton) findViewById(R.id.radioButton16_1);
		radioButton16_2 = (RadioButton) findViewById(R.id.radioButton16_2);
		radioButton17_1 = (RadioButton) findViewById(R.id.radioButton17_1);
		radioButton17_2 = (RadioButton) findViewById(R.id.radioButton17_2);
		radioButton18_1 = (RadioButton) findViewById(R.id.radioButton18_1);
		radioButton18_2 = (RadioButton) findViewById(R.id.radioButton18_2);
		radioButton19_1 = (RadioButton) findViewById(R.id.radioButton19_1);
		radioButton19_2 = (RadioButton) findViewById(R.id.radioButton19_2);
		radioButton19_3 = (RadioButton) findViewById(R.id.radioButton19_3);
		radioButton19_4 = (RadioButton) findViewById(R.id.radioButton19_4);
		radioButton20_1 = (RadioButton) findViewById(R.id.radioButton20_1);
		radioButton20_2 = (RadioButton) findViewById(R.id.radioButton20_2);
		radioButton21_1 = (RadioButton) findViewById(R.id.radioButton21_1);
		radioButton21_2 = (RadioButton) findViewById(R.id.radioButton21_2);
		radioButton1_1.setOnClickListener(listener);
		radioButton1_2.setOnClickListener(listener);
		radioButton2_1.setOnClickListener(listener);
		radioButton2_2.setOnClickListener(listener);
		radioButton3_1.setOnClickListener(listener);
		radioButton3_2.setOnClickListener(listener);
		radioButton4.setOnClickListener(listener);
		radioButton5_1.setOnClickListener(listener);
		radioButton5_2.setOnClickListener(listener);
		radioButton6.setOnClickListener(listener);
		radioButton7.setOnClickListener(listener);
		radioButton9.setOnClickListener(listener);
		radioButton10.setOnClickListener(listener);
		radioButton11_1.setOnClickListener(listener);
		radioButton11_2.setOnClickListener(listener);
		radioButton12_1.setOnClickListener(listener);
		radioButton12_2.setOnClickListener(listener);
		radioButton13_1.setOnClickListener(listener);
		radioButton13_2.setOnClickListener(listener);
		radioButton14_1.setOnClickListener(listener);
		radioButton14_2.setOnClickListener(listener);
		radioButton15_1.setOnClickListener(listener);
		radioButton15_2.setOnClickListener(listener);
		radioButton16_1.setOnClickListener(listener);
		radioButton16_2.setOnClickListener(listener);
		radioButton17_1.setOnClickListener(listener);
		radioButton17_2.setOnClickListener(listener);
		radioButton18_1.setOnClickListener(listener);
		radioButton18_2.setOnClickListener(listener);
		radioButton19_1.setOnClickListener(listener);
		radioButton19_2.setOnClickListener(listener);
		radioButton19_3.setOnClickListener(listener);
		radioButton19_4.setOnClickListener(listener);
		radioButton20_1.setOnClickListener(listener);
		radioButton20_2.setOnClickListener(listener);
		radioButton21_1.setOnClickListener(listener);
		radioButton21_2.setOnClickListener(listener);

		clearBackGround();
	}

	private void clearBackGround() {
		radioButton1_1.setBackgroundResource(R.drawable.nr);
		radioButton1_2.setBackgroundResource(R.drawable.nr);
		radioButton2_1.setBackgroundResource(R.drawable.nr);
		radioButton2_2.setBackgroundResource(R.drawable.nr);
		radioButton3_1.setBackgroundResource(R.drawable.nr);
		radioButton3_2.setBackgroundResource(R.drawable.nr);
		radioButton4.setBackgroundResource(R.drawable.nr);
		radioButton5_1.setBackgroundResource(R.drawable.nr);
		radioButton5_2.setBackgroundResource(R.drawable.nr);
		radioButton6.setBackgroundResource(R.drawable.nr);
		radioButton7.setBackgroundResource(R.drawable.nr);
		radioButton9.setBackgroundResource(R.drawable.nr);
		radioButton10.setBackgroundResource(R.drawable.nr);
		radioButton11_1.setBackgroundResource(R.drawable.nr);
		radioButton11_2.setBackgroundResource(R.drawable.nr);
		radioButton12_1.setBackgroundResource(R.drawable.nr);
		radioButton12_2.setBackgroundResource(R.drawable.nr);
		radioButton13_1.setBackgroundResource(R.drawable.nr);
		radioButton13_2.setBackgroundResource(R.drawable.nr);
		radioButton14_1.setBackgroundResource(R.drawable.nr);
		radioButton14_2.setBackgroundResource(R.drawable.nr);
		radioButton15_1.setBackgroundResource(R.drawable.nr);
		radioButton15_2.setBackgroundResource(R.drawable.nr);
		radioButton16_1.setBackgroundResource(R.drawable.nr);
		radioButton16_2.setBackgroundResource(R.drawable.nr);
		radioButton17_1.setBackgroundResource(R.drawable.nr);
		radioButton17_2.setBackgroundResource(R.drawable.nr);
		radioButton18_1.setBackgroundResource(R.drawable.nr);
		radioButton18_2.setBackgroundResource(R.drawable.nr);
		radioButton19_1.setBackgroundResource(R.drawable.nr);
		radioButton19_2.setBackgroundResource(R.drawable.nr);
		radioButton19_3.setBackgroundResource(R.drawable.nr);
		radioButton19_4.setBackgroundResource(R.drawable.nr);
		radioButton20_1.setBackgroundResource(R.drawable.nr);
		radioButton20_2.setBackgroundResource(R.drawable.nr);
		radioButton21_1.setBackgroundResource(R.drawable.nr);
		radioButton21_2.setBackgroundResource(R.drawable.nr);
	}

	private View.OnClickListener listener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			clearBackGround();
			((RadioButton) v).setBackgroundResource(R.drawable.nr_active);
			switch (v.getId()) {
			case R.id.radioButton1_1:
				tv_title_detail_notruoithantruocdo.setVisibility(View.GONE);
				findWebview(list.get(0).getContent());
				break;
			case R.id.radioButton1_2:
				tv_title_detail_notruoithantruocdo.setVisibility(View.GONE);
				findWebview(list.get(0).getContent());
				break;
			case R.id.radioButton2_1:
				tv_title_detail_notruoithantruocdo.setVisibility(View.GONE);
				findWebview(list.get(1).getContent());
				break;
			case R.id.radioButton2_2:
				tv_title_detail_notruoithantruocdo.setVisibility(View.GONE);
				findWebview(list.get(1).getContent());
				break;
			case R.id.radioButton3_1:
				tv_title_detail_notruoithantruocdo.setVisibility(View.GONE);
				findWebview(list.get(2).getContent());
				break;
			case R.id.radioButton3_2:
				tv_title_detail_notruoithantruocdo.setVisibility(View.GONE);
				findWebview(list.get(2).getContent());
				break;
			case R.id.radioButton4:
				tv_title_detail_notruoithantruocdo.setVisibility(View.GONE);
				findWebview(list.get(3).getContent());
				break;
			case R.id.radioButton5_1:
				tv_title_detail_notruoithantruocdo.setVisibility(View.GONE);
				findWebview(list.get(4).getContent());
				break;
			case R.id.radioButton5_2:
				tv_title_detail_notruoithantruocdo.setVisibility(View.GONE);
				findWebview(list.get(4).getContent());
				break;
			case R.id.radioButton6:
				tv_title_detail_notruoithantruocdo.setVisibility(View.GONE);
				findWebview(list.get(5).getContent());
				break;
			case R.id.radioButton7:
				tv_title_detail_notruoithantruocdo.setVisibility(View.GONE);
				findWebview(list.get(6).getContent());
				break;
			case R.id.radioButton9:
				tv_title_detail_notruoithantruocdo.setVisibility(View.GONE);
				findWebview(list.get(8).getContent());
				break;
			case R.id.radioButton10:
				tv_title_detail_notruoithantruocdo.setVisibility(View.GONE);
				findWebview(list.get(9).getContent());
				break;
			case R.id.radioButton11_1:
				tv_title_detail_notruoithantruocdo.setVisibility(View.GONE);
				findWebview(list.get(10).getContent());
				break;
			case R.id.radioButton11_2:
				tv_title_detail_notruoithantruocdo.setVisibility(View.GONE);
				findWebview(list.get(10).getContent());
				break;
			case R.id.radioButton12_1:
				tv_title_detail_notruoithantruocdo.setVisibility(View.GONE);
				findWebview(list.get(11).getContent());
				break;
			case R.id.radioButton12_2:
				tv_title_detail_notruoithantruocdo.setVisibility(View.GONE);
				findWebview(list.get(11).getContent());
				break;
			case R.id.radioButton13_1:
				tv_title_detail_notruoithantruocdo.setVisibility(View.GONE);
				findWebview(list.get(12).getContent());
				break;
			case R.id.radioButton13_2:
				tv_title_detail_notruoithantruocdo.setVisibility(View.GONE);
				findWebview(list.get(12).getContent());
				break;
			case R.id.radioButton14_1:
				tv_title_detail_notruoithantruocdo.setVisibility(View.GONE);
				findWebview(list.get(13).getContent());
				break;
			case R.id.radioButton14_2:
				tv_title_detail_notruoithantruocdo.setVisibility(View.GONE);
				findWebview(list.get(13).getContent());
				break;
			case R.id.radioButton15_1:
				tv_title_detail_notruoithantruocdo.setVisibility(View.GONE);
				findWebview(list.get(14).getContent());
				break;
			case R.id.radioButton15_2:
				tv_title_detail_notruoithantruocdo.setVisibility(View.GONE);
				findWebview(list.get(14).getContent());
				break;
			case R.id.radioButton16_1:
				tv_title_detail_notruoithantruocdo.setVisibility(View.GONE);
				findWebview(list.get(15).getContent());
				break;
			case R.id.radioButton16_2:
				tv_title_detail_notruoithantruocdo.setVisibility(View.GONE);
				findWebview(list.get(15).getContent());
				break;
			case R.id.radioButton17_1:
				tv_title_detail_notruoithantruocdo.setVisibility(View.GONE);
				findWebview(list.get(16).getContent());
				break;
			case R.id.radioButton17_2:
				tv_title_detail_notruoithantruocdo.setVisibility(View.GONE);
				findWebview(list.get(16).getContent());
				break;
			case R.id.radioButton18_1:
				tv_title_detail_notruoithantruocdo.setVisibility(View.GONE);
				findWebview(list.get(17).getContent());
				break;
			case R.id.radioButton18_2:
				tv_title_detail_notruoithantruocdo.setVisibility(View.GONE);
				findWebview(list.get(17).getContent());
				break;
			case R.id.radioButton19_1:
				tv_title_detail_notruoithantruocdo.setVisibility(View.GONE);
				findWebview(list.get(18).getContent());
				break;
			case R.id.radioButton19_2:
				tv_title_detail_notruoithantruocdo.setVisibility(View.GONE);
				findWebview(list.get(18).getContent());
				break;
			case R.id.radioButton19_3:
				tv_title_detail_notruoithantruocdo.setVisibility(View.GONE);
				findWebview(list.get(18).getContent());
				break;
			case R.id.radioButton19_4:
				tv_title_detail_notruoithantruocdo.setVisibility(View.GONE);
				findWebview(list.get(18).getContent());
				break;
			case R.id.radioButton20_1:
				tv_title_detail_notruoithantruocdo.setVisibility(View.GONE);
				findWebview(list.get(19).getContent());
				break;
			case R.id.radioButton20_2:
				tv_title_detail_notruoithantruocdo.setVisibility(View.GONE);
				findWebview(list.get(19).getContent());
				break;
			case R.id.radioButton21_1:
				tv_title_detail_notruoithantruocdo.setVisibility(View.GONE);
				findWebview(list.get(20).getContent());
				break;
			case R.id.radioButton21_2:
				tv_title_detail_notruoithantruocdo.setVisibility(View.GONE);
				findWebview(list.get(20).getContent());
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
